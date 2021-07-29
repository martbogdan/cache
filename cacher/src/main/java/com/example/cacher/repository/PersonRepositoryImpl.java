package com.example.cacher.repository;

import com.example.cacher.model.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonRepository{

    @Override
    @Cacheable(cacheNames = {"default", "primary"})
    public Person getById(Long id) {
            simulateSlowService();
            return new Person(id, "PersonName-" + id);
    }

    @Override
    @Cacheable(cacheNames = {"someCache"}, cacheManager = "elasticCacheManager", key = "#id")
    public Person getByIdAndName(Long id, String name) {
        simulateSlowService();
        return new Person(id, name);
    }

    private static void simulateSlowService() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
