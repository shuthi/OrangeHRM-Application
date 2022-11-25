package com.orangehrm.qa.pages.project;

public class GetObjectDetails {

	@Override
	public String toString() {
		return "GetObjectDetails [table1=" + table1 + ", table2=" + table2 + ", table3=" + table3 + "]";
	}
	
	String table1;
	String table2;
	String table3;
	
	//getters and setters
	public String getTable1() {
		return table1;
	}
	public void setTable1 (String table1) {
		this.table1 = table1;
	}
	public String getTable2() {
		return table2;
	}
	public void setTable2(String table2) {
		this.table2 = table2;
	}
	public String getTable3() {
		return table3;
	}
	public void setTable3(String table3) {
		this.table3 = table3;
	}

}
