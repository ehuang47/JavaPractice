package org.example.hibernateserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

// Need this only if it lies outside the main package or class is not a component
//@EnableConfigurationProperties(HibernateProperty.class)
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class HibernateServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(HibernateServerApplication.class, args);
  }

}
