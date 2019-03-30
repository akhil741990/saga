package com.soul.saga.core.sec;

import com.soul.saga.core.SagaEvent;

public class SecResponseEvent extends SagaEvent {
	private ResponseStatus status;

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	
}
