package net.lazars.chores.adapter.rest.config;

import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CacheConfiguration implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/*.js", "/*.css", "/*.woff", "/*.woff2")
        .addResourceLocations("classpath:/resources/")
        .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS).cachePrivate().mustRevalidate());

    registry
        .addResourceHandler("/index.html")
        .addResourceLocations("classpath:/resources/index.html")
        .setCacheControl(CacheControl.noStore());
  }
}
