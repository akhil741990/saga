package com.soul.saga.init.kafka.custom.client;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.codehaus.jackson.map.ObjectMapper;

import com.soul.saga.core.sec.DistributedTransactionTracker;

public class DistributedTransactionTrackerSerilaizer implements Serializer<DistributedTransactionTracker>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String topic, DistributedTransactionTracker data) {
		byte [] serializedMsg = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			serializedMsg = mapper.writeValueAsString(data).getBytes();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serializedMsg;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
