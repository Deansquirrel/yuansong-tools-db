package com.yuansong.tools.db.his;

import org.springframework.stereotype.Component;

@Component
class ToolsDbBean {
	
//	@Bean(name = "dynamicDataSourceContextHolderDynamic")
//	public DynamicDataSourceContextHolder getDynamicDataSourceContextHolder() {
//		return new DynamicDataSourceContextHolder();
//	}
//	
//	@Bean(name = "dynamicRoutingDataSourceDynamic")
//	public DynamicRoutingDataSource getDynamicRoutingDataSource(@Qualifier("dynamicDataSourceContextHolderDynamic") DynamicDataSourceContextHolder dynamicDataSourceContextHolder) {
//		return DynamicRoutingDataSource.createDynamicRoutingDataSource(dynamicDataSourceContextHolder);
//	}
//	
//	@Bean(name="jdbcTemplateDynamic")
//	public JdbcTemplate getJdbcTemplate(@Qualifier("dynamicRoutingDataSourceDynamic") DataSource ds) {
//		return new JdbcTemplate(ds);
//	}
//	
//	@Bean(name="txManagerDynamic")
//	public PlatformTransactionManager getTxManager(@Qualifier("dynamicRoutingDataSourceDynamic") DataSource ds) {
//		return new DataSourceTransactionManager(ds);
//	}

}
