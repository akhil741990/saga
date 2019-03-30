package com.soul.saga.core;

import java.util.UUID;

public abstract class SagaEvent {
	private UUID transactionId;
	private long timestamp;
	public SagaEvent(){
		this.timestamp = System.currentTimeMillis();
	}
	public UUID getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
