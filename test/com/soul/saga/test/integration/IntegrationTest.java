package com.soul.saga.test.integration;

import java.util.UUID;

import com.soul.saga.core.sec.Coordinator;
import com.soul.saga.core.sec.DistributedTransactionTracker;
import com.soul.saga.db.DistributedQuery;

public class IntegrationTest {

	
	public static void main(String args[]){
		

		DistributedTransactionTracker trk = new DistributedTransactionTracker();
		DistributedQuery dQuery = new DistributedQuery("DB-1", "Query-1", "comp-Query1");
		DistributedQuery dQuery2 = new DistributedQuery("DB-2", "Query-2", "comp-Query2");
		trk.addQueryToList(dQuery);
		Coordinator cordinator = new Coordinator();
		cordinator.submitSaga(UUID.randomUUID(), trk);
		SagaResponseListenerSimulator sim = new SagaResponseListenerSimulator(cordinator);
		sim.callback();
		
	}
}
