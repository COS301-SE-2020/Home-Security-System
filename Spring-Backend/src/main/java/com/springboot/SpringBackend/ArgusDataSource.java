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
        String username = "emgeverppoehii";
        String password = "b81204231483dbc277a65618de19dcb441089df4bae9864146ed137d90b06f8f";
        String dbUrl = "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com:5432/da9s5ch2v5qhrl";
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
