package com.zephyr.utils;

import static io.restassured.RestAssured.given;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Sample {
	public static void main(String[] args) {
	RestAssured.given().auth().basic("", "").when().get("").then().cookie("");
	//Cookie - small lpeiceo f information to check if the session is valid or not
	//JSessionID - session tracking
	
	JsonObject obj=new JsonObject();
	obj.addProperty("", "");
	RestAssured.given().header("Content-Type", ContentType.JSON).body(obj).post().getBody().jsonPath().get();
	
	
	}
}
