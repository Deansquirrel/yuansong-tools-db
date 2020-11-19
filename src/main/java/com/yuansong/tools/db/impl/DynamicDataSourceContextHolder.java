package com.yuansong.tools.db.impl;

class DynamicDataSourceContextHolder {
	
	private final ThreadLocal<String> contetHolder = new ThreadLocal<String>() {

		@Override
		protected String initialValue() {
			return "dynamic_dbo";
		}
		
	};
	
	public void setDataSourceKey(String key) {
		this.contetHolder.set(key);
	}
	
	public String getDataSourceKey() {
		return this.contetHolder.get();
	}
	
	public void remove() {
		this.contetHolder.remove();
	}

}
