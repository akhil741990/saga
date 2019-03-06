package com.soul.saga.db;

public class DbEventMsg {
	
	// To uniquely for tracking the message-response flow
	private String msgId;
	private String query;
	private  Action action;
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append("DbEventMsg").append("{msgId:").append(msgId)
		 .append(",query:").append(query)
		 .append(",action:").append(action).append("}");
		return s.toString();
	}
	
}
