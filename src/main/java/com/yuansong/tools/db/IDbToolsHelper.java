package com.yuansong.tools.db;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public interface IDbToolsHelper {
	
	public JdbcTemplate getJdbcTemplate();
	
	public String getTxManager();
	
	public void addDataSource(String key, DataSource dataSource);
	
	public void addDataSource(String key, IDbConnConfig config, DataSourceType type) throws Exception;
	
	public void removeDataSource(String key);
	
	public void clear();
	
	public int size();
	
	public void setDataSourceKey(String key);
	
	public void remove();

}
