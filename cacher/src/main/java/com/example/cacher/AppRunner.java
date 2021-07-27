package com.example.cacher;

import com.example.cacher.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AppRunner implements CommandLineRunner {

    private final PersonRepository personRepository;

    public AppRunner(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("!======= Fetching Person ========!");
        log.info("Id -> 1 " + personRepository.getById(1L));
        log.info("Id -> 5 " + personRepository.getById(5L));
        log.info("Id -> 1 " + personRepository.getById(1L));
        log.info("Id -> 5 " + personRepository.getById(5L));
        log.info("Id -> 1 " + personRepository.getById(1L));
        log.info("Id -> 5 " + personRepository.getById(5L));
    }
}
