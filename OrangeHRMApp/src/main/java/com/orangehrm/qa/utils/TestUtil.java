package com.orangehrm.qa.utils;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class TestUtil {

	public static long PAGE_LOAD_TIME_OUT = 10;
	public static long IMPLICITLY_WAIT = 20;
	
	public static final String QUERY1 = "query1";
	public static final String QUERY2 = "query2";
	
	
	public static String encryptDecryptData() {
		Encoder encoder = Base64.getEncoder();
		String db = "Oracle123";
		String encodedString = encoder.encodeToString(db.getBytes());
		
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode(encodedString);
		String decryptVal = new String(bytes);
		
		return decryptVal;
		
	}
}
