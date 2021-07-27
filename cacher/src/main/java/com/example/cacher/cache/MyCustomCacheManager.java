package com.example.cacher.cache;

import com.example.cacher.repository.PersonElasticRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.support.AbstractCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class MyCustomCacheManager implements CacheManager {

    private final Map<String, Cache> cacheMap = new HashMap<>();
    private final PersonElasticRepo repo;

    @Override
    public Cache getCache(String name) {
        if (!cacheMap.containsKey(name)) {
            cacheMap.put(name, new ElasticCache(repo));
        }
        return cacheMap.get(name);
    }

    @Override
    public Collection<String> getCacheNames() {
        return cacheMap.keySet();
    }
}
