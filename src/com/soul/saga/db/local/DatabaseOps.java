package com.soul.saga.db.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DatabaseOps {

	public static PreparedStatement createPreparedStatement(Connection con, String sql, Object... param)
			throws Exception {

		try {

			PreparedStatement ps = con.prepareStatement(sql);

			for (int i = 0; i < param.length; i++) {
				ps.setObject((i + 1), param[i]);
			}

			return ps;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		

	}

	public static void closeConnection(Connection con) {

		try {

			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeConnection(Connection con, PreparedStatement ps) {

		try {

			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {

		try {

			rs.close();
			ps.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
