package com.example.astronauts;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
public class AstronautsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AstronautsApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {


		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setMaxTotal(20);
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);

		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

		final CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager)
				.setRedirectStrategy(new LaxRedirectStrategy())
				.build();
		factory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.setRequestFactory(factory);
		factory.setConnectTimeout(3000);
		factory.setReadTimeout(20000);
		return restTemplate;
	}
}
