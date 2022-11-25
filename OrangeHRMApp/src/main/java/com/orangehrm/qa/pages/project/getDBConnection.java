package com.orangehrm.qa.pages.project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.orangehrm.qa.base.Base;
import com.orangehrm.qa.utils.TestUtil;

import oracle.jdbc.driver.OracleDriver;

public class getDBConnection extends Base{

	private static String dbURL, userName, password;

	public static Connection createConnection() {

		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Unable to locate driver "+e.getMessage());
		}

		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			System.out.println("Could not load the driver "+e.getMessage());
		}

		System.out.println("Driver loaded successfully!");

		/*
			Properties prop = new Properties();

			try {
				prop.load(new FileInputStream("E:\\Softwares\\Java_projects\\OrangeHRMApp\\src\\main\\resources\\Config\\config.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Could not load the config file "+e.getMessage());
			}*/

		dbURL = prop.getProperty("dbURL");
		userName = prop.getProperty("dbUN");
		password = prop.getProperty("dbpwd");

		try {
			connection = DriverManager.getConnection(dbURL, userName, password);
			System.out.println("Connected with connection");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;		
	}

	public static ArrayList<String> retriveData(Connection con, String query){

		ArrayList<String> result = new ArrayList<String>();

		try {
			Statement stmt = con.createStatement();
			try {
				ResultSet rs = stmt.executeQuery(query);
				try {
					while(rs.next()) {
						result.add(rs.getString(1));
					}
				}

				catch (SQLException e) {
					System.out.println("Unable to retrive data from the database. "+e.getMessage());
				}
				finally {
					try {
						rs.close();
					}
					catch(Exception ignore){						
					}
				}
			}
			catch (SQLException e) {
				System.out.println("Unable to execute SQL query. "+e.getMessage());
			}	
			finally {
				try {
					if(stmt!=null) {
						stmt.close();
					}
				}
				catch(Exception ignore){						
				}
			}
		} 

		catch (SQLException e) {
			System.out.println("Unable to create statement. "+e.getMessage());
		}

		return result;

	}

	public static List<GetObjectDetails> GetObjectDetails(String tableName1, String tableName2, String query, String scenarioName) {

		Statement stmt = null;
		Connection connect = null;
		List<GetObjectDetails> allObjData = new ArrayList<GetObjectDetails>();

		connect = getDBConnection.createConnection();
		try {
			stmt.getConnection().createStatement();
			System.out.println("Unable to create statement");						

			
			switch (scenarioName) {
			case TestUtil.QUERY1:
				ResultSet resultQuery1 = stmt.executeQuery(query);
				System.out.println("Inside switch case of query1");
				while(resultQuery1.next()) {
					GetObjectDetails objList = new GetObjectDetails();
					objList.setTable1(resultQuery1.getString(1));
					objList.setTable2(resultQuery1.getString(2));
					objList.setTable3(resultQuery1.getString(3));
					allObjData.add(objList);
				}break;

			case TestUtil.QUERY2:
				ResultSet resultQuery2 = stmt.executeQuery(query);
				System.out.println("Inside switch case of query2");
				while(resultQuery2.next()) {
					GetObjectDetails objList = new GetObjectDetails();
					objList.setTable1(resultQuery2.getString(1));
					objList.setTable2(resultQuery2.getString(2));
					objList.setTable3(resultQuery2.getString(3));
					allObjData.add(objList);
				}break;

			default:
				System.out.println("Switch statement is not defined properly");
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(stmt!=null) {
					stmt.close();
				}
			}
			catch(SQLException e){	
				e.printStackTrace();
			}
			try {
				if(connect!=null) {
					connect.close();
				}
			}
			catch(SQLException e){	
				e.printStackTrace();
			}
		}
		return allObjData;
	}


	public static ArrayList<String> retriveMultipleColumn(Connection con, String query){

		ArrayList<String> result = new ArrayList<String>();
		int num=1;
		try {
			Statement stmt = con.createStatement();
			try {
				ResultSet rs = stmt.executeQuery(query);

				try {
					ResultSetMetaData rsmd = rs.getMetaData();
					int column_count = rsmd.getColumnCount();
					while(rs.next()) {
						while(num<=column_count) {
							result.add(rs.getString(num));
							num++;
						}
					}
				}
				catch (SQLException e) {
					System.out.println("Unable to retrive data from DB. "+ e.getMessage());
				}
				finally {
					try {
						rs.close();
					}catch(Exception ignore) {

					}
				}
			}
			catch (SQLException e) {
				System.out.println("Unable to execute query. "+ e.getMessage());
			}
			finally {
				try {
					stmt.close();
				}catch(Exception ignore) {

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;		
	}

	public static int getResultCount(Connection con, String query) {

		int count = 0;

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next()) {
				count = rs.getInt(1);
				System.out.println("Count: "+count);
			}
		} catch (SQLException e) {
			System.out.println("Unable to get the result count from DB. "+e.getMessage());
		}

		return count;		
	}

	public static int getRowCount(Connection con, String query) {

		int count = 0;

		try {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(query);

			rs.last();
			count = rs.getRow();
			rs.beforeFirst();
		} catch (SQLException e) {
			System.out.println("Unable to get the result count from DB. "+e.getMessage());
		}

		return count;		
	}
}
