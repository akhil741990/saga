package com.soul.saga.core.sec;


import com.soul.saga.core.SagaEventListener;

public class Coordinator implements SagaEventListener<SecResponseEvent>{

	@Override
	public void handle(SecResponseEvent event) {
		// TODO Auto-generated method stub
		if(event.getStatus().equals(ResponseStatus.PASS)){
			
		}
	}


}
