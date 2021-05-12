package com.example.astronauts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SpaceLaunchNowClientImpl implements SpaceLaunchNowClient {
    @Value("${spacelaunchnowclient.list.url}")
    private String serviceUrl;
    private final RestTemplate restTemplate;

    public SpaceLaunchNowClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Astronaut> listAstronauts() {
        List<Astronaut> astronautList = new ArrayList<>();
        String nextUrl = serviceUrl + "?limit=100&offset=0";
        while(nextUrl!= null && !nextUrl.isEmpty()) {
            SpaceLaunchNowResponse spaceLaunchNowResponse = restTemplate.getForObject(nextUrl, SpaceLaunchNowResponse.class);
            astronautList.addAll(Arrays.asList(spaceLaunchNowResponse.getResults()));
            nextUrl = spaceLaunchNowResponse.getNext();
        }
        return astronautList;
    }

    @Override
    public Astronaut lookupAstronautByID(String id) {
        String url = serviceUrl + id;
        Astronaut astronaut = restTemplate.getForObject(url, Astronaut.class);
        return astronaut;
    }

}
