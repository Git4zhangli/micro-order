package com.xs.micro.order.domain.em;

public interface IntEnumInter<E extends Enum<E>> {
	
	int intValue();

	String toString();
}
