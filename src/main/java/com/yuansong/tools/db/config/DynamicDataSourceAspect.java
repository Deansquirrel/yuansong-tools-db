package com.yuansong.tools.db.config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yuansong.tools.db.IToolsDbHelper;
import com.yuansong.tools.db.TargetSource;

@Aspect
@Order(-1)
@Component
class DynamicDataSourceAspect {

	@Autowired
	private IToolsDbHelper toolsDbHelper;
		
	@Pointcut("@annotation(com.yuansong.tools.db.TargetSource)")
    public void pointCut() {}
	
	@Before("pointCut() && @annotation(targetSource)")
	public void doBefore(TargetSource targetSource) {
		this.toolsDbHelper.setDataSourceKey(targetSource.value());
	}

	@After("pointCut() && @annotation(targetSource)")
	public void doAfter(TargetSource targetSource) {
		this.toolsDbHelper.remove();
	}
	
}
