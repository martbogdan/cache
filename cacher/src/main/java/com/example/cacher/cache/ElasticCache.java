package com.example.cacher.cache;

import com.example.cacher.model.Person;
import com.example.cacher.repository.PersonElasticRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class ElasticCache extends AbstractValueAdaptingCache {

    private final Map<String, Cache> cacheMap = new HashMap<>(16);

    @Autowired
    private PersonElasticRepo elasticRepo;

    /**
     * Create an {@code AbstractValueAdaptingCache} with the given setting.
     *
     * @param allowNullValues whether to allow for {@code null} values
     */
    protected ElasticCache(boolean allowNullValues) {
        super(allowNullValues);
    }

    @Override
    protected Object lookup(Object key) {
        return elasticRepo.findById((Long) key, PageRequest.of(0, 10))
                .stream()
                .filter(p -> p.getId().equals((Long) key))
                .findFirst()
                .orElse(null);
    }

    public ElasticCache(PersonElasticRepo elasticRepo) {
        this(true);
        this.elasticRepo = elasticRepo;
    }

    @Override
    public String getName() {
        return "persons";
    }

    @Override
    public PersonElasticRepo getNativeCache() {
        return this.elasticRepo;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        this.elasticRepo.save((Person) value);
    }

    @Override
    public void evict(Object key) {
        this.elasticRepo.deleteById(String.valueOf((Long) key));
    }

    @Override
    public void clear() {
        this.elasticRepo.deleteAll();
    }
}
