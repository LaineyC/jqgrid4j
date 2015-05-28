package com.lite.jqgrid4j.typeproxy;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTypeProxy<X> implements TypeProxy<X> {

	public String toString(String value) {
		return value;
	}
	
	public abstract X toPrototype(String value);

	public List<X> toListOfPrototype(String[] value) {
		List<X> list = new ArrayList<X>();
		for(String v : value)
			list.add(this.toPrototype(v));
		return list;
	}

	public String toValue(String value){
		return "'" + this.toString(value) + "'";
	}
	
	public String toListValue(String[] value){
		String rt = "";
		for(int i = 0 ; i < value.length ; i++ ){
			rt += this.toValue(value[i]);
			if(i < value.length-1)
				rt += ",";
		}
		return rt;
	}
	
}
