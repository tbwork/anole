package org.tbwork.anole.server.basic.util;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;
import java.util.UUID;

public class Md5Util {
	
   
    public static String md5(String inputString){
    	try{
    		Hasher hasher = Hashing.md5().newHasher();
        	hasher.putString(inputString,  Charset.forName("UTF-8")); 
        	return hasher.hash().toString();
    	}
    	catch(Exception e){
    		throw new RuntimeException(e.getMessage());
    	} 
    }
    
    public static String generateToken() {
    	UUID uuid = UUID.randomUUID();
    	return uuid.toString();
    }
    
  
    
}
