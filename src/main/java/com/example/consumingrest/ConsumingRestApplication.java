package com.example.consumingrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	@Autowired
	AppConfiguration conf;

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			System.out.println("CommandLineRunner run ...");
			MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
			headers.add("Ror-Profil-Utilisateur-Code", "CU22");
			headers.add("Ror-Profil-Utilisateur-System", "1.2.250.1.213.1.6.1.66");
			headers.add("Ror-Role-Metier-Code", "AUTOMATE");
			headers.add("Ror-Role-Metier-System", "1.2.250.1.213.1.1.4.6");
			headers.add("PROFIL", "Profil1");
			HttpEntity<?> entity = new HttpEntity<Object>(headers);			
			String queryUrl ="https://api.rortest.esante.gouv.fr/ws-diffusion-fhir/Organization?_count=10";
			ResponseEntity<String> response = conf.restTemplate().exchange(queryUrl, HttpMethod.GET, entity, String.class);
			System.out.println("appel http done!");
			System.out.println("body:\n" + response.getBody());
			
		};
	}
}
