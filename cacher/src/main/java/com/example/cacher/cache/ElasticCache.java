package com.example.cacher.cache;

import com.example.cacher.model.Person;
import com.example.cacher.repository.PersonElasticRepo;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.domain.PageRequest;
import java.util.concurrent.Callable;

public class ElasticCache implements Cache {

    private final PersonElasticRepo elasticRepo;

    public ElasticCache(PersonElasticRepo elasticRepo) {
        this.elasticRepo = elasticRepo;
    }

    @Override
    public String getName() {
        return "persons";
    }

    @Override
    public Object getNativeCache() {
        return this;
    }

    @Override
    public ValueWrapper get(Object key) {
        Long id = (Long) key;
        Person person = elasticRepo.findById(id, PageRequest.of(0, 10)).get().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        return new SimpleValueWrapper(person);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        Long id = (Long) key;
        Person person = elasticRepo.findById(id, PageRequest.of(0, 10)).get().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        Object value = person;
        if (value != null && type != null && !type.isInstance(value)) {
            throw new IllegalStateException("Cached value is not of required type [" + type.getName() + "]: " + value);
        } else {
            return (T) value;
        }
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        Long id = (Long) key;
        Person person = (Person) value;
        elasticRepo.save(person);
    }

    @Override
    public void evict(Object key) {
        Long id = (Long) key;
        elasticRepo.deleteById(String.valueOf(id));
    }

    @Override
    public void clear() {
        elasticRepo.deleteAll();
    }
}
