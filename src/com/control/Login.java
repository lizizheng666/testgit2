package com.control;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ann.RequestParam;
import com.pojo.User;
import com.service.Service;
import com.serviceImpl.ServiceImpl;

public class Login {

	Service ser = new ServiceImpl();

	public String login(@RequestParam User user,HttpServletRequest req) {
		String res = ser.login(user);
		if (res=="LoginOK"){
			
			HttpSession hss = req.getSession();
			hss.setAttribute(user.getPhone(), "user");
			return "../index/index.jsp";
		} else {
			return "login.jsp";
		}
	}

	public String regis(@RequestParam User user) {
		String res2 = ser.regn(user);
		if(res2.equals("RegOK")) {
			return "../login/login.jsp";
			}
		else if(res2=="IsExist") {
			return "regis.jsp";
		}else {
			return "regis.jsp";
		}
		
	}
}
