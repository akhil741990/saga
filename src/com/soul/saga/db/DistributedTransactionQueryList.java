package com.soul.saga.db;

import java.util.ArrayList;

/*
 * This class will be used to prepare the ordered list of 
 * queries to be executed on the distributed DB machines
 */
public class DistributedTransactionQueryList {

	private ArrayList<DistributedQuery>queryList ;
	
	DistributedTransactionQueryList(){
		queryList = new ArrayList<>();
	}
	
	public void addQuery(String dbName, String query){
		queryList.add(new DistributedQuery(dbName, query));
	}
	
}
