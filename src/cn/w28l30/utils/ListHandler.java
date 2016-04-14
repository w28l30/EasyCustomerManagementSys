package cn.w28l30.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class ListHandler implements ResultSetHander {

	private Class clazz;

	public ListHandler(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public Object handle(ResultSet rs) {
		try {
			List list = new ArrayList();
			while (rs.next()) {
				Object bean = clazz.newInstance();
				ResultSetMetaData meta = rs.getMetaData();
				for (int i = 0; i < meta.getColumnCount(); i++) {
					String name = meta.getColumnName(i + 1);
					Object value = rs.getObject(name);

					Field f = bean.getClass().getDeclaredField(name);
					f.setAccessible(true);
					f.set(bean, value);
				}
				list.add(bean);
			}

			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

}
