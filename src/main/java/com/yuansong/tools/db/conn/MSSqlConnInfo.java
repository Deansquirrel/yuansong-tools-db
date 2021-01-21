package com.yuansong.tools.db.conn;

public class MSSqlConnInfo extends BaseConnInfo {

	private String server;
	private Integer port;
	private String dbName;
	private String userName;
	private String password;
	
	public MSSqlConnInfo() {}
	public MSSqlConnInfo(
			String name,
			String server, 
			Integer port, 
			String dbName, 
			String userName, 
			String password) {
		super(name);
		this.server = server;
		this.port = port;
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
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
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
