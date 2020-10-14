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
        String username = "axcbwlyplqglzb";
        String password = "d82d8c9d290b821033d9bd59e3a65ab15273113317e68784beed29d46cdd32f9";
        String dbUrl = "jdbc:postgresql://ec2-54-246-87-132.eu-west-1.compute.amazonaws.com:5432/ddpbd232fd4gmg";
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
