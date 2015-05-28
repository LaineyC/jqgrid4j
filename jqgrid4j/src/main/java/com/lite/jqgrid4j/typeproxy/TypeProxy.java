package com.lite.jqgrid4j.typeproxy;

import java.util.List;

public interface TypeProxy<X> {
	
	//可以间接对对数据加密
	public String toString(String value);
	
	//解析成java对象 例如java.util.Date 对象
	public X toPrototype(String value);
	
	//解析成java对象的集合  List<java.util.Date>
	public List<X> toListOfPrototype(String[] value);

	//在脚本中的值  如   '2012-02-22 22:22:22'
	public String toValue(String value);
	
	//在脚本中的值  如   '2012-02-22 22:22:22','2012-02-22 22:22:22'
	public String toListValue(String[] value);
	
}
