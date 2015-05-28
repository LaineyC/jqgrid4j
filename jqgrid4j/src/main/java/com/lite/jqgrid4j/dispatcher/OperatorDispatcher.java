package com.lite.jqgrid4j.dispatcher;

import com.lite.jqgrid4j.bean.Rule;
import com.lite.jqgrid4j.bean.Snippet;
import com.lite.jqgrid4j.config.FilterConfig;
import com.lite.jqgrid4j.typeproxy.TypeProxy;

public interface OperatorDispatcher {
	
	//does not begin with
	public Snippet bn(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//between  and 
	public Snippet bt(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//begins with
	public Snippet bw(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//contains
	public Snippet cn(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//does not end with
	public Snippet en(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//equals
	public Snippet eq(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	// end with
	public Snippet ew(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//greater or equal
	public Snippet ge(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//greater
	public Snippet gt(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//in
	public Snippet in(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//less or equal
	public Snippet le(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//less
	public Snippet lt(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//not between  and 
	public Snippet nb(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//does not contain
	public Snippet nc(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//not equal
	public Snippet ne(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//not in
	public Snippet ni(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//is not null
	public Snippet nn(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
	//is null
	public Snippet nu(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig);
	
}
