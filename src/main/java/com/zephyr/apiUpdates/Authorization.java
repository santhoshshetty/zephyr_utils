package com.zephyr.apiUpdates;

import java.net.URI;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;
import com.thed.zephyr.cloud.rest.client.impl.JwtGeneratorImpl;
import com.thed.zephyr.cloud.rest.model.ZConfig;

public class Authorization {

	public String generateToken(String zephyrURL, String accessKey, String secretKey, String accountId, String endpoint, String methodType)
			throws Exception {
		String access = accessKey;
		String secret = secretKey;
		String account = accountId;
		String zurl = zephyrURL;
		ZConfig zConfig = new ZConfig(access, secret, account, zurl);
		JwtGenerator jwtGenerator = new JwtGeneratorImpl(zConfig);
		String url = zurl + endpoint;
		URI uri = new URI(url);
		String jwt = jwtGenerator.generateJWT(methodType, uri, 2500	);
		System.out.println("FINAL API : " + (new URI(url)).toString());
		System.out.println("JWT Token : " + jwt);
		return jwt;
	}
}
