package cn.w28l30.service;

import java.util.List;

import cn.w28l30.dao.CustomerDao;
import cn.w28l30.dao.impl.CustomerDaoImpl;
import cn.w28l30.domain.Customer;
import cn.w28l30.domain.PageBean;
import cn.w28l30.domain.QueryInfo;
import cn.w28l30.domain.QueryResult;
import cn.w28l30.factory.DaoFactory;

public class BusinessServiceImpl {
	private CustomerDao dao = DaoFactory.getInstance().createDao(CustomerDao.class);

	public void addCustomer(Customer c) {
		dao.add(c);
	}
	
	public void deleteCustomer(String id) {
		dao.delete(id);
	}
	
	public void updateCustomer(Customer c) {
		dao.update(c);
	}
	
	public List getAllCustomer() {
		return dao.getAll();
	}
	
	public Customer findCustomer(String id) {
		return dao.find(id);
	}
	
	public PageBean pageQuery(QueryInfo info) {
		PageBean bean = new PageBean();
		QueryResult qr = dao.pageQuery(info);
		bean.setCurrentPage(info.getCurrentPage());
		bean.setList(qr.getList());
		bean.setPageSize(info.getPageSize());
		bean.setTotalRecord(qr.getTotalRecord());
		return bean;
	}

}
