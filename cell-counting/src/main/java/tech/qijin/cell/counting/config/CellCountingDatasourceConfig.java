package tech.qijin.cell.counting.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tech.qijin.util4j.mybatis.handler.EnumValueTypeHandler;
import tech.qijin.util4j.mybatis.interceptor.ChannelInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author michealyang
 * @date 2019-11-12
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Configuration
@MapperScan(basePackages = "tech.qijin.cell.counting.db.dao",
        sqlSessionFactoryRef = "countingSqlSessionFactory",
        sqlSessionTemplateRef = "countingSqlSessionTemplate")
public class CellCountingDatasourceConfig {

    @Bean("countingDataSourceTemplate")
    @ConfigurationProperties("spring.datasource.druid.counting")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "countingSqlSessionFactory")
    public SqlSessionFactory countingSqlSessionFactory(@Qualifier("countingDataSourceTemplate") DataSource dataSourceOne)
            throws Exception {
        return getSqlSessionFactory(dataSourceOne);
    }

    @Bean("countingSqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate(@Qualifier("countingSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "countingTransactionManager")
    public DataSourceTransactionManager countingTransactionManager(@Qualifier("countingDataSourceTemplate") DataSource dataSourceOne) {
        return new DataSourceTransactionManager(dataSourceOne);
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setDefaultEnumTypeHandler(EnumValueTypeHandler.class);
        ChannelInterceptor channelInterceptor = new ChannelInterceptor();
        Properties channelProperties = new Properties();
        channelProperties.put("tenantColumnName", "channel");
        channelProperties.put("excludedTables", "counting_template");
        channelInterceptor.setProperties(channelProperties);
        configuration.addInterceptor(channelInterceptor);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }
}
