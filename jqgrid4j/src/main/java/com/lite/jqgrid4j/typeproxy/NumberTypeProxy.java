package com.lite.jqgrid4j.typeproxy;

public abstract class NumberTypeProxy<X extends Number> extends AbstractTypeProxy<X> {
	
	public String toValue(String value){
		return this.toString(value);
	}

}
