package com.yuansong.tools.db.conn;

public class SQLiteConnInfo extends BaseConnInfo {

	private String path;
	
	public SQLiteConnInfo() {};
	public SQLiteConnInfo(String name, String path) {
		super(name);
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
