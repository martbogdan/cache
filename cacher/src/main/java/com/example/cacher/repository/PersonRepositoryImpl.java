package com.example.cacher.repository;

import com.example.cacher.model.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonRepository{

    @Override
    @Cacheable("persons")
    public Person getById(Long id) {
            simulateSlowService();
            Person person1 = new Person(id, "PersonName");
            //personElasticRepo.save(person1);
            return person1;
    }

    private static void simulateSlowService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
