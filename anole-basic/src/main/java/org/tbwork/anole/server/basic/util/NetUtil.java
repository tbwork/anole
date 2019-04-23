package org.tbwork.anole.server.basic.util;

import org.tbwork.anole.loader.context.Anole;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;


public class NetUtil {  

	private static final int start_port = 11300;
	 
	public static String getLocalAddress() {
		try {
			return getLocalHostLANAddress().getHostAddress();
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public static int getPort(String propertyName, Integer defaultPort){
    	int result = 0;
    	try{ 
    		Integer port =  Anole.getIntProperty(propertyName);
    		if(port == null || port ==0) {
    			port = defaultPort;
    		}
    		if(isPortUsing(port)) {
				throw new RuntimeException("The port " + port + " is already occupied." );
			}
    		return result;
    	}
    	catch(Exception e){
    		throw new RuntimeException ("Wrong format for the configuration '"+propertyName+"'");
    	}  	 
}
	
	public static int getPort(String propertyName){
	    	int result = 0;
	    	try{ 
	    		Integer port =  Anole.getIntProperty(propertyName);
	    		if(port == null || port ==0) {
	    			port = getOneValidPort();
	    		}
	    		else {
	    			if(isPortUsing(port)) {
	    				throw new RuntimeException("The port " + port + " is already occupied." );
	    			}
	    		}
	    		return result;
	    	}
	    	catch(Exception e){
	    		throw new RuntimeException ("Wrong format for the configuration '"+propertyName+"'");
	    	}  	 
	}
 
	
	private static InetAddress getLocalHostLANAddress() throws Exception {
	    try {
	        InetAddress candidateAddress = null; 
	        for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
	            NetworkInterface iface = (NetworkInterface) ifaces.nextElement();  
	            for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
	                InetAddress inetAddr = (InetAddress) inetAddrs.nextElement(); 
	                if (!inetAddr.isLoopbackAddress()) { 
	                    if (inetAddr.isSiteLocalAddress()) {  
	                        return inetAddr;
	                    } else if (candidateAddress == null) { 
	                        candidateAddress = inetAddr;
	                    }
	                } 
	            } 
	        }
	        if (candidateAddress != null) {
	            return candidateAddress;
	        } 
	        InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
	        return jdkSuppliedAddress;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getLocalAddress());
	}
	
	
    public static boolean isPortUsing(int port){ 
        boolean flag = false;   
        try {  
        	InetAddress theAddress = InetAddress.getByName("127.0.0.1");  
            Socket socket = new Socket(theAddress,port);  
            flag = true;
            socket.close();
        }
        catch (UnknownHostException e){
        	
        }
        catch (IOException e) {  
              
        }  
        return flag;  
   }  
	 
   public static int getOneValidPort(){
		for(int i = start_port; i < 65535; i++){
			if(!isPortUsing(i)) 
				return i;
		}
		throw new RuntimeException("No available port!");
   }
}
