package com.orangehrm.qa.pages.project;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aventstack.extentreports.Status;
import com.orangehrm.qa.base.Base;

public class APIComponents {

	public static JSONObject getFloorDetails(JSONObject response) {

		JSONArray child = response.getJSONArray("children");
		JSONObject floor = child.getJSONObject(0);

		return floor;		
	}

	public static JSONArray getOverallRoomDetails(JSONObject floor) {

		JSONArray room = floor.getJSONArray("children");
		return null;
	}

	public static JSONObject getRoomDetails(JSONArray room, int r) {
		
		JSONObject roomObject = room.getJSONObject(r); //room{}
		if(roomObject.get("nodeType").equals("Room")) {
			String id = roomObject.getString("id");
			String code = roomObject.getString("code");
			System.out.println("Room id - "+id+" Room Code - "+code);
		}
		
		return roomObject;	
	}
	
	public static ArrayList<JSONObject> getSectionDetails(JSONObject roomObject){
		ArrayList<JSONObject> sections = new ArrayList<JSONObject>();
		JSONArray roomChildren = roomObject.getJSONArray("children");
		
		if(roomChildren.length()!=0) {
			for(int j=0; j< roomChildren.length();j++) {
				sections.add(roomChildren.getJSONObject(j));
			}
		}else {
			Base.logReport(Status.INFO, "Room is empty");
		}
		return sections;		
	}
	
	public static JSONArray getSuiteArray(JSONObject sectionObject) {
		JSONArray suite = sectionObject.getJSONArray("children");
		return suite;
	}
	
	public static JSONObject getSuiteObject(JSONArray suite, int sec) {
		JSONObject suiteObject = suite.getJSONObject(sec);
		return suiteObject;
	}
	
	public static ArrayList<JSONObject> getSuiteDetails(JSONObject sectionObject){
		ArrayList<JSONObject> suites = new ArrayList<JSONObject>();
		
		JSONArray suite = sectionObject.getJSONArray("children");
		for(int i=0;i<suite.length();i++) {
			suites.add(suite.getJSONObject(i));
		}
		return suites;
	}
	
	public static JSONArray getRackArray(JSONObject suiteObject) {
		JSONArray rack = suiteObject.getJSONArray("children");
		
		return rack;
	}
	
	public static JSONObject getRackObject(JSONArray rack, int rac) {
		JSONObject rackObject = rack.getJSONObject(rac);
		return rackObject;
	}
	
	public static ArrayList<JSONObject> getRackDetails(JSONObject suiteObject){ //returning contents of array
		ArrayList<JSONObject> racks = new ArrayList<JSONObject>();
		
		JSONArray rack = suiteObject.getJSONArray("children");
		for(int i=0;i<rack.length();i++) {
			racks.add(rack.getJSONObject(i));
		}
		return racks;
	}
}
