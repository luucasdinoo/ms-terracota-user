package br.com.terracota.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${trc.db.url}")
    private String dbUrl;

    @Value("${trc.db.username}")
    private String dbUsername;

    @Value("${trc.db.password}")
    private String dbPassword;

    @Value("${trc.db.driver-class-name}")
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
