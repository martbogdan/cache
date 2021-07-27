package com.example.cacher.controller;

import com.example.cacher.repository.PersonElasticRepo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    private final PersonElasticRepo elasticRepo;

    public CacheController(PersonElasticRepo elasticRepo) {
        this.elasticRepo = elasticRepo;
    }

    @DeleteMapping("/clean")
    public void cleanCache() {
        elasticRepo.deleteAll();
    }
}
