package com.soul.saga.core.sec;

import com.soul.saga.core.QueryRouter;
import com.soul.saga.db.DistributedQuery;


public class SecResponseProcessor implements Runnable {

	private SecResponseEvent event;
	public SecResponseProcessor(SecResponseEvent event) {
		this.event = event;
	}
	
	@Override
	public void run() {
		if(event.getStatus().equals(ResponseStatus.PASS)){
			DistributedTransactionTracker tracker = DistributedTransactionManager.getInstance()
										.getTransactionTracker(event.getTransactionId());
			DistributedQuery query = tracker.getNextQuery();
			QueryRouter.routeQueries(query);
		}else{
			try {
				DistributedTransactionManager.getInstance().getRollBackQ().put((new RollbackHandler(event)));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
