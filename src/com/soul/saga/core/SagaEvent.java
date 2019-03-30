package com.soul.saga.core;

import java.util.UUID;

public abstract class SagaEvent {
	private UUID transactionId;
	private long timestamp;
	public SagaEvent(){
		this.timestamp = System.currentTimeMillis();
	}
}
