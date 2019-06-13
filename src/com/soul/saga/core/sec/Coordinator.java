package com.soul.saga.core.sec;


import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.soul.saga.core.SagaEventListener;
import com.soul.saga.db.DbEventMsg;
import com.soul.saga.init.kafka.custom.client.CustomConsumer;
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
	private Thread kafkaEventSubcriberThread;
	
	@Override
	public void handle(SecResponseEvent event) {
		System.out.println("Processing msg :"+ event.getTransactionId());
		if(event.getStatus().equals(ResponseStatus.PASS)){
			responseProcesserPool.submit(new SecResponseProcessor(event));
		}else {
			roolbackHandlerPool.submit(new RollbackHandler(event));
		}
		
	}
	public Coordinator(String kafkaIP, String kafkaTopic){
		transMgr = DistributedTransactionManager.getInstance();
		responseProcesserPool = Executors.newFixedThreadPool(10); // TODO : make this configurable
		roolbackHandlerPool = Executors.newFixedThreadPool(10);
		kafkaEventSubcriberThread = new Thread(new KafkaSubcriberThread(CustomConsumer.createConsumerForCoordinator(kafkaIP), kafkaTopic));
		kafkaEventSubcriberThread.start();
	}

	public void submitSaga(DistributedTransactionTracker transaction){
		transMgr.addTransactionTracker(transaction.getId(), transaction);
		// start the transaction
		SecResponseEvent event = new SecResponseEvent();
		event.setTransactionId(transaction.getId());
		event.setStatus(ResponseStatus.PASS);
		this.handle(event);
	}
	
	class KafkaSubcriberThread implements Runnable{
		Consumer<String,DistributedTransactionTracker> kafkaEventSubcriber;
		KafkaSubcriberThread(Consumer<String,DistributedTransactionTracker> kafkaEventSubcriber, String topic){
			this.kafkaEventSubcriber = kafkaEventSubcriber;
			kafkaEventSubcriber.subscribe(Collections.singletonList(topic));
		}
		@Override
		public void run() {
			System.out.println("Co-ordinator's consumer thtead started");
			while (true) {
		        ConsumerRecords<String, DistributedTransactionTracker> messages = kafkaEventSubcriber.poll(100);
		        for (ConsumerRecord<String, DistributedTransactionTracker> message : messages) {
		          try{	
		          System.out.println("Message received " + message.value().toString());
		          Coordinator.this.submitSaga(message.value());
		          }catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
		        }
		    }
			
		}
		
	}
}
