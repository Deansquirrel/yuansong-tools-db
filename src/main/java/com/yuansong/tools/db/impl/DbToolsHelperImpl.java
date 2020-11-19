package com.yuansong.tools.db.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.yuansong.tools.db.DataSourceType;
import com.yuansong.tools.db.IDataSourceHelper;
import com.yuansong.tools.db.IDbConnConfig;
import com.yuansong.tools.db.IDbToolsHelper;

@Component
class DbToolsHelperImpl implements IDbToolsHelper {
	
	@Autowired
	@Qualifier(value="jdbcTemplateDynamic")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier(value="dynamicRoutingDataSourceDynamic")
	private DynamicRoutingDataSource dynamicRoutingDataSource;
	
	@Autowired
	private IDataSourceHelper dataSourceHelper;

	@Override
	public void setDataSourceKey(String key) {
		this.dynamicRoutingDataSource.setDataSourceKey(key);
	}
	
	@Override
	public void addDataSource(String key, IDbConnConfig config, DataSourceType type) throws Exception {		
		this.addDataSource(key, this.dataSourceHelper.getDataSource(config, type));
	}

	@Override
	public void remove() {
		this.dynamicRoutingDataSource.remove();
	}

	@Override
	public void addDataSource(String key, DataSource dataSource) {
		this.dynamicRoutingDataSource.addDataSource(key, dataSource);
	}

	@Override
	public void removeDataSource(String key) {
		this.dynamicRoutingDataSource.removeDataSource(key);
	}

	@Override
	public void clear() {
		this.dynamicRoutingDataSource.clear();
	}

	@Override
	public int size() {
		return this.dynamicRoutingDataSource.size();
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	@Override
	public String getTxManager() {
		return "txManagerDynamic";
	}

}
