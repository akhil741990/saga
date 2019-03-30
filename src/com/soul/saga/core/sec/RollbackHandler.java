package com.soul.saga.core.sec;


import java.util.UUID;

import com.soul.saga.core.SagaEvent;

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
		
	}

}
