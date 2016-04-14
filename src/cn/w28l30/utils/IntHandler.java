package cn.w28l30.utils;

import java.sql.ResultSet;

public class IntHandler implements ResultSetHander {

	@Override
	public Object handle(ResultSet rs) {
		try {
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return 0;
	}

}
