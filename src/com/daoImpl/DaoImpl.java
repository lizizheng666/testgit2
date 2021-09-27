package com.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.dao.Dao;
import com.pojo.User;
import com.util.LinkDataBase;

public class DaoImpl implements Dao{
	
	QueryRunner qr = new QueryRunner();

	@Override
	public String login(User user) {
		
		Connection con = LinkDataBase.getCon();
		String sql = "select phone,password from `t_user` where phone = ? and password = ?";
		try {
			User user1 = qr.query(con, sql, new BeanHandler<User>(User.class), user.getPhone(),user.getPassword());
			if(user1!=null) {
				return "LoginOK";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "LoginNO";
	}

	@Override
	public String register(User user) {
		Connection con = LinkDataBase.getCon();
		String sql = "select phone from `t_user` where phone = ?";
		String sql2 = "insert `t_user`(phone,password) values(?,?)";
		try {
			User user2 = qr.query(con, sql, new BeanHandler<User>(User.class), user.getPhone());
			if(user2==null) {
				qr.insert(con, sql2,new BeanHandler<String>(String.class), user.getPhone(),user.getPassword());
				return "RegOK";
			}
			else {
				return "IsExist";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "RegNO";
		}
		
	}

}
