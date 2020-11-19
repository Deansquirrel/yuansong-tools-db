package com.yuansong.tools.db.impl;

import java.text.MessageFormat;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.yuansong.tools.db.DataSourceType;
import com.yuansong.tools.db.IDataSourceHelper;
import com.yuansong.tools.db.IDbConnConfig;

@Component
class DataSourceHelper implements IDataSourceHelper {
	
	@Override
	public DataSource getDataSource(IDbConnConfig config, DataSourceType type) throws Exception {
		DruidDataSource ds = new DruidDataSource();
		if(config.getName() != null && config.getName().trim() != "") {
			ds.setName(config.getName().trim());
		}
		ds.setUrl(this.getUrl(config.getServer(), config.getDbName(), type));
		if(config.getUsername() != null && !"".equals(config.getUsername().trim())) {
			ds.setUsername(config.getUsername().trim());
		}
		if(config.getPassword() != null && !"".equals(config.getPassword().trim())) {
			ds.setPassword(config.getPassword().trim());
		}
		this.subSetDataSource(ds);
		return ds;
	}
	
	private String getUrl(String server, String dbName, DataSourceType type) throws Exception {		
		switch(type) {
		case Mssql:
			return MessageFormat.format("jdbc:sqlserver://{0};DatabaseName={1}", server, dbName);
		case SQLite:
			return MessageFormat.format("jdbc:sqlite:{0}", server);
		case MySQL:
			return MessageFormat.format("jdbc:mysql://{0}/{1}?serverTimezone=Asia/Shanghai&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull", server, dbName);
			default:
				throw new Exception(MessageFormat.format("unsupported database type [{0}]", type));
		}
	}
	
	/**
	 * 设置数据库连接参数
	 * @param ds
	 * @throws Exception
	 */
	private void subSetDataSource(DruidDataSource ds) {
		ds.setMinIdle(0);
		ds.setInitialSize(1);
		ds.setMaxActive(30);
		ds.setMaxWait(10000);
		ds.setQueryTimeout(3600);
		ds.setValidationQuery("SELECT 'X'");
		ds.setTimeBetweenEvictionRunsMillis(60000);
		ds.setMinEvictableIdleTimeMillis(30000);
		ds.setTimeBetweenConnectErrorMillis(5 * 60 * 1000);
	}

}
