package com.orangehrm.qa.pages.project;

import java.util.Map;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.orangehrm.qa.base.Base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIInvoker extends Base{

	public static Response putMethod(String updatedUserJSON, String uri, Map<String, String> headers) {

		Base.logReport(Status.INFO, MarkupHelper.createLabel("PUT METHOD", ExtentColor.BLUE));
		Base.logReport(Status.INFO, "URL: "+uri);
		Base.logReport(Status.INFO, MarkupHelper.createCodeBlock(updatedUserJSON, CodeLanguage.JSON));
		Base.logReport(Status.INFO, "Headers: "+headers);

		Response response = RestAssured.given().contentType(ContentType.JSON).headers(headers).body(updatedUserJSON).put(uri);

		return response;
	}

	public static Response putMethod(String uri, Map<String, String> headers) {

		Base.logReport(Status.INFO, MarkupHelper.createLabel("PUT METHOD", ExtentColor.BLUE));
		Base.logReport(Status.INFO, "URL: "+uri);
		Base.logReport(Status.INFO, "Headers: "+headers);

		Response response = RestAssured.given().headers(headers).put(uri);
		Base.logReport(Status.INFO, MarkupHelper.createLabel("PUT Response", ExtentColor.GREEN));
		
		Base.logReport(Status.INFO, "Response Status Code: "+response.getStatusCode());
		Base.logReport(Status.INFO, "Response Body");
		Base.logReport(Status.INFO, MarkupHelper.createCodeBlock(response.body().asString()));

		return response;
	}
	
	public static Response getMethod(String uri, Map<String, String> headers) {

		Base.logReport(Status.INFO, MarkupHelper.createLabel("GET METHOD", ExtentColor.BLUE));
		Base.logReport(Status.INFO, "URL: "+uri);
		Base.logReport(Status.INFO, MarkupHelper.createOrderedList(headers));

		Response response = RestAssured.given().contentType(ContentType.JSON).headers(headers).get(uri);
		
		Base.logReport(Status.INFO, MarkupHelper.createLabel("GET Response", ExtentColor.GREEN));	
		Base.logReport(Status.INFO, "Response Status Code: "+response.getStatusCode());

		return response;
	}
	
	public static Response postMethod(String updatedUserJSON, String uri, Map<String, String> headers) {

		Base.logReport(Status.INFO, MarkupHelper.createLabel("POST METHOD", ExtentColor.BLUE));
		Base.logReport(Status.INFO, "URL: "+uri);
		Base.logReport(Status.INFO, MarkupHelper.createCodeBlock(updatedUserJSON, CodeLanguage.JSON));
		Base.logReport(Status.INFO, MarkupHelper.createOrderedList(headers));

		Response response = RestAssured.given().contentType(ContentType.JSON).headers(headers).body(updatedUserJSON).post(uri);
		
		Base.logReport(Status.INFO, MarkupHelper.createLabel("POST Response", ExtentColor.GREEN));	
		Base.logReport(Status.INFO, "Response Status Code: "+response.getStatusCode());
		Base.logReport(Status.INFO, "Response Body");
		Base.logReport(Status.INFO, MarkupHelper.createCodeBlock(response.body().asString()));

		return response;
	}

}
