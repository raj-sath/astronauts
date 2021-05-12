package com.example.astronauts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface AstronautController {

    ResponseEntity<List<Astronaut>> listAstronauts();

    ResponseEntity<Astronaut> getAstronaut(String id);
}
