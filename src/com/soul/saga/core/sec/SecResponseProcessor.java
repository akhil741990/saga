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
		
		System.out.println("SecResponseProcessor :Processing msg :"+ event.getTransactionId());
		
		DistributedTransactionTracker tracker = DistributedTransactionManager.getInstance()
									.getTransactionTracker(event.getTransactionId());
		DistributedQuery query = tracker.nextQuery();
		if (query == null) {
			System.out.println("Saga Successful");
		}else{
			System.out.println("Next Query : "+ query.getQuery());
			QueryRouter.routeQueries(query);
		}
		
	}

}
