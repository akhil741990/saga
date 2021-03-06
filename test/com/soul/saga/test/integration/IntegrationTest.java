package com.soul.saga.test.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.soul.saga.core.sec.Coordinator;
import com.soul.saga.core.sec.DistributedTransactionTracker;
import com.soul.saga.db.DistributedQuery;
import com.soul.saga.init.kafka.custom.client.CustomProducer;

public class IntegrationTest {

	
	public static void main(String args[]) throws IOException{
		

		DistributedTransactionTracker trk = new DistributedTransactionTracker();
		DistributedQuery dQuery = new DistributedQuery("DB-1", "Query-1", "comp-Query1");
		DistributedQuery dQuery2 = new DistributedQuery("DB-2", "Query-2", "comp-Query2");
		trk.addQueryToList(dQuery);
		trk.addQueryToList(dQuery2);
		Coordinator cordinator = new Coordinator("localhost","co-ordinator");
		UUID transactionId = trk.getId();
		
		Producer<String, DistributedTransactionTracker> p = CustomProducer.createProducerForCoordinator("localhost");
		System.out.println("Send Saga Transaction to Co-ordinator , transactionId : " + transactionId);
		p.send(new ProducerRecord<String, DistributedTransactionTracker>("co-ordinator", trk));
		p.flush();
		
		//cordinator.submitSaga(trk);
		ResponseSimulator sim = new ResponseSimulator(cordinator);		
		BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in)); 
		
		while(true){
			System.out.println("SecResponseSimulator:Please enter the response type "
					+ "\n 1) Possitive Response : Y \n"
					+ "2) Negtaive Response : N \n"
					+ "3) Exit the test : E"
					);
			String name = reader.readLine();
			if("Y".equals(name)){
				sim.sendPositiveResponse(transactionId);
			}else if("N".equals(name)){
				sim.sendNegativeResponse(transactionId);
			}else if ("E".equals(name)){
				break;
			}else {
				System.out.println("Invalid Input");
			}
		}
		
		
		
	}
}
