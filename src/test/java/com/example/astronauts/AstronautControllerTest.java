package com.example.astronauts;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class AstronautControllerTest {

    private RestTemplate restTemplate;
    @LocalServerPort
    private int randomServerPort;
    @Before
    public void setupRestTemplate() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(5);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(5);

        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        final CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager)
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();
        factory.setHttpClient(httpClient);
        restTemplate = new RestTemplate(factory);
        restTemplate.setRequestFactory(factory);
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(0);
    }
    @Test
    public void testGetAstronautLists() {
        Astronaut[] results = restTemplate.getForObject("http://localhost:" + randomServerPort +"/api/1.0/astronaut/", Astronaut[].class);
        assertTrue(results!=null);
        assertTrue(results.length > 1);

        Astronaut astronaut = results[0];
        assertTrue(astronaut.getName() !=null && !astronaut.getName().isEmpty());
        assertTrue(astronaut.getNationality() !=null && !astronaut.getNationality().isEmpty());
        assertTrue(astronaut.getProfile_image_thumbnail() !=null && !astronaut.getProfile_image_thumbnail().isEmpty());
    }
    @Test
    public void testGetAstronautByID() {
        Astronaut astronaut = restTemplate.getForObject("http://localhost:"  + randomServerPort + "/api/1.0/astronaut/1", Astronaut.class);
        assertTrue(astronaut!=null);
        assertTrue(astronaut.getDate_of_birth() !=null && !astronaut.getDate_of_birth().isEmpty());
        assertTrue(astronaut.getBio() !=null && !astronaut.getBio().isEmpty());
    }

}
