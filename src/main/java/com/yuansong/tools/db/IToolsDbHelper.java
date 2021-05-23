package com.yuansong.tools.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

public interface IToolsDbHelper {
	
	public static final String BEAN_TX_MANAGER = "txManagerDynamic";
	public static final String BEAN_JDBCTEMPLATE = "jdbcTemplateDynamic";
	
	/**
	 * 默认数据库连接超时时间（秒）
	 */
	public static final int DEFAULT_QUERYTIMEOUT = 3600;
	
	/**
	 * 获取数据源连接
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate();
	
	/**
	 * 添加数据源
	 * @param key
	 * @param config
	 * @param queryTimeout
	 */
	public void addDataSource(@NonNull String key, @NonNull BaseConnInfo config, Integer queryTimeout);
	default public void addDataSource(@NonNull String key, @NonNull BaseConnInfo config) {
		this.addDataSource(key, config, null);
	}
		
	/**
	 * 移除数据源
	 * @param key
	 */
	public void removeDataSource(@NonNull String key);
	
	/**
	 * 清空所有数据源
	 */
	public void clear();
	
	/**
	 * 数据源数量
	 * @return
	 */
	public long size();
	
	/**
	 * 设置当前数据源
	 * @param key
	 */
	public void setDataSourceKey(@NonNull String key);
	
	/**
	 * 重置缓存（连接使用完后需调用）
	 */
	public void remove();
	
	/**
	 * 返回MSSql数据源配置
	 * @param name
	 * @param server
	 * @param dbName
	 * @param userName
	 * @param password
	 * @return
	 */
	default public MSSqlConnInfo getMSSqlConnInfo(String name, String server, String dbName, String userName, String password) {
		MSSqlConnInfo conn = new MSSqlConnInfo();
		conn.setName(name);
		conn.setServer(server);
		conn.setDbName(dbName);
		conn.setUserName(userName);
		conn.setPassword(password);
		return conn;
	}
	
	/**
	 * 返回MySql数据源配置
	 * @param name
	 * @param server
	 * @param dbName
	 * @param userName
	 * @param password
	 * @return
	 */
	default public MySqlConnInfo getMySqlConnInfo(String name, String server, String dbName, String userName, String password) {
		MySqlConnInfo conn = new MySqlConnInfo();
		conn.setName(name);
		conn.setServer(server);
		conn.setDbName(dbName);
		conn.setUserName(userName);
		conn.setPassword(password);
		return conn;
	}
	
	/**
	 * 返回SQLite数据源配置
	 * @param name
	 * @param path
	 * @return
	 */
	default public SQLiteConnInfo getSQLiteConnInfo(String name, String path ) {
		SQLiteConnInfo conn = new SQLiteConnInfo();
		conn.setName(name);
		conn.setPath(path);
		return conn;
	}

}
