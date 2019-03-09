package com.soul.saga.init.kafka.custom.client;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.codehaus.jackson.map.ObjectMapper;

import com.soul.saga.db.DbEventMsg;

public class DbEventMsgDeserializer implements Deserializer<DbEventMsg>{

	@Override
	public void configure(Map<String, ?> var1, boolean var2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public DbEventMsg deserialize(String var1, byte[] msg) {
		
		DbEventMsg deserializedMsg = null;
		ObjectMapper mapper  = new ObjectMapper();
		try {
			deserializedMsg = mapper.readValue(msg, DbEventMsg.class);
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
