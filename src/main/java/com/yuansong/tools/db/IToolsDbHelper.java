package com.yuansong.tools.db;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

import com.yuansong.tools.db.conn.MSSqlConnInfo;

public interface IToolsDbHelper {
	
	public static String BEAN_TX_MANAGER = "txManagerDynamic";
	public static String BEAN_JDBCTEMPLATE = "jdbcTemplateDynamic";
	
	/**
	 * 获取数据源连接
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate();
	
	/**
	 * 添加数据源 mssql
	 * @param key
	 * @param config
	 */
	public void addDataSource(@NonNull String key, @NonNull MSSqlConnInfo config);
	
	//TODO sqlite mysql 待补充
	
	public void removeDataSource(@NonNull String key);
	
	public void clear();
	
	public long size();
	
	public void setDataSourceKey(@NonNull String key);
	
	public void remove();

}
