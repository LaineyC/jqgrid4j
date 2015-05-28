package com.lite.jqgrid4j.dispatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.lite.jqgrid4j.bean.ParameterStyle;
import com.lite.jqgrid4j.bean.Rule;
import com.lite.jqgrid4j.bean.Snippet;
import com.lite.jqgrid4j.config.FilterConfig;
import com.lite.jqgrid4j.typeproxy.TypeProxy;

public class HQLOperatorDispatcher implements OperatorDispatcher {
	
	private final Map<String,Integer> keyMap = new HashMap<String, Integer>();

	private String getKey(String name){
		String rename = name.replaceAll("\\W", "_");
		Integer marker = keyMap.get(rename);
		if(marker == null)
			marker = 1;
		String key = rename + marker;
		marker++;
		keyMap.put(rename, marker);
		return key;
	}
	
	private StringBuffer placeholder(Rule rule, String scriptValue, Object prototype, FilterConfig filterConfig) {
		StringBuffer placeholder = new StringBuffer();
		if(ParameterStyle.NAMED.equals(filterConfig.getParameterStyle())){
			String key = this.getKey(rule.getField());
			placeholder.append(":" + key);
			rule.getSnippet().getParameterNames().add(key);
			rule.getSnippet().getParameterValues().add(prototype);
		}
		else if(ParameterStyle.NON_NAMED.equals(filterConfig.getParameterStyle())){
			if(prototype instanceof List<?>){
				List<?> prototypeList = (List<?>)prototype;
				for(int i = 0 ; i < prototypeList.size() ; i++){
					if(i != 0){
						placeholder.append(filterConfig.getScriptSpaceMark()).
						append(",").append(filterConfig.getScriptSpaceMark());
					}
					placeholder.append("?");
				}
				rule.getSnippet().getParameterValues().addAll(prototypeList);
			}
			else{
				placeholder.append("?");
				rule.getSnippet().getParameterValues().add(prototype);
			}
		}
		else {
			placeholder.append(scriptValue);
		}
		return placeholder;
	}
	
	private String toValue(String string){
		return "'" + string + "'";
	}
	
	//does not begin with
	public Snippet bn(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append("STR(").append(rule.getField()).append(") NOT LIKE ")
			.append(this.placeholder(rule, 
					this.toValue(typeProxy.toString(rule.getData()) + "%"), 
					typeProxy.toString(rule.getData() ) + "%", filterConfig));
		return snippet;
	}
	
	//between  and 
	public Snippet bt(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		String[] vs = rule.getData().split(filterConfig.getDataSplit());
		List<?> values = typeProxy.toListOfPrototype(vs);
		snippet.getScript().append("( ").append(rule.getField()).append(" BETWEEN ")
		.append(this.placeholder(rule, typeProxy.toValue(vs[0]), values.get(0), filterConfig))
		.append(" AND ")
		.append(this.placeholder(rule, typeProxy.toValue(vs[1]), values.get(1), filterConfig))
		.append(" )");
		return snippet;
	}
	
	//begins with
	public Snippet bw(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append("STR(").append(rule.getField()).append(") LIKE ")
		.append(this.placeholder(rule, 
				this.toValue(typeProxy.toString(rule.getData()) + "%"), 
				typeProxy.toString(rule.getData()) + "%", filterConfig));
		return snippet;
	}
	
	//contains
	public Snippet cn(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append("STR(").append(rule.getField()).append(") LIKE ")
		.append(this.placeholder(rule, 
				this.toValue("%" + typeProxy.toString(rule.getData()) + "%"), 
				"%" + typeProxy.toString(rule.getData()) + "%", filterConfig));
		return snippet;
	}
	
	//does not end with
	public Snippet en(Rule rule, TypeProxy<?> typeProxy,FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append("STR(").append(rule.getField()).append(") NOT LIKE ")
		.append(this.placeholder(rule, 
				this.toValue("%" + typeProxy.toString(rule.getData())), 
				"%" + typeProxy.toString(rule.getData()), filterConfig));
		return snippet;
	}
	
