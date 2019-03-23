package com.soul.saga.core;

import java.util.HashMap;
import java.util.Map;

public class DbRegistrar {

	private static Map<String,String>  qMap;
	static {
		qMap = new HashMap();
	}
	public static void  registerToSagaQ(String dbName, String qName){
		qMap.put(dbName, qName);
	}
	
	public static String  getQName(String dbName){
		return qMap.get(dbName);
	}
}

