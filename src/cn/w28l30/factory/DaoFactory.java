package cn.w28l30.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import cn.w28l30.exception.DaoException;


public class DaoFactory {
	private Properties daoConfig = new Properties();
	
	private DaoFactory() {
		InputStream in = DaoFactory.class.getClassLoader().getResourceAsStream("customerDao.properties");
		try {
			daoConfig.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new DaoException(e);
		}
	}

	private static DaoFactory instance = new DaoFactory();

	public static DaoFactory getInstance() {
		return instance;
	}
	
	public <T> T createDao(Class<T> clazz){
		
		//clazz.getName();     //cn.itcast.dao.UserDao
		String name = clazz.getSimpleName();
		String className = daoConfig.getProperty(name);
		try {
			T dao = (T) Class.forName(className).newInstance();
			return dao;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
