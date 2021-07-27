package com.example.cacher.config;

import com.example.cacher.cache.ElasticCache;
import com.example.cacher.cache.MyCustomCacheManager;
import com.example.cacher.repository.PersonElasticRepo;
import com.example.cacher.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CachingConfig {

    @Autowired
    PersonElasticRepo elasticRepo;
    @Autowired
    PersonRepository personRepository;

    @Bean
    public CacheManager cacheManager() {
        return new MyCustomCacheManager(elasticRepo);
    }

    @Primary
    @Bean
    public Cache cache() {
        return new ElasticCache(elasticRepo);
    }
}
