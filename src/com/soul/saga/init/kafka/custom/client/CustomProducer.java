package com.soul.saga.init.kafka.custom.client;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.soul.saga.core.sec.DistributedTransactionTracker;
import com.soul.saga.db.Action;
import com.soul.saga.db.DbEventMsg;


public class CustomProducer {
	private static String KAFKA_MACHINE_IP = "localhost";
	private static Producer<String, DbEventMsg> createProducer(String kafkaBrokerIP) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerIP+":9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "AvroProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        // Configure the KafkaAvroSerializer.
       props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
               "com.soul.saga.init.kafka.custom.client.DbEventMsgSerializer");
        // Schema Registry location.
       
        return new KafkaProducer<>(props);
    }
	

	public static Producer<String, DistributedTransactionTracker> createProducerForCoordinator(String kafkaBrokerIP) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokerIP+":9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "AvroProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        // Configure the KafkaAvroSerializer.
       props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
               "com.soul.saga.init.kafka.custom.client.DistributedTransactionTrackerSerilaizer");
        // Schema Registry location.
       
        return new KafkaProducer<>(props);
    }
	
	
	
//	public static void main(String args[]){
//		Producer<String,DbEventMsg> producer = createProducer("localhost");
//		DbEventMsg msg = new DbEventMsg();
//		msg.setMsgId("1");
//		msg.setQuery("select * from table");
//		msg.setAction(Action.COMMIT);
//		 producer.send(new ProducerRecord<String, DbEventMsg>("queue_db1", msg));
//		   System.out.println("Message " + msg.toString() + " sent !!");
//	   producer.flush();
//	}

	public static void main(String args[]){
		Producer<String,DistributedTransactionTracker> producer = createProducerForCoordinator("localhost");
		DistributedTransactionTracker msg = new DistributedTransactionTracker();
		
		 producer.send(new ProducerRecord<String, DistributedTransactionTracker>("co-ordinator", msg));
		   System.out.println("Message " + msg.toString() + " sent !!");
	   producer.flush();
	}
}
