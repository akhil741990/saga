package com.soul.saga.db.discovery;

import java.util.HashMap;
import java.util.Map;

public class DiscoveryService {

	private static DiscoveryService service;
	private Map<String,String> dbMap;
	private DiscoveryService(){
		dbMap = new HashMap<>();
	}
	public static DiscoveryService getInstance(){
		if(service == null){ // TODO : make it thread safe;
			service = new DiscoveryService();
		}
		return service;
	}
	
	public static void register(String dbName, String ipAddress){
		getInstance().dbMap.put(dbName, ipAddress);
	}
	
	public String getIpAddress(String dbName){
		return getInstance().dbMap.get(dbName);
	}
}
