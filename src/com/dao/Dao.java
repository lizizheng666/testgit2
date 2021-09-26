package com.dao;

import com.pojo.User;

public interface Dao {
		public String login(User user);
		
		public String register(User user);
}
