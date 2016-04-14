package cn.w28l30.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class JdbcUtils {
	private static DataSource ds = null;

	static {
		try {
			Properties prop = new Properties();
			prop.load(JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	public static void release(Connection conn, Statement st, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close(); // throw new
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			st = null;
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void update(String sql, Object[] param) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				st.setObject(i + 1, param[i]);
			}
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, st, rs);
		}
	}

	public static Object query(String sql, Object[] param, ResultSetHander handler) {
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			st = conn.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				st.setObject(i + 1, param[i]);
			}
			rs = st.executeQuery();
			return handler.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			release(conn, st, rs);
		}

		return null;
	}

}


