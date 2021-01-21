package com.yuansong.tools.db.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yuansong.tools.db.his.IDbToolsHelper;
import com.yuansong.tools.db.his.TargetSource;

@Aspect
@Order(-1)
@Component
class DynamicDataSourceAspect {

	@Autowired
	private IDbToolsHelper dbToolsHelper;
		
	@Pointcut("@annotation(com.yuansong.tools.db.aop.TargetSource)")
    public void pointCut() {}
	
	@Before("pointCut() && @annotation(targetSource)")
	public void doBefore(TargetSource targetSource) {
		this.dbToolsHelper.setDataSourceKey(targetSource.value());
	}

	@After("pointCut() && @annotation(targetSource)")
	public void doAfter(TargetSource targetSource) {
		this.dbToolsHelper.remove();
	}
	
}
