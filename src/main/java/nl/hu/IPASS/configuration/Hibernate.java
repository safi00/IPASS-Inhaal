package nl.hu.IPASS.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration   // Twee verschillende Configuration imports
public class Hibernate {
    @Bean
    public static SessionFactory createSessionFactory() {
        org.hibernate.cfg.Configuration config = new org.hibernate.cfg.Configuration();   // Twee verschillende Configuration imports
        config.setProperty("hibernate.connection.url", System.getenv("pok_url"));
        config.setProperty("hibernate.connection.username", System.getenv("pok_username"));
        config.setProperty("hibernate.connection.password", System.getenv("pok_password"));
        config.configure();
        return config.buildSessionFactory();
    }
}
