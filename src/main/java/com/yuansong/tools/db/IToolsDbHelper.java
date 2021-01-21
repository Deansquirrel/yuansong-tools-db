package com.yuansong.tools.db;

import org.springframework.jdbc.core.JdbcTemplate;

import com.yuansong.tools.db.conn.MSSqlConnInfo;

public interface IToolsDbHelper {
	
	/**
	 * 获取数据源连接
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate();
	
	/**
	 * 获取事务名称
	 * @return
	 */
	public String getTxManager();
	
	/**
	 * 添加数据源 mssql
	 * @param key
	 * @param config
	 */
	public void addDataSource(String key, MSSqlConnInfo config);
	
	//TODO sqlite mysql 待补充
	
	public void removeDataSource(String key);
	
	public void clear();
	
	public long size();
	
	public void setDataSourceKey(String key);
	
	public void remove();

}
