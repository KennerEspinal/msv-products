package com.patterns.ecommerce.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

//    Variables for MySQL database

    @Value("${db.engine:mysql}")
    private String dbEngine;

    @Value("${MYSQL_USER:root}")
    private String mysqlUser;

    @Value("${MYSQL_PASSWORD:root}")
    private String mysqlPassword;

    @Value("${MYSQL_DATABASE:ecommerce}")
    private String mysqlDatabase;

//    Variables for Oracle database

    @Value("${ORACLE_PASSWORD:m1p4ssw0rd}")
    private String oraclePassword;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();

        if ("oracle".equalsIgnoreCase(dbEngine)) {
            dataSourceBuilder.driverClassName("oracle.jdbc.OracleDriver");
            dataSourceBuilder.url("jdbc:oracle:thin:@localhost:1521/XE");
            dataSourceBuilder.username("system");
            dataSourceBuilder.password(oraclePassword);
        } else {
            dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
            dataSourceBuilder.url("jdbc:mysql://localhost:3306/" + mysqlDatabase);
            dataSourceBuilder.username(mysqlUser);
            dataSourceBuilder.password(mysqlPassword);
        }

        return dataSourceBuilder.build();
    }
}
