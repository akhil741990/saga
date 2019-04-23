package com.soul.saga.core.sec;


import java.util.ArrayList;

import com.soul.saga.db.DistributedQuery;

public class DistributedTransactionTracker {
	/*
	 * This array list contains the ordered list of queries to
	 * be executed on respective Distributed Services
	 * 
	 * On each successfully reply from the respective service lastQueryExecuted will be incremented
	 * to execute the next query in the list;
	 * On error in query execution , rollback message will be sent to all the service prior in the list 
	 * i.e from (0 to lastQueryExecuted-1)
	 *
	 */
	private final ArrayList<DistributedQuery> queryList ;
	int lastQueryExecuted;
	
	public DistributedTransactionTracker(){
		this.lastQueryExecuted = -1;
		this.queryList = new ArrayList<>();
	}
	
	public void addQueryToList(DistributedQuery query){
		this.queryList.add(query);
	}
	
	public ArrayList<DistributedQuery> getqList() {
		return queryList;
	}
	
	public int getLastQueryExecuted() {
		return lastQueryExecuted;
	}
	
	private int incrementQueryIndex(){
		if(lastQueryExecuted == queryList.size()){
			return -1;
		}
		return ++lastQueryExecuted;
	}
	public DistributedQuery getNextQuery(){	
		int index = incrementQueryIndex();
		if (index == -1) {
			return null;
		}
		return this.queryList.get(index);
	}
}
