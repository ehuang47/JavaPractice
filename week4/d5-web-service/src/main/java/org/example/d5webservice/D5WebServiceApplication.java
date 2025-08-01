package org.example.d5webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class D5WebServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(D5WebServiceApplication.class, args);
  }

}
