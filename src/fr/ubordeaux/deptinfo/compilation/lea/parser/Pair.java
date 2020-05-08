package fr.ubordeaux.deptinfo.compilation.lea.parser;

import fr.ubordeaux.deptinfo.compilation.lea.type.Type;

public class Pair<T1, T2> {

	private T1 key;
	private T2 value;

	public Pair(T1 key, T2 value) {
		this.key = key;
		this.value = value;
	}

	public T1 getKey() {
		return this.key;
	}

	public T2 getValue() {
		return this.value;
	}
	
	

}
