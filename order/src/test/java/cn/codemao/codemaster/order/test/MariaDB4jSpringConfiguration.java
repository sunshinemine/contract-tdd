package cn.codemao.codemaster.order.test;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("test")
public class MariaDB4jSpringConfiguration {

    @Bean
    public MariaDB4jSpringService mariaDB4jSpringService() {
        MariaDB4jSpringService mariaDB4jSpringService = new MariaDB4jSpringService();
        mariaDB4jSpringService.getConfiguration().addArg("--character-set-server=utf8");
        mariaDB4jSpringService.getConfiguration().addArg("--user=root");
        return mariaDB4jSpringService;
    }

    @Bean
    public DataSource dataSource(MariaDB4jSpringService mariaDB4jSpringService,
                                 @Value("${spring.datasource.name}") String databaseName,
                                 @Value("${spring.datasource.username}") String username,
                                 @Value("${spring.datasource.password}") String password,
                                 @Value("${spring.datasource.driver-class-name}") String driverName) {
        try {
            mariaDB4jSpringService.getDB().createDB(databaseName);
        } catch (ManagedProcessException e) {
            e.printStackTrace();
        }

        DBConfigurationBuilder config = mariaDB4jSpringService.getConfiguration();
        return DataSourceBuilder.create()
            .driverClassName(driverName)
            .url(config.getURL(databaseName))
            .username(username)
            .password(password)
            .build();
    }
}
