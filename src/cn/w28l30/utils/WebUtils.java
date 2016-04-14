package cn.w28l30.utils;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtils {
	
	public static <T> T request2Bean(HttpServletRequest request, Class <T>classBean) {
		try {
			T bean = classBean.newInstance();
			ConvertUtils.register(new Converter() {

				@Override
				public <T> T convert(Class<T> type, Object value) {
					// TODO Auto-generated method stub
					if (value == null) {
						return null;
					}
					if (!(value instanceof String)) {
						throw new ConversionException("只支持string类型的转换");
					}
					String str = (String) value;
					if (str.trim().equals("")) {
						return null;
					}
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					try {
						return (T) df.parse(str);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						throw new RuntimeException(e); // 异常链不能断
					}
				}}, Date.class);
			BeanUtils.populate(bean, request.getParameterMap());
			return bean;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

	}
	
	public static String generateID() {
		return UUID.randomUUID().toString();
	}

}
