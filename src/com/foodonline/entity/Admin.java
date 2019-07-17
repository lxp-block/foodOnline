package com.foodonline.entity;

public class Admin implements java.io.Serializable {


	private Integer id;
	private String loginName;
	private String loginPwd;


	public Admin() {
	}

	
	public Admin(String loginName, String loginPwd) {
		this.loginName = loginName;
		this.loginPwd = loginPwd;
	}

	

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

}
