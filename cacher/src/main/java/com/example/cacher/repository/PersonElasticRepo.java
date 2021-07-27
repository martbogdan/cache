package com.example.cacher.repository;

import com.example.cacher.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonElasticRepo extends ElasticsearchRepository<Person, String> {

    Page<Person> findById(Long id, Pageable pageable);
}
