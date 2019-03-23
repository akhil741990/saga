package com.soul.saga.db;

public class DistributedQuery {
	private final String dbName;
	private final String query;
	public DistributedQuery(String dbName, String query) {
		this.query = query;
		this.dbName = dbName;
				
	}
	public String getDbName() {
		return dbName;
	}
	public String getQuery() {
		return query;
	}	
}