	//equals
	public Snippet eq(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append(rule.getField()).append(" = ")
			.append(this.placeholder(rule, typeProxy.toValue(rule.getData()), 
					typeProxy.toPrototype(rule.getData()), filterConfig));
		return snippet;
	}
	
	// end with
	public Snippet ew(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append("STR(").append(rule.getField()).append(" LIKE ")
		.append(this.placeholder(rule, 
				this.toValue("%" + typeProxy.toString(rule.getData())), 
				"%" + typeProxy.toString(rule.getData()), filterConfig));
		return snippet;
	}
	
	//greater or equal
	public Snippet ge(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append(rule.getField()).append(" >= ")
			.append(this.placeholder(rule, typeProxy.toValue(rule.getData()), 
					typeProxy.toPrototype(rule.getData()), filterConfig));
		return snippet;
	}
	
	//greater
	public Snippet gt(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append(rule.getField()).append(" > ")
		.append(this.placeholder(rule, typeProxy.toValue(rule.getData()), 
				typeProxy.toPrototype(rule.getData()), filterConfig));
		return snippet;
	}
	
	//in
	public Snippet in(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append(rule.getField()).append(" IN( ")
			.append(this.placeholder(rule, typeProxy.toListValue(rule.getData().split(filterConfig.getDataSplit())), 
					typeProxy.toListOfPrototype(rule.getData().split(filterConfig.getDataSplit())), filterConfig)).append(" )");
		return snippet;
	}
	
	//less or equal
	public Snippet le(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append(rule.getField()).append(" <= ")
			.append(this.placeholder(rule, typeProxy.toValue(rule.getData()), 
					typeProxy.toPrototype(rule.getData()), filterConfig));
		return snippet;
	}
	
	//less
	public Snippet lt(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append(rule.getField()).append(" < ")
			.append(this.placeholder(rule, typeProxy.toValue(rule.getData()), 
					typeProxy.toPrototype(rule.getData()), filterConfig));
		return snippet;
	}
	
	//not between  and 
	public Snippet nb(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		String[] vs = rule.getData().split(filterConfig.getDataSplit());
		List<?> values = typeProxy.toListOfPrototype(vs);
		snippet.getScript().append("( ").append(rule.getField()).append(" NOT BETWEEN ")
		.append(this.placeholder(rule, typeProxy.toValue(vs[0]), values.get(0), filterConfig))
		.append(" AND ")
		.append(this.placeholder(rule, typeProxy.toValue(vs[1]), values.get(1), filterConfig))
		.append(" )");
		return snippet;
	}
	
	//does not contain
	public Snippet nc(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append("STR(").append(rule.getField()).append(") NOT LIKE ")
		.append(this.placeholder(rule, 
				this.toValue("%" + typeProxy.toString(rule.getData()) + "%"), 
				"%" + typeProxy.toString(rule.getData()) + "%", filterConfig));
		return snippet;
	}
	
	//not equal
	public Snippet ne(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append(rule.getField()).append(" != ")
			.append(this.placeholder(rule, typeProxy.toValue(rule.getData()), 
					typeProxy.toPrototype(rule.getData()), filterConfig));
		return snippet;
	}
	
	//not in
	public Snippet ni(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append(rule.getField()).append(" NOT IN( ")
			.append(this.placeholder(rule, typeProxy.toListValue(rule.getData().split(filterConfig.getDataSplit())), 
					typeProxy.toListOfPrototype(rule.getData().split(filterConfig.getDataSplit())), filterConfig)).append(" )");
		return snippet;
	}
	
	//is not null
	public Snippet nn(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append(rule.getField()).append(" IS NOT NULL");
		return snippet;
	}
	
	//is null
	public Snippet nu(Rule rule, TypeProxy<?> typeProxy, FilterConfig filterConfig){
		Snippet snippet = rule.getSnippet();
		snippet.getScript().append(rule.getField()).append(" IS NULL");
		return snippet;
	}
}
