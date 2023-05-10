package net.lazars.chores.adapter.redis.service;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lazars.chores.adapter.redis.config.RedisCacheConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CacheEvictionService {

  private final CacheManager cacheManager;

  public void evictCache(final CacheType cacheType, final String cacheKey) {
    evictCache(cacheType.getCacheName(), cacheKey);
    evictCache(cacheType.getCacheName(), cacheType.getGroupCollectionName());
  }

  public void evictCache(final CacheType cacheType) {
    final String cacheName = cacheType.getCacheName();
    final Cache cache = cacheManager.getCache(cacheName);
    if (cache != null) {
      cache.clear();
      log.info("Evicted cache: {}", cacheName);
    }
  }

  private void evictCache(final String cacheName, final String cacheKey) {
    final Cache cache = cacheManager.getCache(cacheName);
    if (cache != null) {
      cache.evict(cacheKey);
      log.info("Evicted cache: {} with key: {}", cacheName, cacheKey);
    }
  }

  @Getter
  @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
  public enum CacheType {
    USER_CACHE(RedisCacheConfiguration.USER_CACHE, RedisCacheConfiguration.USER_GROUP_CACHE),
    ITEM_CACHE(RedisCacheConfiguration.ITEM_CACHE, RedisCacheConfiguration.ITEM_GROUP_CACHE);

    private final String cacheName;
    private final String groupCollectionName;
  }
}
