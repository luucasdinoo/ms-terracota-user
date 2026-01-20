package br.com.terracota.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${TRC_DB_URL:jdbc}")
    private String dbUrl;

    @Value("${TRC_DB_USERNAME}")
    private String dbUsername;

    @Value("${TRC_DB_PASSWORD}")
    private String dbPassword;

    @Value("${TRC_DB_DRIVER_CLASS_NAME}")
    private String dbDriverClassName;

    @Bean
    public DataSource connectionDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(this.dbUrl);
        dataSource.setUsername(this.dbUsername);
        dataSource.setPassword(this.dbPassword);
        dataSource.setDriverClassName(this.dbDriverClassName);
        return dataSource;
    }
}
