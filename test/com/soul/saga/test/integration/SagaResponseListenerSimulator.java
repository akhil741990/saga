package com.soul.saga.test.integration;

import com.soul.saga.core.sec.Coordinator;
import com.soul.saga.core.sec.SecResponseEvent;

public class SagaResponseListenerSimulator {

	private Coordinator cor;
	public SagaResponseListenerSimulator(Coordinator cor) {
		this.cor = cor;
	}
	
	public void callback(){
		SecResponseEvent event = new SecResponseEvent();
		this.cor.handle(event);
	}
}
