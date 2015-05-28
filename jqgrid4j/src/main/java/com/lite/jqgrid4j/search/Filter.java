package com.lite.jqgrid4j.search;

import com.lite.jqgrid4j.bean.FilterResult;
import com.lite.jqgrid4j.config.FilterConfig;

public interface Filter {
	
	public FilterResult filter(String filters, Class<?> root);
	
	public FilterResult filter(String filters, Class<?> root, FilterConfig filterConfig);

}
