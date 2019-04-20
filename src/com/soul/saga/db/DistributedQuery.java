package com.soul.saga.db;

public class DistributedQuery {
	private final String dbName;
	private final String query;
	private final String compensatoryQuery;
	public DistributedQuery(String dbName, String query, String compensatoryQuery) {
		this.query = query;
		this.compensatoryQuery = compensatoryQuery;
		this.dbName = dbName;
				
	}
	public String getDbName() {
		return dbName;
	}
	public String getQuery() {
		return query;
	}	
	public String getCompensatoryQuery() {
		return compensatoryQuery;
	}
}
