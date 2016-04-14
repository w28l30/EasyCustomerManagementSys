package cn.w28l30.utils;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class BeanHandler implements ResultSetHander {

	private Class clazz;

	public BeanHandler(Class clazz) {
		this.clazz = clazz;
	}

	@Override
	public Object handle(ResultSet rs) {
		try {
			if (!rs.next()) {
				return null;
			}
			Object bean = clazz.newInstance();
			ResultSetMetaData meta = rs.getMetaData();
			for (int i = 0; i < meta.getColumnCount(); i++) {
				String name = meta.getColumnName(i + 1);
				Object value = rs.getObject(name);

				Field f = bean.getClass().getDeclaredField(name);
				f.setAccessible(true);
				f.set(bean, value);
			}

			return bean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

}
