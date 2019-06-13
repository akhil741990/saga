package com.soul.saga.init.kafka.custom.client;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.codehaus.jackson.map.ObjectMapper;

import com.soul.saga.core.sec.DistributedTransactionTracker;

public class DistributedTransactionTrackerDeserilaizer implements Deserializer<DistributedTransactionTracker>{

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DistributedTransactionTracker deserialize(String topic, byte[] data) {
		DistributedTransactionTracker deserializedMsg = null;
		ObjectMapper mapper  = new ObjectMapper();
		try {
			deserializedMsg = mapper.readValue(data, DistributedTransactionTracker.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deserializedMsg;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
