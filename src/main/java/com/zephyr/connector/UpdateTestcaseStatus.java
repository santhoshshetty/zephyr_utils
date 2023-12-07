package com.zephyr.connector;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.net.URI;
import java.net.URL;

import org.apache.http.client.utils.URIBuilder;

import com.thed.zephyr.cloud.rest.client.JwtGenerator;
import com.thed.zephyr.cloud.rest.client.impl.JwtGeneratorImpl;
import com.thed.zephyr.cloud.rest.model.ZConfig;

import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateTestcaseStatus {
	
	public static void main(String[] args) throws Exception {
		
		String cycleId = "12b75872-6dd7-4a44-80cc-c43be64f4686";
		
		String projectId = "24380";
		
		String versionId = "33665";
		
		String issueId = "392790";
		
		String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
		// zephyr accessKey , we can get from Addons >> zapi section
		String accessKey = "amlyYTo1NmYyNWFkNC1lN2RlLTRjM2YtYWRmOS04MmE2NmYxMWI1YTYgNWYyNDM0ZGQ2YzlkODIwMDIyYzI1YjM5IFVTRVJfREVGQVVMVF9OQU1F";
		// zephyr secretKey , we can get from Addons >> zapi section
		String secretKey = "zIEXqfiIw66fAryCON-CAxI6yNZtmokRPwj122zFPgg";
		// Jira accountId
		String accountId = "5f2434dd6c9d820022c25b39";
		ZConfig zConfig = new ZConfig(accessKey, secretKey, accountId, zephyrBaseUrl);
		
		JwtGenerator jwtGenerator = new JwtGeneratorImpl(zConfig);
		
		URIBuilder attachmentUri = new URIBuilder(zephyrBaseUrl.concat("/public/rest/api/1.0/attachment"));
		attachmentUri.addParameter("issueId", issueId);
		attachmentUri.addParameter("versionId", versionId);
		attachmentUri.addParameter("entityName", "execution");
		attachmentUri.addParameter("cycleId", cycleId);
		attachmentUri.addParameter("entityId", "c416fc49-8410-4862-856c-0c9263c4c92a");
		attachmentUri.addParameter("comment", "Success");
		attachmentUri.addParameter("projectId", projectId);
		
		URL ZapiUrl = attachmentUri.build().toURL();
		
		String url = ZapiUrl.toString();
		
		URI uri = new URI(url);
		
		String  jwt = jwtGenerator.generateJWT("POST", uri, 800);
		
		/*
		 * MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		 * builder.setContentType(org.apache.http.entity.ContentType.MULTIPART_FORM_DATA
		 * ); builder.addPart("fileExtension", new StringBody("html",
		 * org.apache.http.entity.ContentType.MULTIPART_FORM_DATA));
		 */
		
		
		File file = new File("C:\\Users\\skumargoureshetty\\Documents\\download.png");
		RequestSpecification request1 = given().header("Authorization", jwt).header("zapiAccessKey", accessKey)				
				.multiPart(file.getName(),file,"multipart/form-data")
				.when();
		
		Response response = request1.relaxedHTTPSValidation().post(url);
		
		System.out.println(response.getBody().asString());
		
		System.out.println("execution is completed");
	}
}