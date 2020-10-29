package com.limo.boot.config.mybatis;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@MapperScan("com.limo.boot.mapper")
public class SessionFactoryConfiguration {
    private Logger logger = LoggerFactory.getLogger(SessionFactoryConfiguration.class);
    /**
     * 自动装在数据库资源bean
     */
    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactory() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //Mybatis的参数配置
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        //配置mybatis需要的 datasource
        Environment environment = new Environment("dev", transactionFactory, dataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
        //将配置加入sqlSessionFactoryBean
        sqlSessionFactoryBean.setConfiguration(configuration);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //启用Mybatis的全部xml文件，就不需要一个个去打开
        String packageSerchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/mapper/**.xml";
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(packageSerchPath));
        sqlSessionFactoryBean.setDataSource(dataSource);
        //实体类所在的包
        sqlSessionFactoryBean.setTypeAliasesPackage("com.limo.boot.entity");
        logger.info("Mybatis config finished");
        return sqlSessionFactoryBean;
    }
}