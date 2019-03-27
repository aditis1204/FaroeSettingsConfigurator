package org.n52.faroe.configurator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Starting point for the configurator API.
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class ConfiguratorApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfiguratorApplication.class, args);
  }
}
