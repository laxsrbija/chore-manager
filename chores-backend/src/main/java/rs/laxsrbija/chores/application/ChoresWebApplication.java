package rs.laxsrbija.chores.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "rs.laxsrbija.chores")
public class ChoresWebApplication {

  public static void main(final String[] args) {
    SpringApplication.run(ChoresWebApplication.class, args);
  }
}
