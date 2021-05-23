package com.yuansong.tools.db;

import java.text.MessageFormat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

@Component
class ToolsDbHelperImpl implements IToolsDbHelper {
	
	@Autowired
	@Qualifier(value=IToolsDbHelper.BEAN_JDBCTEMPLATE)
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier(value="dynamicRoutingDataSourceDynamic")
	private DynamicRoutingDataSource dynamicRoutingDataSource;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
	
	@Override
	public void addDataSource(String key, BaseConnInfo config, Integer queryTimeout) {
		if(config instanceof MSSqlConnInfo) {
			this.dynamicRoutingDataSource.addDataSource(key, this.getDataSource((MSSqlConnInfo)config, queryTimeout));
			return;
		}
		if(config instanceof MySqlConnInfo) {
			this.dynamicRoutingDataSource.addDataSource(key, this.getDataSource((MySqlConnInfo)config, queryTimeout));
			return;
		}
		if(config instanceof SQLiteConnInfo) {
			this.dynamicRoutingDataSource.addDataSource(key, this.getDataSource((SQLiteConnInfo)config, queryTimeout));
			return;
		}
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
	public long size() {
		return this.dynamicRoutingDataSource.size();
	}

	@Override
	public void setDataSourceKey(String key) {
		this.dynamicRoutingDataSource.setDataSourceKey(key);
	}

	@Override
	public void remove() {
		this.dynamicRoutingDataSource.remove();
	}
		
	private DataSource getDataSource(MSSqlConnInfo config, Integer queryTimeout) {
		DruidDataSource ds = new DruidDataSource();
		if(config.getName() != null && "".equals(ds.getName().trim())) {
			ds.setName(config.getName().trim());
		}
		ds.setDriver(null);
		ds.setUrl(MessageFormat.format("jdbc:sqlserver://{0};DatabaseName={1}", config.getServer(), config.getDbName()));
		ds.setUsername(config.getUserName());
		if(config.getPassword() != null) {
			ds.setPassword(config.getPassword());
		} else {
			ds.setPassword("");
		}
		this.subSetDataSource(ds, queryTimeout);
		return ds;
	}
	
	private DataSource getDataSource(MySqlConnInfo config, Integer queryTimeout) {
		DruidDataSource ds = new DruidDataSource();
		if(config.getName() != null && "".equals(ds.getName().trim())) {
			ds.setName(config.getName().trim());
		}
		ds.setUrl(MessageFormat.format("jdbc:mysql://{0}/{1}?serverTimezone=Asia/Shanghai&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull", 
				config.getServer(), config.getDbName()));
		ds.setUsername(config.getUserName());
		if(config.getPassword() != null) {
			ds.setPassword(config.getPassword());
		} else {
			ds.setPassword("");
		}
		this.subSetDataSource(ds, queryTimeout);
		return ds;
	}
	
	private DataSource getDataSource(SQLiteConnInfo config, Integer queryTimeout) {
		DruidDataSource ds = new DruidDataSource();
		if(config.getName() != null && "".equals(ds.getName().trim())) {
			ds.setName(config.getName().trim());
		}
		ds.setUrl(MessageFormat.format("jdbc:sqlite:{0}", config.getPath()));
		this.subSetDataSource(ds, queryTimeout);
		return ds;
	}
	
	/**
	 * 设置数据库连接参数
	 * @param ds
	 * @throws Exception
	 */
	private void subSetDataSource(DruidDataSource ds, Integer queryTimeout) {
		ds.setMinIdle(0);
		ds.setInitialSize(1);
		ds.setMaxActive(30);
		ds.setMaxWait(10000);
		if(queryTimeout == null) {
			ds.setQueryTimeout(IToolsDbHelper.DEFAULT_QUERYTIMEOUT);
		} else {
			ds.setQueryTimeout(queryTimeout);
		}
		ds.setValidationQuery("SELECT 1");
		ds.setTimeBetweenEvictionRunsMillis(60000);
		ds.setMinEvictableIdleTimeMillis(30000);
		ds.setTimeBetweenConnectErrorMillis(15 * 1000);
		//设置登录超时，避免连接信息错误时，一直等待的问题
		ds.setLoginTimeout(10);
	}

}
