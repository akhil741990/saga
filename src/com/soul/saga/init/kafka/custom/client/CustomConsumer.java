package com.soul.saga.init.kafka.custom.client;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.soul.saga.core.sec.DistributedTransactionTracker;
import com.soul.saga.db.DbEventMsg;


public class CustomConsumer {
	
	
	
	
	
private static String KAFKA_MACHINE_IP = "localhost";
	
	public static Consumer<String, DbEventMsg> createConsumer(String kafkaBrokerIP) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerIP+":9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleAvroConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
    
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "com.soul.saga.init.kafka.custom.client.DbEventMsgDeserializer");  //<----------------------
        
        //Schema registry location.
        
        return new KafkaConsumer<>(props);
    }

	
	public static Consumer<String, DistributedTransactionTracker> createConsumerForCoordinator(String kafkaBrokerIP) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerIP+":9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleAvroConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
    
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "com.soul.saga.init.kafka.custom.client.DistributedTransactionTrackerDeserilaizer");  //<----------------------
        
        //Schema registry location.
        
        return new KafkaConsumer<>(props);
    }
	
	
	public static void main(String args[]){
		System.out.println("DbEventConsumer started...");
		Consumer<String, DbEventMsg> consumer = createConsumer("localhost");
		consumer.subscribe(Collections.singletonList("queue_db1"));
		while (true) {
	        ConsumerRecords<String, DbEventMsg> messages = consumer.poll(100);
	        for (ConsumerRecord<String, DbEventMsg> message : messages) {
	          try{	
	          System.out.println("Message received " + message.value().toString());
	          }catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
	        }
	    }
	}
}
