package com.baicai.futurekiller.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.baicai.futurekiller.ServerLogger;

public class DbManager {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			ServerLogger.error("load Driver error:", e);
		}
	}

	public static void execSql(String sql, List<Object[]> paramsList) {
		Connection conn = getDbConnection();
		QueryRunner qRunner = new QueryRunner();
		try {
			qRunner.batch(conn, sql, paramsList.toArray(new Object[][] {}));
		} catch (SQLException e) {
			ServerLogger.error("execSql error:", e);
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				ServerLogger.error("close dbConnection error:", e);
			}
		}
	}

	public static <T> T selectSql(String sql, ResultSetHandler<T> rsh, Object... params) {
		Connection conn = getDbConnection();
		QueryRunner qRunner = new QueryRunner();
		try {
			return qRunner.query(conn, sql, rsh, params);
		} catch (SQLException e) {
			ServerLogger.error("selectSql error:", e);
			return null;
		} finally {
			try {
				DbUtils.close(conn);
			} catch (SQLException e) {
				ServerLogger.error("close dbConnection error:", e);
			}
		}
	}

	private static Connection getDbConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://10.17.1.61/killer", "pjy", "pjy");
		} catch (SQLException e) {
			ServerLogger.error("connect failed!", e);
		}
		return conn;
	}

	public static <T> T selectOne(String sql, Class<T> cls, Object... params) {
		return selectSql(sql, new BeanHandler<T>(cls), params);
	}

	public static <T> List<T> selectList(String sql, Class<T> cls, Object... params) {
		return selectSql(sql, new BeanListHandler<T>(cls), params);
	}
}
