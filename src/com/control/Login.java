package com.control;

import com.ann.RequestParam;
import com.pojo.User;

public class Login {
		public String login(@RequestParam User user) {
			System.out.println("点了登录。。。。。。。");
			
			
			return "../index.jsp";
			
		}
		
		public String register() {
			return "";		
			}
}
