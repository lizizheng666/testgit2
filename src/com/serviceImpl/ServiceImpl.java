package com.serviceImpl;

import com.dao.Dao;
import com.daoImpl.DaoImpl;
import com.pojo.User;
import com.service.Service;

public class ServiceImpl implements Service {
	
	Dao dao = new DaoImpl();

	@Override
	public String login(User user) {
		// TODO Auto-generated method stub
		return dao.login(user);
	}

	@Override
	public String regn(User user) {
		// TODO Auto-generated method stub
		return dao.register(user);
	}

}
