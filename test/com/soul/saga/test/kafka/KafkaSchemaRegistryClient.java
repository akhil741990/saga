package com.soul.saga.test.kafka;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class KafkaSchemaRegistryClient {
	
	
	public static void main(String args[]) throws IOException{
		
		 final MediaType SCHEMA_CONTENT = MediaType.parse("application/vnd.schemaregistry.v1+json");
//		 final String DbEventMsgSchema = "{\n" +
//		            "    \\\"namespace\\\": \\\"com.soul.saga.db\\\"," +
//		            "    \\\"type\\\": \\\"record\\\"," +
//		            "    \\\"name\\\": \\\"DbEventMsg\\\"," +
//		            "    \\\"fields\\\": [" +
//		            "        {\\\"type\\\": \\\"enum\\\", \\\"name\\\": \\\"action\\\", \\\"symbols\\\" : [\\\"COMMIT\\\", \\\"ROLLBACK\\\"] }," +
//		            "        {\\\"name\\\": \\\"query\\\", \\\"type\\\": \\\"string\\\"}" +
//		            "    ]" +
//		            "  }";
		
		 
//		 final String DbEventMsgSchema = "{\n" +
//		            "  \"schema\": \"" +
//		            "  {" +
//		            "    \\\"namespace\\\": \\\"com.cloudurable.phonebook\\\"," +
//		            "    \\\"type\\\": \\\"record\\\"," +
//		            "    \\\"name\\\": \\\"Employee\\\"," +
//		            "    \\\"fields\\\": [" +
//		            "        {\\\"name\\\": \\\"fName\\\", \\\"type\\\": \\\"string\\\"}," +
//		            "        {\\\"name\\\": \\\"lName\\\", \\\"type\\\": \\\"string\\\"}," +
//		            "        {\\\"name\\\": \\\"age\\\",  \\\"type\\\": \\\"int\\\"}," +
//		            "        {\\\"name\\\": \\\"phoneNumber\\\",  \\\"type\\\": \\\"string\\\"}" +
//		            "    ]" +
//		            "  }\"" +
//		            "}";
		 
		 
		 final String DbEventMsgSchema =  new String(Files.readAllBytes(Paths.get("D:/Soul/saga/test/resources/schema.properties")));
		 
		 final OkHttpClient client = new OkHttpClient();
	        //POST A NEW SCHEMA
	     Request request = new Request.Builder()
	                .post(RequestBody.create(SCHEMA_CONTENT, DbEventMsgSchema))
	                .url("http://192.168.1.104:8081/subjects/db-event-msg/versions")
	                .build();
	     
	     String output = client.newCall(request).execute().body().string();
	        System.out.println(output);
		 
		 
	}

}
