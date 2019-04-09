package com.soul.saga.core.sec;


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
		responseProcesserPool.submit(new SecResponseProcessor(event));
	}
	public Coordinator(){
		transMgr = DistributedTransactionManager.getInstance();
		responseProcesserPool = Executors.newFixedThreadPool(10); // TODO : make this configurable
		roolbackHandlerPool = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
		          transMgr.getRollBackQ());
	}


}
