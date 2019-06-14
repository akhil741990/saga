package com.soul.saga.test.integration;

import java.util.UUID;

import com.soul.saga.core.sec.Coordinator;
import com.soul.saga.core.sec.ResponseStatus;
import com.soul.saga.core.sec.SecResponseEvent;

public class ResponseSimulator {

	private Coordinator cor;
	public ResponseSimulator(Coordinator cor) {
		this.cor = cor;
	}

	
	public void sendPositiveResponse(UUID transactionId){
		SecResponseEvent e = new SecResponseEvent();
		e.setTransactionId(transactionId);
		e.setStatus(ResponseStatus.PASS);
		callback(e);
	}
	
	public void sendNegativeResponse(UUID transactionId){
		SecResponseEvent e = new SecResponseEvent();
		e.setTransactionId(transactionId);
		e.setStatus(ResponseStatus.FAIL);
		callback(e);
	}
	
	public void callback(SecResponseEvent event){
		this.cor.handle(event);
	}
}
