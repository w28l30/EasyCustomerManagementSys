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
			Object[] param = {c.getId(),c.getGender(),new java.sql.Date(c.getBirthday().getTime()),c.getCellphone(),c.getEmail(),c.getPreference(),c.getType(),c.getDescription(), c.getId()};
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
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		QueryResult qr = new QueryResult();
		try {
			conn = JdbcUtils.getConnection();
			String sql = "select * from customer limit ?, ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, info.getStartIndex());
			st.setInt(2, info.getPageSize());
			rs = st.executeQuery();
			List list = new ArrayList<>();
			while (rs.next()) {
				Customer c = new Customer();
				c.setBirthday(rs.getDate("birthday"));
				c.setEmail(rs.getString("email"));
				c.setId(rs.getString("id"));
				c.setCellphone(rs.getString("cellphone"));
				c.setDescription(rs.getString("description"));
				c.setGender(rs.getString("gender"));
				c.setName(rs.getString("name"));
				c.setType(rs.getString("type"));
				c.setPreference(rs.getString("preference"));
				list.add(c);
			}
			qr.setList(list);
			
			sql = "select count(*) from customer";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			if (rs.next()) {
				qr.setTotalRecord(rs.getInt(1));
			}
			return qr;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtils.release(conn, st, rs);
		}
	}

}
