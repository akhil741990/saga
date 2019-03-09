package com.soul.saga.init.kafka.custom.client;

import java.io.IOException;
import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.codehaus.jackson.map.ObjectMapper;

import com.soul.saga.db.DbEventMsg;

public class DbEventMsgSerializer implements Serializer<DbEventMsg>{

	@Override
	public void configure(Map<String, ?> var1, boolean var2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] serialize(String var1, DbEventMsg msg) {
		
		byte [] serializedMsg = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValueAsString(msg).getBytes();
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
