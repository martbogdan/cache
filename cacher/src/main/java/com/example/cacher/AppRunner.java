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
        log.info("!======= Fetching Person By Id ========!");
        for (int k=0; k<2; k++) {
            for (long i=1; i<6; i++) {
                log.info("Id -> " + i + " " + personRepository.getById(i));
            }
        }

        log.info("!======= Fetching Person By Id and Name ========!");
        for (int k=0; k<2; k++) {
            for (long i=1; i<6; i++) {
                log.info("Id -> " + i + " " + personRepository.getByIdAndName(i, "Name-" + i));
            }
        }

    }
}
