package cn.w28l30.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import cn.w28l30.dao.CustomerDao;
import cn.w28l30.domain.Customer;
import cn.w28l30.domain.QueryInfo;
import cn.w28l30.domain.QueryResult;
import cn.w28l30.exception.DaoException;
import cn.w28l30.utils.BeanHandler;
import cn.w28l30.utils.IntHandler;
import cn.w28l30.utils.JdbcUtils;
import cn.w28l30.utils.ListHandler;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void add(Customer c) {
		// TODO Auto-generated method stub
		try {
			String sql = "insert into customer(id,name,gender,birthday,cellphone,email,preference,type,description) values(?,?,?,?,?,?,?,?,?)";
			Object[] param = {c.getId(),c.getName(),c.getGender(),new java.sql.Date(c.getBirthday().getTime()),c.getCellphone(),c.getEmail(),c.getPreference(),c.getType(),c.getDescription()};
			JdbcUtils.update(sql, param);
		} catch (Exception e) {
			throw new DaoException(e);
		} 
	}

	@Override
	public void update(Customer c) {
		// TODO Auto-generated method stub
		try {
			String sql = "update customer set name=?, gender=?, birthday=?, cellphone=?, email=?, preference=?, type=?, description=? where id=?";
			Object[] param = {c.getName(),c.getGender(),new java.sql.Date(c.getBirthday().getTime()),c.getCellphone(),c.getEmail(),c.getPreference(),c.getType(),c.getDescription(), c.getId()};
			JdbcUtils.update(sql, param);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		try {
			Object[] param = {id};
			String sql = "delete from customer where id=?";
			JdbcUtils.update(sql, param);
		} catch (Exception e) {
			throw new DaoException(e);
		} 
	}

	@Override
	public Customer find(String id) {
		// TODO Auto-generated method stub
		try {
			Object[] param = {id};
			String sql = "select * from customer where id=?";
			return (Customer) JdbcUtils.query(sql, param, new BeanHandler(Customer.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		try {

			String sql = "select * from customer";
			Object[] param = {};
			return (List<Customer>) JdbcUtils.query(sql, param, new ListHandler(Customer.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public QueryResult pageQuery(QueryInfo info) {
		// TODO Auto-generated method stub
		QueryResult qr = new QueryResult();
		try {
			String sql = "select * from customer limit ?, ?";
			Object[] params = {info.getStartIndex(),info.getPageSize()};
			List list = (List) JdbcUtils.query(sql, params, new ListHandler(Customer.class));

			qr.setList(list);
			
			sql = "select count(*) from customer";
			params = new Object[]{};
			int totalRecord = (Integer) JdbcUtils.query(sql, params, new IntHandler());
			
			qr.setTotalRecord(totalRecord);
			return qr;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
