package com.springboot.SpringBackend;

import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
public class ArgusDataSource {
    
    @Bean
    @Profile("heroku")
    //@LiquibaseDataSource
    public javax.sql.DataSource herokuDataSource() {
        String username = "qsszqzdqblzzgu";
        String password = "00637803c6774e6ab493967691637baf0f11a6c3006ee28cb784cdaba979d4c7";
        String dbUrl = "jdbc:postgresql://ec2-54-246-87-132.eu-west-1.compute.amazonaws.com:5432/debhjjv3t3u3fs";
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(dbUrl)
                .username(username)
                .password(password)
                .build();
    }
    
    /*
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
    */

}
