package org.tbwork.anole.server.basic.util;

import java.nio.charset.Charset;
import java.util.UUID;

import org.tbwork.anole.loader.context.Anole;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

public class StringUtil { 
	
   
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
