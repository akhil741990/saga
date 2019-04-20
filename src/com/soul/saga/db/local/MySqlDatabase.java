package com.soul.saga.db.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.apache.commons.dbcp.BasicDataSource;


public class MySqlDatabase implements Database{
	
	
	private static MySqlDatabase instance = new MySqlDatabase();
	private BasicDataSource dataSource;
	//private DBJobExecutor dbJobExecutor;
	protected MySqlDatabase() {
		
	}
	// instance is initialized at at Class Load,
	// so no need to gaurd instance from the race of initialization from multiple threads 
	public static MySqlDatabase getInstance(){
		return instance;
	}
	
	public void init(){
		
		this.dataSource = new BasicDataSource();
		
		//TODO :  makes these values configurable via INI/ Properties file
		this.dataSource.setUrl("jdbc:mysql://localhost:3306/ponkala");
		this.dataSource.setUsername("root");
		this.dataSource.setPassword("password");
		this.dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		this.dataSource.setInitialSize(3);
		this.dataSource.setMaxActive(10);
		//this.dbJobExecutor = new DBJobExecutor();
	}
	
	private Connection getConnection() throws Exception {
			return this.dataSource.getConnection();
	}

	
	public int executeInsertOrUpdateQuery(String query, Object... param){
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = getConnection();
			ps = DatabaseOps.createPreparedStatement(con,query, param);
			//rowsAffected = this.dbJobExecutor.submit(dbJob);
		    return ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally {
			DatabaseOps.closeConnection(con, ps);
		}
	}
	
}
