package com.yuansong.tools.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

	private final Logger logger = LoggerFactory.getLogger(DynamicRoutingDataSource.class);
	
	private DynamicDataSourceContextHolder contextHolder = null;
	private Map<Object, Object> targetDataSources = null;
	
	/**
	 * 禁止直接new对象
	 */
	private DynamicRoutingDataSource() {};
	
	/**
	 * 创建对象
	 * @param contextHolder
	 * @return
	 */
	public static final DynamicRoutingDataSource createDynamicRoutingDataSource(DynamicDataSourceContextHolder contextHolder) {
		DynamicRoutingDataSource drds = new DynamicRoutingDataSource();
		drds.setTargetDataSources(new HashMap<>());
		drds.contextHolder = contextHolder;
		return drds;
	}
	
	@Override
	protected Object determineCurrentLookupKey() {
		return this.contextHolder.getDataSourceKey();
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		super.setTargetDataSources(targetDataSources);
		this.targetDataSources = targetDataSources;
	}
	
	/**
	 * 数据源配置是否存在
	 * @param key
	 * @return
	 */
	public boolean isExistDataSource(String key) {
		return this.targetDataSources.containsKey(key);
	}
	
	/**
	 * 数据源配置长度
	 * @return
	 */
	public int size() {
		return this.targetDataSources.size();
	}
	
	/**
	 * 添加数据源
	 * @param key
	 * @param dataSource
	 */
	public synchronized void addDataSource(String key, DataSource dataSource) {
		if(this.targetDataSources.containsKey(key)) {
			return;
		}
		this.targetDataSources.put(key, dataSource);
		this.afterPropertiesSet();
		logger.debug("datasource {} has been added", key);
	}
	
	/**
	 * 移除数据源
	 * @param key
	 */
	public synchronized void removeDataSource(String key) {
		DruidDataSource dataSource = (DruidDataSource) this.targetDataSources.get(key);
		if(this.targetDataSources.containsKey(key)) {
			this.targetDataSources.remove(key);
			dataSource.close();
			dataSource = null;
			this.afterPropertiesSet();			
		}
		logger.debug("datasource {} has been removed", key);
	}
	/**
	 * 设置当前数据源
	 * @param key
	 */
	public synchronized void setDataSourceKey(String key) {
		this.contextHolder.setDataSourceKey(key);
	}
	
	public synchronized void remove() {
		this.contextHolder.remove();
	}
	
	/**
	 * 数据源key列表
	 * @return
	 */
	public Set<String> keySet() {
		Set<String> result = new HashSet<String>();
		for(Object key : this.targetDataSources.keySet()) {
			result.add(String.valueOf(key));
		}
		return result;
	}
	
	/**
	 * 清空数据源
	 */
	public synchronized void clear() {
		for(String key : this.keySet()) {
			this.removeDataSource(key);
		}
	}
}
