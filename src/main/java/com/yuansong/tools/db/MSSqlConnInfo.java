package com.yuansong.tools.db;

class MSSqlConnInfo extends BaseConnInfo {

	private String server;
	private String dbName;
	private String userName;
	private String password;
	
	public MSSqlConnInfo() {}
	public MSSqlConnInfo(
			String name,
			String server, 
			String dbName, 
			String userName, 
			String password) {
		super(name);
		this.server = server;
		this.dbName = dbName;
		this.userName = userName;
		this.password = password;
	}
	
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
