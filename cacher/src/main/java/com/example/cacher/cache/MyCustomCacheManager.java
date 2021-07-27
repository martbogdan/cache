package com.example.cacher.cache;

import com.example.cacher.repository.PersonElasticRepo;
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

public class MyCustomCacheManager implements CacheManager {

    private final Map<String, Cache> cacheMap = new HashMap<>();

    @Autowired
    private final Cache cache;

    public MyCustomCacheManager(Cache cache, String... cacheNames) {
        setCacheNames(Arrays.asList(cacheNames));
        this.cache = cache;
    }

    public void setCacheNames(@Nullable Collection<String> cacheNames) {
        if (cacheNames != null) {
            for (String name : cacheNames) {
                this.cacheMap.put(name, cache);
            }
        }
    }

    @Override
    public Cache getCache(String name) {
        return cacheMap.get(name);
    }

    @Override
    public Collection<String> getCacheNames() {
        return cacheMap.keySet();
    }
}
