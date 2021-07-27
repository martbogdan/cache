package com.example.cacher.repository;

import com.example.cacher.model.Person;

public interface PersonRepository {

    Person getById(Long id);
}
