package com.lite.jqgrid4j.search;

import com.lite.jqgrid4j.bean.FilterResult;
import com.lite.jqgrid4j.config.FilterConfig;

public abstract class AbstractFilter implements Filter {
	
	public AbstractFilter(){
		
	}

	public FilterResult filter(String filters, Class<?> root) {
		return this.filter(filters, root, new FilterConfig());
	}

}
