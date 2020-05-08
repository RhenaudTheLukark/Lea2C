package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class ExprVALUE extends Expr {

	private Object value;

	public ExprVALUE(Object value, int line, int column) throws Exception {
		super(line, column);
		this.value = value;
		if (value instanceof Integer)
			setType(new TreeType(TypeCode.INTEGER));
		else if (value instanceof Float)
			setType(new TreeType(TypeCode.FLOAT));
		else if (value instanceof String)
			setType(new TreeType(TypeCode.STRING));
		else if (value instanceof Boolean)
			setType(new TreeType(TypeCode.BOOLEAN));
	}

	@Override
	public String generateCode() throws CodeException {
		String result = "";
		if (getValue() instanceof Integer)
			result = getValue().toString();
		else if (getValue() instanceof Float)
			result = getValue().toString();
		else if (getValue() instanceof String)
			result = getValue().toString();
		else if (getValue() instanceof Boolean)
			result = (Boolean)getValue() ? "1" : "0";
		return result;
	}

	@Override
	public void checkType() throws TypeException {
	}

	@Override
	public Object constEval(Environment<ExprVALUE> const_environment) throws EnvironmentException, TypeException {
		return getValue();
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
