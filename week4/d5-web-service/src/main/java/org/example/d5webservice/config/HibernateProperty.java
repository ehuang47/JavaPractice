package org.example.d5webservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "database.hibernate")
public class HibernateProperty {
  private String url;

  private String driver;

  private String username;

  private String password;

  private String dialect;

  private String showSql;
}