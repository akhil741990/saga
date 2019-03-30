package com.soul.saga.core.sec;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class DistributedTransactionManager {
	private Map<UUID,DistributedTransactionTracker> transactions;
	private LinkedBlockingQueue<Runnable> rollbackQ ;
	private static DistributedTransactionManager instance;
	private DistributedTransactionManager(){
		transactions = new ConcurrentHashMap<>();
		rollbackQ = new LinkedBlockingQueue<>();
	}
	public static DistributedTransactionManager getInstance(){
		if(instance == null){
			instance = new DistributedTransactionManager();
		}
		return instance;
	}
	public void addTransactionTracker(UUID id, DistributedTransactionTracker tracker){
		this.transactions.put(id, tracker);
	}
	public DistributedTransactionTracker getTransactionTracker(UUID id){
		return this.transactions.get(id);
	}
	public LinkedBlockingQueue<Runnable> getRollBackQ(){
		return this.instance.rollbackQ;
	}
}
