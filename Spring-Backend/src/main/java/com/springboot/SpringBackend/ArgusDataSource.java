package com.springboot.SpringBackend;

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
        String username = "riocmbgqjouvuc";
        String password = "ee49f128e49e2768cbca7ec4a409c010a284abcd951d10eeadb9c2d2e8da5172";
        String dbUrl = "jdbc:postgresql://ec2-52-30-161-203.eu-west-1.compute.amazonaws.com:5432/d3c0s3b2ndlilg";
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url(dbUrl)
                .username(username)
                .password(password)
                .build();
    }

    /*@Bean
    @Profile("local")
    @Primary
    public javax.sql.DataSource postgresqlDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql://localhost:5432/argus_db?stringtype=unspecified")
                .username("Sigma")
                .password("Argus")
                .build();
    }*/
}
