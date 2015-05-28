package com.lite.jqgrid4j.dispatcher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lite.jqgrid4j.config.FilterConfig;
import com.lite.jqgrid4j.typeproxy.AbstractTypeProxy;
import com.lite.jqgrid4j.typeproxy.NumberTypeProxy;
import com.lite.jqgrid4j.typeproxy.TypeProxy;

public class DefaultTypeProxyDispatcher implements TypeProxyDispatcher{
	
	public TypeProxy<?> stringTypeProxy(FilterConfig filterConfig){
		return new AbstractTypeProxy<String>(){
			@Override
			public String toPrototype(String value) {
				return value;
			}
		};
	}

	public TypeProxy<?> dateTypeProxy(FilterConfig filterConfig){
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return new AbstractTypeProxy<Date>(){
			@Override
			public Date toPrototype(String value) {
				try {
					return sdf.parse(value) ;
				} catch (ParseException e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	public TypeProxy<?> booleanTypeProxy(FilterConfig filterConfig){
		return new AbstractTypeProxy<Boolean>(){
			@Override
			public Boolean toPrototype(String value) {
				return new Boolean(value);
			}
			@Override
			public String toValue(String value){
				return this.toString(value);
			}
		};
	}

	public TypeProxy<?> byteTypeProxy(FilterConfig filterConfig){
		return new NumberTypeProxy<Byte>(){
			@Override
			public Byte toPrototype(String value) {
				return new Byte(value);
			}
		};
	}

	public TypeProxy<?> shortTypeProxy(FilterConfig filterConfig){
		return new NumberTypeProxy<Short>(){
			@Override
			public Short toPrototype(String value) {
				return new Short(value);
			}
		};
	}

	public TypeProxy<?> integerTypeProxy(FilterConfig filterConfig){
		return new NumberTypeProxy<Integer>(){
			@Override
			public Integer toPrototype(String value) {
				return new Integer(value);
			}
		};
	}

	public TypeProxy<?> longTypeProxy(FilterConfig filterConfig){
		return new NumberTypeProxy<Long>(){
			@Override
			public Long toPrototype(String value) {
				return new Long(value);
			}
		};
	}

	public TypeProxy<?> floatTypeProxy(FilterConfig filterConfig){
		return new NumberTypeProxy<Float>(){
			@Override
			public Float toPrototype(String value) {
				return new Float(value);
			}
		};
	}

	public TypeProxy<?> doubleTypeProxy(FilterConfig filterConfig){
		return new NumberTypeProxy<Double>(){
			@Override
			public Double toPrototype(String value) {
				return new Double(value);
			}
		};
	}
}
