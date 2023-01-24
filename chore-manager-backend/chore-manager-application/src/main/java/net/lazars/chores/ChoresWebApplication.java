package net.lazars.chores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(
    includeFilters =
        @Filter(
            type = FilterType.REGEX,
            pattern = {"net.lazars.chores.core.service.*"}))
public class ChoresWebApplication {

  public static void main(final String[] args) {
    SpringApplication.run(ChoresWebApplication.class, args);
  }
}
