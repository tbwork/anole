package org.tbwork.anole.subscriber.core.impl;
 
import org.tbwork.anole.loader.core.loader.impl.AnoleFileLoader;
import org.tbwork.anole.loader.util.ProjectUtil; 
import org.tbwork.anole.subscriber.core.AnoleClient; 

public class AnoleSubscriberFileSystemLoader extends AnoleFileLoader{

	 public AnoleSubscriberFileSystemLoader(){
		 super(SubscriberConfigManager.getInstance());
	 }
 
}
