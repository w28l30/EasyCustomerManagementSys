package cn.w28l30.dao;

import java.util.List;


import cn.w28l30.domain.Customer;
import cn.w28l30.domain.QueryInfo;
import cn.w28l30.domain.QueryResult;

public interface CustomerDao {
	public void add(Customer c);

	public void update(Customer c);

	public void delete(String id);

	public Customer find(String id);

	public List<Customer> getAll();
	
	public QueryResult pageQuery(QueryInfo info);
}
