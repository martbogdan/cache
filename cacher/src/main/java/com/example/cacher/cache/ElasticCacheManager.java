package com.example.cacher.cache;

import com.example.cacher.repository.PersonElasticRepo;
import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class ElasticCacheManager implements CacheManager {

    private final Map<String, Cache> cacheMap = new HashMap<>();
    private final PersonElasticRepo repo;

    @Override
    public Cache getCache(String name) {
        return cacheMap.computeIfAbsent(name, c -> new ElasticCache(repo));
    }

    @Override
    public Collection<String> getCacheNames() {
        return cacheMap.keySet();
    }
}
