package com.soul.saga.core.sec;


import java.util.ArrayList;

import com.soul.saga.db.DistributedQuery;

public class DistributedTransactionTracker {
	/*
	 * This array list contains the ordered list of queries to
	 * be executed on respective Distributed Services
	 */
	private ArrayList<DistributedQuery> queryList ;
	public ArrayList<DistributedQuery> getqList() {
		return queryList;
	}
	public void setqList(ArrayList<DistributedQuery> queryList) {
		this.queryList = queryList;
	}
	public int getLastQueryExecuted() {
		return lastQueryExecuted;
	}
	public void setLastQueryExecuted(int lastQueryExecuted) {
		this.lastQueryExecuted = lastQueryExecuted;
	}
	int lastQueryExecuted;
	/*
	 * On each successfully reply from the respective service lastQueryExecuted will be incremented
	 * to execute the next query in the list;
	 * On error in query execution , rollback message will be sent to all the service prior in the list 
	 * i.e from (0 to lastQueryExecuted-1)
	 */
	public int incrementQueryIndex(){
		return ++lastQueryExecuted;
	}
}
