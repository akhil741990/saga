package com.soul.saga.core.sec;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.soul.saga.core.SagaEventListener;

public class Coordinator implements SagaEventListener<SecResponseEvent>{

	private DistributedTransactionManager transMgr;
	private ExecutorService responseProcesserPool; 
	private ExecutorService roolbackHandlerPool ; 
	@Override
	public void handle(SecResponseEvent event) {
		// TODO Auto-generated method stub
	}
	public Coordinator(){
		transMgr = DistributedTransactionManager.getInstance();
		responseProcesserPool = Executors.newFixedThreadPool(10); // TODO : make this configurable
		roolbackHandlerPool = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
		          transMgr.getRollBackQ());
	}


}
