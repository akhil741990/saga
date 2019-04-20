package com.soul.saga.test.local.db;

import com.soul.saga.db.local.MySqlDatabase;

public class MySqlDbConnectorTest {

	public static void main(String args[]){
		MySqlDatabase db = MySqlDatabase.getInstance();
		db.init();
		
		insertTest(db);
		updateTest(db);
		
	}
	
	public  static void insertTest(MySqlDatabase db){
		int i = db.executeInsertOrUpdateQuery("insert into customer values (?,?,?,?)", 1,"Tommy",34,"Goa");
		if(i > 0){
			System.out.println("Record inserted successfully");
			System.out.println("Affected rows :"+i);
		}
	}

	
	public  static void updateTest(MySqlDatabase db){
		int i = db.executeInsertOrUpdateQuery("update customer set name = ? where id = ?", "Jimmy",1);
		if(i > 0){
			System.out.println("Record inserted successfully");
			System.out.println("Affected rows :"+i);
		}
	}
}
