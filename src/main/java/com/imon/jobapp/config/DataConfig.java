package com.imon.jobapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.jboss.C3P0PooledDataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    // This manual dataSource Bean doesn't provide connection pool so we should
    // not use in the production
    // @Bean
    // public DataSource dataSource() {
    // DriverManagerDataSource dataSource = new DriverManagerDataSource();
    // dataSource.setUrl(url);
    // dataSource.setUsername(username);
    // dataSource.setPassword(password);
    // return dataSource;
    // }

    // This is manually setting Hikaripool connection pooling
    // @Bean
    // public DataSource dataSource() {
    // HikariDataSource dataSource = new HikariDataSource();
    // dataSource.setJdbcUrl(url);
    // dataSource.setUsername(username);
    // dataSource.setPassword(password);
    // return dataSource;
    // }

    // setting C3PO connection pooling mechanism instead of default Hikaripool
    // connection pooling
    @Bean
    DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setDataSourceName(username);
        dataSource.setPassword(password);

        dataSource.setMinPoolSize(5);
        dataSource.setMaxPoolSize(20);
        dataSource.setMaxIdleTime(300);
        dataSource.setMaxStatements(50);
        return dataSource;
    }

    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

}
