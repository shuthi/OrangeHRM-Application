package com.orangehrm.qa.pages.project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.orangehrm.qa.base.Base;

public class ReadLinuxData {
	
	static String host = "";
	static String user = "";
	static String pwd = "";
	static String s1 = "";
	
	public static StringBuilder puttyConnection() throws JSchException, IOException {
		
			Session session = new JSch().getSession(user, host, 22);
			session.setPassword(pwd);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			System.out.println("Session is connected");
			Channel channel = session.openChannel("shell");
			OutputStream ops = channel.getOutputStream();
			PrintStream ps = new PrintStream(ops, true, "UTF-8");
			channel.connect();
			
			ps.println("docker exec -it dxfpoc /bin/sh");
			ps.println("cd /data/dxfjson");
			ps.println("ls -la --time-style=+\\{%d-%m-%Y::%H:%M\\}");
			ps.println("exit");
			ps.close();
			
			File file = new File("E:\\Softwares\\Java_projects\\OrangeHRMApp\\src\\main\\resources\\Config\\puttyData.txt");
			PrintStream stream = new PrintStream(file);
			System.out.println("From now on "+file.getAbsolutePath()+" will be the console");
			System.setOut(stream);			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(channel.getInputStream()));
			String line = null;
			StringBuilder output = new StringBuilder();
			List<String> list = new ArrayList<String>();
			String exchanges ="";
			
			while((line = reader.readLine())!=null) {
				if(line.startsWith("-rw")==true) {
					line = line.lines().findAny().get();
					output.append(line+"\n");
				}
				exchanges = output.toString();
				list.add(exchanges);
				if(list.size()>=123) {
					System.out.println(exchanges);
					list.clear();
					break;
				}
			}
			try {
			System.out.println("TRY BLOCK");
			session.disconnect();
			channel.disconnect();
			System.out.println("Session is disconnected");
			}
			catch(Exception e) {
				System.out.println("Exception "+e.getMessage());				
			}				
			
		return  output;
	}

}
