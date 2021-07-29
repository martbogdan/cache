package com.example.cacher.config;

import com.example.cacher.cache.ElasticCache;
import com.example.cacher.cache.ElasticCacheManager;
import com.example.cacher.repository.PersonElasticRepo;
import com.example.cacher.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;

@Configuration
public class CachingConfig {

    @Autowired
    PersonElasticRepo elasticRepo;
    @Autowired
    PersonRepository personRepository;

    @Bean
    @Primary
    public CacheManager defaultCacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager("persons");
        cacheManager.setCacheNames(Arrays.asList("default", "primary"));
        return cacheManager;
    }

    @Bean
    public CacheManager elasticCacheManager() {
        return new ElasticCacheManager(elasticRepo);
    }

    @Bean
    public Cache cache() {
        return new ElasticCache(elasticRepo);
    }
}
