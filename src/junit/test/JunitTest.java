package junit.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.w28l30.dao.CustomerDao;
import cn.w28l30.dao.impl.CustomerDaoImpl;
import cn.w28l30.domain.Customer;

public class JunitTest {
	private CustomerDao dao = new CustomerDaoImpl();
	
	@Test
	public void testAdd() {
		Customer c = new Customer();
		c.setDescription("aaaaa");
		c.setBirthday(new Date(1989-01-01));
		c.setCellphone("7326920735");
		c.setEmail("yysgkgtc511@gmail.com");
		c.setName("yy");
		c.setType("vip");
		c.setGender("male");
		c.setPreference("football");
		c.setId("1");
		dao.add(c);
	}
	
	@Test
	public void testDelete() {
		dao.delete("1");
	}
	
	@Test
	public void testUpdate() {
		Customer c = new Customer();
		c.setDescription("bbbb");
		c.setBirthday(new Date(1989-01-01));
		c.setCellphone("7326920735");
		c.setEmail("yysgkgtc511@gmail.com");
		c.setName("yy");
		c.setType("vip");
		c.setGender("male");
		c.setPreference("football");
		c.setId("1");
		dao.update(c);
	}
	
	@Test
	public void testGetAll() {
		List<Customer> list = dao.getAll();
		for (Customer c : list) {
			System.out.println(c.getName());
		}
	}
	
	@Test
	public void testFind() {
		Customer c = dao.find("1");
		System.out.println(c.getName());
	}
}
