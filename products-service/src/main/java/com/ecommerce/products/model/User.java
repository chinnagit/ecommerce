package com.ecommerce.products.model;

public class User {
	
	String userId;
	String name;
	String password;
	String role;
	
	public User() {}
	
	public User(String userId, String name, String password, String role) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.role = role;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", password=" + password + ", role=" + role + "]";
	}

}
