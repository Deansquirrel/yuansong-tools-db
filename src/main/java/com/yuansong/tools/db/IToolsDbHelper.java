package com.yuansong.tools.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

import com.yuansong.tools.db.conn.MSSqlConnInfo;
import com.yuansong.tools.db.conn.MySqlConnInfo;
import com.yuansong.tools.db.conn.SQLiteConnInfo;

public interface IToolsDbHelper {
	
	public static final String BEAN_TX_MANAGER = "txManagerDynamic";
	public static final String BEAN_JDBCTEMPLATE = "jdbcTemplateDynamic";
	
	/**
	 * 默认数据库连接超时时间
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
	 * @param queryTimeout 查询超时（秒）
	 */
	public void addDataSource(@NonNull String key, @NonNull MSSqlConnInfo config, Integer queryTimeout);
	default public void addDataSource(@NonNull String key, @NonNull MSSqlConnInfo config) {
		this.addDataSource(key, config, null);
	}
	
	public void addDataSource(@NonNull String key, @NonNull MySqlConnInfo config, Integer queryTimeout);
	default public void addDataSource(@NonNull String key, @NonNull MySqlConnInfo config) {
		this.addDataSource(key, config, null);
	}
	
	public void addDataSource(@NonNull String key, @NonNull SQLiteConnInfo config, Integer queryTimeout);
	default public void addDataSource(@NonNull String key, @NonNull SQLiteConnInfo config) {
		this.addDataSource(key, config, null);
	}
	
	//TODO sqlite mysql 待补充
	
	public void removeDataSource(@NonNull String key);
	
	public void clear();
	
	public long size();
	
	public void setDataSourceKey(@NonNull String key);
	
	public void remove();

}
