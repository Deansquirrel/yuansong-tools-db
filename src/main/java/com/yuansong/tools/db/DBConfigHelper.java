package com.yuansong.tools.db;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DBConfigHelper {

	public IDBConfig getMssqlDbConfig(
			@Nullable String name,
			@NonNull String server,
			@NonNull String dbName,
			@NonNull String username,
			@Nullable String password) {
		return this.getDbConfig(name, server, dbName, username, password);
	}
	
	public IDBConfig getMysqlDbConfig(
			@Nullable String name,
			@NonNull String server,
			@NonNull String dbName,
			@NonNull String username,
			@Nullable String password) {
		return this.getDbConfig(name, server, dbName, username, password);
	}
	
	public IDBConfig getSQLiteDbConfig(@NonNull String path) {
		return this.getSQLiteDbConfig(null, path, null, null);
	}
	
	public IDBConfig getSQLiteDbConfig(
			@Nullable String name,
			@NonNull String path,
			@Nullable String username,
			@Nullable String password) {
		return this.getDbConfig(name, path, null, username, password);
	}
	
	private IDBConfig getDbConfig(
			String name,
			String server,
			String dbName,
			String username,
			String password) {
		return new IDBConfig() {

			@Override
			public String getName() {
				if(name == null || "".equals(name.trim())) {
					return null;
				} else {
					return name.trim();
				}
			}

			@Override
			public String getServer() {
				if(server == null || "".equals(server.trim())) {
					return "";
				} else {
					return server.trim();
				}
			}

			@Override
			public String getDbName() {
				if(dbName == null || "".equals(dbName.trim())) {
					return "";
				} else {
					return dbName.trim();
				}
			}

			@Override
			public String getUsername() {
				if(username == null || "".equals(username.trim())) {
					return "";
				} else {
					return username.trim();
				}
			}

			@Override
			public String getPassword() {
				if(password == null || "".equals(password.trim())) {
					return "";
				} else {
					return password.trim();
				}
			}
			
		};
	}
	
}
