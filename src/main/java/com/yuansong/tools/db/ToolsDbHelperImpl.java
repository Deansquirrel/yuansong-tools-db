package com.yuansong.tools.db;

import java.text.MessageFormat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.yuansong.tools.db.config.DynamicRoutingDataSource;
import com.yuansong.tools.db.conn.MSSqlConnInfo;

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
	public void addDataSource(String key, MSSqlConnInfo config) {
		this.dynamicRoutingDataSource.addDataSource(key, this.getDataSource(config));
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
	
//	case Mssql:
//	return MessageFormat.format("jdbc:sqlserver://{0};DatabaseName={1}", server, dbName);
//case SQLite:
//	return MessageFormat.format("jdbc:sqlite:{0}", server);
//case MySQL:
//	return MessageFormat.format("jdbc:mysql://{0}/{1}?serverTimezone=Asia/Shanghai&characterEncoding=utf8&autoReconnect=true&zeroDateTimeBehavior=convertToNull", server, dbName);
//	default:
//		throw new Exception(MessageFormat.format("unsupported database type [{0}]", type));
//}
	
	private DataSource getDataSource(MSSqlConnInfo config) {
		DruidDataSource ds = new DruidDataSource();
		if(config.getName() != null && "".equals(ds.getName().trim())) {
			ds.setName(config.getName().trim());
		}
		ds.setUrl(MessageFormat.format("jdbc:sqlserver://{0};DatabaseName={1}", config.getServer(), config.getDbName()));
		if(config.getPassword() != null) {
			ds.setPassword(config.getPassword());
		} else {
			ds.setPassword("");
		}
		this.subSetDataSource(ds);
		return ds;
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
		ds.setTimeBetweenConnectErrorMillis(15 * 1000);
	} 

}
