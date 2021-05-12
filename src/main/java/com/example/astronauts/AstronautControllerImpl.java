package com.example.astronauts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;


@RestController
@RequestMapping(path="/api/1.0/astronaut")
public class AstronautControllerImpl  implements AstronautController {

    private final SpaceLaunchNowClient spaceLaunchNowClient;

    Logger logger = LoggerFactory.getLogger(AstronautControllerImpl.class);

    @Autowired
    public AstronautControllerImpl(SpaceLaunchNowClient spaceLaunchNowClient) {
        this.spaceLaunchNowClient = spaceLaunchNowClient;
    }

    @GetMapping(value = "/")
    @Override
    public ResponseEntity<List<Astronaut>> listAstronauts() {
        try {
            return new ResponseEntity<>(spaceLaunchNowClient.listAstronauts(),HttpStatus.OK);
        }
        catch(HttpClientErrorException ex) {
            logger.error(ex.getMessage(),ex);
            return new ResponseEntity<>( null, HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(Exception ex) {
            logger.error(ex.getMessage(),ex);
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<Astronaut> getAstronaut(@PathVariable("id") String id) {
        try {
            return new ResponseEntity<>(spaceLaunchNowClient.lookupAstronautByID(id),HttpStatus.OK);
        }
        catch(HttpClientErrorException ex) {
            logger.error(ex.getMessage(),ex);
            return new ResponseEntity<>(null, null, HttpStatus.SERVICE_UNAVAILABLE);
        }
        catch(Exception ex) {
            logger.error(ex.getMessage(),ex);
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
