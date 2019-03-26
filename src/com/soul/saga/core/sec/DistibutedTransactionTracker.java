package com.soul.saga.core.sec;


import com.soul.saga.db.DistributedTransactionQueryList;

public class DistibutedTransactionTracker {
	DistributedTransactionQueryList qList;
	int lastQueryExecuted;
	/*
	 * On each successfully reply from the respective service lastQueryExecuted will be incremented
	 * to execute the next query in the list;
	 * On error in query execution , rollback message will be sent to all the service prior in the list 
	 * i.e from (0 to lastQueryExecuted-1)
	 */
}
