package com.soul.saga.core.sec;


import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.soul.saga.core.SagaEventListener;
/**
 * This class is the Saga Execution Co-ordinator
 *  It governs the execution of distributed  transaction 
 *  across services
 * @author akhil
 *
 */
public class Coordinator implements SagaEventListener<SecResponseEvent>{

	private DistributedTransactionManager transMgr;
	private ExecutorService responseProcesserPool; 
	private ExecutorService roolbackHandlerPool ; 
	@Override
	public void handle(SecResponseEvent event) {
		System.out.println("Processing msg :"+ event.getTransactionId());
		if(event.getStatus().equals(ResponseStatus.PASS)){
			responseProcesserPool.submit(new SecResponseProcessor(event));
		}else {
			roolbackHandlerPool.submit(new RollbackHandler(event));
		}
		
	}
	public Coordinator(){
		transMgr = DistributedTransactionManager.getInstance();
		responseProcesserPool = Executors.newFixedThreadPool(10); // TODO : make this configurable
		roolbackHandlerPool = Executors.newFixedThreadPool(10);
		
	}

	public void submitSaga(UUID id, DistributedTransactionTracker transaction){
		transMgr.addTransactionTracker(id, transaction);
		// start the transaction
		SecResponseEvent event = new SecResponseEvent();
		event.setTransactionId(id);
		event.setStatus(ResponseStatus.PASS);
		this.handle(event);
	}

}
