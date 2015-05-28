package com.lite.jqgrid4j.dispatcher;

import com.lite.jqgrid4j.config.FilterConfig;
import com.lite.jqgrid4j.typeproxy.TypeProxy;

public interface TypeProxyDispatcher {

	public TypeProxy<?> stringTypeProxy(FilterConfig filterConfig);
	
	public TypeProxy<?> dateTypeProxy(FilterConfig filterConfig);
	
	public TypeProxy<?> booleanTypeProxy(FilterConfig filterConfig);
	
	public TypeProxy<?> byteTypeProxy(FilterConfig filterConfig);
	
	public TypeProxy<?> shortTypeProxy(FilterConfig filterConfig);
	
	public TypeProxy<?> integerTypeProxy(FilterConfig filterConfig);
	
	public TypeProxy<?> longTypeProxy(FilterConfig filterConfig);
	
	public TypeProxy<?> floatTypeProxy(FilterConfig filterConfig);
	
	public TypeProxy<?> doubleTypeProxy(FilterConfig filterConfig);

}
