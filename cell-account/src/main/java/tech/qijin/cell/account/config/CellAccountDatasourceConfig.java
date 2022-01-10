package tech.qijin.cell.account.config;

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
import tech.qijin.util4j.mybatis.interceptor.SqlInfoInterceptor;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author michealyang
 * @date 2019-11-12
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Configuration
@MapperScan(basePackages = "tech.qijin.cell.account.db.dao",
        sqlSessionFactoryRef = "accountSqlSessionFactory",
        sqlSessionTemplateRef = "accountSqlSessionTemplate")
public class CellAccountDatasourceConfig {

    @Bean("accountDataSourceTemplate")
    @ConfigurationProperties("spring.datasource.druid.account")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "accountSqlSessionFactory")
    public SqlSessionFactory accountSqlSessionFactory(@Qualifier("accountDataSourceTemplate") DataSource dataSourceOne)
            throws Exception {
        return getSqlSessionFactory(dataSourceOne);
    }

    @Bean("accountSqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate(@Qualifier("accountSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "accountTransactionManager")
    public DataSourceTransactionManager accountTransactionManager(@Qualifier("accountDataSourceTemplate") DataSource dataSourceOne) {
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
        channelProperties.put("excludedTables", "drops,item,drops_item");
        channelInterceptor.setProperties(channelProperties);
        configuration.addInterceptor(channelInterceptor);
        SqlInfoInterceptor sqlInfoInterceptor = new SqlInfoInterceptor();
        configuration.addInterceptor(sqlInfoInterceptor);
        sqlSessionFactoryBean.setConfiguration(configuration);
        return sqlSessionFactoryBean.getObject();
    }
}
