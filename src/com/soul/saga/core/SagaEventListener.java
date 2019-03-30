package com.soul.saga.core;

public interface SagaEventListener<T extends SagaEvent> {

	public void handle(T event);
}
