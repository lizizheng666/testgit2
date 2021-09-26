package com.pojo;

public class User {
/**
 * `sname` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(20) DEFAULT NULL COMMENT '密码',
  `headptt` varchar(20) DEFAULT NULL COMMENT '头像',
 */
	private String sname;
	private String password;
	private String headptt;
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeadptt() {
		return headptt;
	}
	public void setHeadptt(String headptt) {
		this.headptt = headptt;
	}
	
}
