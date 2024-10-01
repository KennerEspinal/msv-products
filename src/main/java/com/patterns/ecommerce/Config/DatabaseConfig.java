package com.patterns.ecommerce.Config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${db.engine}")
    private String dbEngine;

    @Value("${spring.datasource.mysql.url}")
    private String mysqlUrl;

    @Value("${spring.datasource.mysql.username}")
    private String mysqlUser;

    @Value("${spring.datasource.mysql.password}")
    private String mysqlPassword;

    @Value("${spring.datasource.oracle.url}")
    private String oracleUrl;

    @Value("${spring.datasource.oracle.username}")
    private String oracleUser;

    @Value("${spring.datasource.oracle.password}")
    private String oraclePassword;

    @PostConstruct
    public void checkDbEngine() {
        System.out.println("DB Engine: " + dbEngine);
        System.out.println("MySQL URL: " + mysqlUrl);
        System.out.println("Oracle URL: " + oracleUrl);
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();

        if ("oracle".equalsIgnoreCase(dbEngine)) {
            System.out.println("Configuring Oracle DataSource");
            dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
            dataSourceBuilder.url(oracleUrl);
            dataSourceBuilder.username(oracleUser);
            dataSourceBuilder.password(oraclePassword);
        } else {
            System.out.println("Configuring MySQL DataSource");
            dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
            dataSourceBuilder.url(mysqlUrl);
            dataSourceBuilder.username(mysqlUser);
            dataSourceBuilder.password(mysqlPassword);
        }

        return dataSourceBuilder.build();
    }
}
