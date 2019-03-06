package com.soul.saga.init.kafka.client;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;

import com.soul.saga.db.Action;
import com.soul.saga.db.DbEventMsg;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;

public class DbEventProducer {
	private static String KAFKA_MACHINE_IP = "192.168.1.102";

	private static Producer<Long, DbEventMsg> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_MACHINE_IP+":9092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "AvroProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                LongSerializer.class.getName());
        // Configure the KafkaAvroSerializer.
       props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                KafkaAvroSerializer.class.getName());
        // Schema Registry location.
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG,
                "http://"+KAFKA_MACHINE_IP+":8081");
        return new KafkaProducer<>(props);
    }
	
	public static void main(String args[]){
		System.out.println("DbEventProducer Started....");
		Producer<Long, DbEventMsg> p = createProducer();
		DbEventMsg msg = new DbEventMsg();
		msg.setMsgId("1");
		msg.setQuery("select * from table");
		msg.setAction(Action.COMMIT);
		p.send(new ProducerRecord<Long, DbEventMsg>("db-queue", msg));
		p.flush();
		p.close();
	}
}
