package com.yuansong.tools.db;

import javax.sql.DataSource;

public interface IDataSourceHelper {
	
	public DataSource getDataSource(IDbConnConfig config, DataSourceType type) throws Exception;

}
