package com.lookinsure.insuranceDemo.infrastructure.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.*;

import javax.sql.DataSource;

@Configuration
public class DBConfig {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .setDatabaseConfigurer(
                        EmbeddedDatabaseConfigurers.customizeConfigurer(EmbeddedDatabaseType.H2,embeddedDatabaseConfigurer -> this.customize(embeddedDatabaseConfigurer,dataSourceProperties))
                )
                .build();
    }

    private EmbeddedDatabaseConfigurer customize(EmbeddedDatabaseConfigurer defaultConfigurer,DataSourceProperties dataSourceProperties) {
        return new EmbeddedDatabaseConfigurerDelegate(defaultConfigurer) {
            @Override
            public void configureConnectionProperties(ConnectionProperties properties, String databaseName) {
                super.configureConnectionProperties(properties, databaseName);
                properties.setUrl(dataSourceProperties.getUrl());
                properties.setUsername(dataSourceProperties.getUsername());
                properties.setPassword(dataSourceProperties.getPassword());
            }
        };
    }
}
