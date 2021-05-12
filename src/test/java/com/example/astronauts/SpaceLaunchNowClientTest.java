package com.example.astronauts;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SpaceLaunchNowClientTest {

    @Autowired
    private SpaceLaunchNowClient spaceLaunchNowClient;

    @Test
    public void testListAstronauts() {
        List<Astronaut> astronautList = spaceLaunchNowClient.listAstronauts();
        assertTrue(astronautList!=null);
        assertTrue(astronautList.size() > 1);
        Astronaut astronaut = astronautList.get(0);
        assertTrue(astronaut!=null);
        assertTrue(astronaut.getName() !=null && !astronaut.getName().isEmpty());
        assertTrue(astronaut.getNationality() !=null && !astronaut.getNationality().isEmpty());
        assertTrue(astronaut.getProfile_image_thumbnail() !=null && !astronaut.getProfile_image_thumbnail().isEmpty());
    }

    @Test
    public void testLookupAstronautByID() {
        Astronaut astronaut = spaceLaunchNowClient.lookupAstronautByID("1");
        assertTrue(astronaut!=null);
        assertTrue(astronaut.getDate_of_birth() !=null && !astronaut.getDate_of_birth().isEmpty());
        assertTrue(astronaut.getBio() !=null && !astronaut.getBio().isEmpty());
    }


    @Test
    public void testErrorHandling() {
        try {
            Astronaut astronaut = spaceLaunchNowClient.lookupAstronautByID("DKKD");
        }
        catch (HttpClientErrorException ex) {
            assertTrue(ex.getMessage().indexOf("404") >= 0);
            return;
        }
        assertTrue("Expected 404 exception",false);
    }
    public void setSpaceLaunchNowClient(SpaceLaunchNowClient spaceLaunchNowClient) {
        this.spaceLaunchNowClient = spaceLaunchNowClient;
    }
}
