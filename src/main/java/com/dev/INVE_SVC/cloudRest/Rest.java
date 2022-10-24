package com.dev.INVE_SVC.cloudRest;

import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.dev.INVE_SVC.config.ServiceConfig;
import com.dev.INVE_SVC.exception.CustomException;

@Component
public class Rest {
	
	@Autowired
	private KeycloakRestTemplate restTemplate;
	
	@Autowired
	ServiceConfig config;
	
	public Object service(String serviceUrl, String vMethod, Object dataOb) {
		
		HttpMethod hpptmethod = HttpMethod.GET;
		
		if(vMethod.equals("POST")) {
			hpptmethod = HttpMethod.POST;
		}
		
		//통신하여 돌아온 데이터가 정의는 오브젝트로 정의한다.
		System.out.println(config.getGateway() + "/" + serviceUrl);
		
		ResponseEntity<?> restExchange = restTemplate.exchange(config.getGateway() + "/" + serviceUrl, hpptmethod, null, Object.class, dataOb);
		
		//리턴된 http 통신이 상태가 OK 가 아닐때
		if(!restExchange.getStatusCode().equals(HttpStatus.OK)){
			throw new CustomException(restExchange.getStatusCode().toString(), restExchange.getBody().toString());
		}

		return restExchange.getBody();
	}
}
