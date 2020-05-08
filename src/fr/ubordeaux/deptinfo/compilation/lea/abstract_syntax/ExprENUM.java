package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.Type;

public class ExprENUM extends ExprVALUE {
	
	private String name;

	public ExprENUM(String name, Object object, Type type, int line, int column) throws Exception {
		super(object, line, column);
		this.name = name;
		setType(type);
		
	}

	public String toString() {
		return name;
	}

}
