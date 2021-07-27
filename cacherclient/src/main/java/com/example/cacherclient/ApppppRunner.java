package com.example.cacherclient;

import com.example.cacherclient.util.JsonReader;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApppppRunner implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {

        for (int i=0; i<10; i++) {
            if (i == 3 || i == 8) {
                JSONObject jsonObject1 = JsonReader.readJsonFromUrl("http://localhost:8081/person/3");
                JSONObject jsonObject5 = JsonReader.readJsonFromUrl("http://localhost:8081/person/8");
                System.out.println(jsonObject1.toString());
                System.out.println(jsonObject5.toString());
            }
            JSONObject jsonObject1 = JsonReader.readJsonFromUrl("http://localhost:8081/person/1");
            JSONObject jsonObject5 = JsonReader.readJsonFromUrl("http://localhost:8081/person/5");
            System.out.println(jsonObject1.toString());
            System.out.println(jsonObject5.toString());
        }

    }
}
