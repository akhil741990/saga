package com.soul.saga.core.sec;


import java.util.UUID;

import com.soul.saga.core.SagaEvent;
import com.soul.saga.db.DistributedQuery;

//TODO : shd we make this Callable so that we can track the final rollbback status ? 
public class RollbackHandler  implements Runnable{

	private SagaEvent event;

	public RollbackHandler(SagaEvent event) {
		this.event = event;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		UUID transId = event.getTransactionId();
		DistributedTransactionTracker tracker = DistributedTransactionManager.getInstance().getTransactionTracker(transId);
		for(int i = tracker.getLastQueryExecuted() -1 ; i>=0 ;i --){
			/* send the rollback query on dedicated rollbackQ so that their execcution
			 * is not delayed , which would be the case if the same Q is used for rollback and normal queries 
			 */
			
		}
	}
	
	private String generatedRollBackQuery(DistributedQuery query){
		return null;
	}

}
