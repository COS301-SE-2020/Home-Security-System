package com.springboot.SpringBackend.config;

/*
import com.springboot.SpringBackend.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;
*/
//@Configuration
public class LiquibaseConfig {
/*
    // Method 1
    @Value("${../../resources/db/liquibase-changelog.xml}")
    private String changelog;

    @Autowired
    DataSource dataSource;

    @Bean
    public SpringLiquibase liquibase()  {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog(changelog);
        return liquibase;
    }

    // Method 2
    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
    }
*/
}
