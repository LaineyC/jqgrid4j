package com.lite.jqgrid4j.dispatcher;

import java.lang.reflect.Method;
import com.lite.jqgrid4j.bean.Rule;
import com.lite.jqgrid4j.bean.Snippet;
import com.lite.jqgrid4j.config.FilterConfig;
import com.lite.jqgrid4j.typeproxy.TypeProxy;

public final class DispatcherManager{
	
	private static DispatcherManager instance = new DispatcherManager();
	
	private DispatcherManager(){
		
	}
	
	public static DispatcherManager getInstance(){
		 return instance;
	}

	public Snippet operaterAction(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig, OperatorDispatcher target) {
		Snippet snippet = null;
		try {
			Method m = target.getClass().getMethod(rule.getOperator(), new Class[]{Rule.class, TypeProxy.class, FilterConfig.class });
			snippet = (Snippet)m.invoke(target, new Object[]{rule, typeProxy, filterConfig});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return snippet;
	}

	public TypeProxy<?> typeProxyAction(Rule rule, FilterConfig filterConfig, TypeProxyDispatcher target) {
		TypeProxy<?> typeProxy = null;
		try {
			Method m = target.getClass().getMethod(rule.getType() + "TypeProxy", new Class[]{FilterConfig.class});
			typeProxy = (TypeProxy<?>)(m.invoke(target, new Object[]{filterConfig}));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return typeProxy;
	}

}
