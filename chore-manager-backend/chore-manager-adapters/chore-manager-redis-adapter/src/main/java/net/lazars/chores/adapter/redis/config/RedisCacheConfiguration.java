package net.lazars.chores.adapter.redis.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class RedisCacheConfiguration {

  public static final String USER_CACHE = "users";
  public static final String USER_GROUP_CACHE = "'allUsers'";
  public static final String ITEM_CACHE = "items";
  public static final String ITEM_GROUP_CACHE = "'allItems'";

  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager(USER_CACHE, ITEM_CACHE);
  }
}
