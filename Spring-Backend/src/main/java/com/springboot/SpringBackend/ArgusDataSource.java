package com.springboot.SpringBackend;

import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class ArgusDataSource {
    /*
    @Bean
    @Profile("heroku")
    //@LiquibaseDataSource
    public javax.sql.DataSource herokuDataSource() {
        String username = "mxewdvawjquqsn";
        String password = "4b9049fdc93a174ebae9d2a03688b3bd2f01638fe96748cc0014fe835e598af8";
        String dbUrl = "jdbc:postgresql://ec2-18-203-7-163.eu-west-1.compute.amazonaws.com:5432/df8a232j9ndrq8";
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(dbUrl)
                .username(username)
                .password(password)
                .build();
    }
    */

    @Bean
    @Profile("local")
    @Primary
    public javax.sql.DataSource postgresqlDataSource() {
        return (javax.sql.DataSource) DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/argus_db?stringtype=unspecified")
                .username("Sigma")
                .password("Argus")
                .build();
    }

}
