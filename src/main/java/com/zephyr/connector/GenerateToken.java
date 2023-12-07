package com.zephyr.connector;

import java.net.URI;
import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;
import com.thed.zephyr.cloud.rest.client.impl.JwtGeneratorImpl;
import com.thed.zephyr.cloud.rest.model.ZConfig;

public class GenerateToken {
	private JwtResponse response;
	private JwtGenerator jwtGenerator;

	public GenerateToken(ZConfig zconfig) {
		this.jwtGenerator = new JwtGeneratorImpl(zconfig);
	}

	public JwtResponse getTokenObject(String url, String method, int longitivity) throws Exception {
		URI uri = new URI(url);
		String jwt = jwtGenerator.generateJWT(method, uri, longitivity);
		HashMap<String, String> tokenObjMap = new HashMap<String, String>();
		tokenObjMap.put("jwtToken", jwt);
		tokenObjMap.put("finalApi", url);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.valueToTree(tokenObjMap);
		this.response = mapper.treeToValue(jsonNode, JwtResponse.class);
		return response;
	}
}
