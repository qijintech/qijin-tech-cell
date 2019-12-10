package tech.qijin.cell.im.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author michealyang
 * @date 2019-11-12
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Configuration
@MapperScan(basePackages = "tech.qijin.cell.im.db.dao",
        sqlSessionFactoryRef = "imSqlSessionFactory",
        sqlSessionTemplateRef = "imSqlSessionTemplate")
public class ImDatasourceConfig {

    @Primary
    @Bean("dataSourceIm")
    @ConfigurationProperties("spring.datasource.druid.im")
    public DataSource dataSourceOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "imSqlSessionFactory")
    public SqlSessionFactory imSqlSessionFactory(@Qualifier("dataSourceIm") DataSource dataSourceOne)
            throws Exception {
        return getSqlSessionFactory(dataSourceOne);
    }

    @Bean("imSqlSessionTemplate")
    SqlSessionTemplate sqlSessionTemplate(@Qualifier("imSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name = "imTransactionManager")
    public DataSourceTransactionManager imTransactionManager(@Qualifier("dataSourceIm") DataSource dataSourceOne) {
        return new DataSourceTransactionManager(dataSourceOne);
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource)
            throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }
}
