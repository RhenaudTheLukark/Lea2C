package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class ExprPLUS extends ExprBinary {

	public ExprPLUS(Expr left, Expr right, int line, int column) throws Exception {
		super(left, right, line, column);
		setType(getLeft().getType());
	}

	public void checkType() throws TypeException {
		this.getLeft().checkType();
		this.getRight().checkType();
		TypeCode[] typeCodes = {TypeCode.INTEGER, TypeCode.FLOAT};
		getLeft().getType().assertType(this, typeCodes);
		getLeft().getType().assertType(this, getRight().getType());
	}

	@Override
	public String generateCode() throws CodeException {
		String result;
		result = "(" + getLeft().generateCode()
				+ " + " + getRight().generateCode() + ")";
		return result;
	}

	@Override
	public Object constEval(Environment<ExprVALUE> environment) throws EnvironmentException, TypeException {
		Object o1 = getLeft().constEval(environment);
		Object o2 = getRight().constEval(environment);
		if ((o1 instanceof Integer) && (o2 instanceof Integer)) 
			return new Integer((Integer)o1 + (Integer)o2);
		else if ((o1 instanceof Float) && (o2 instanceof Float)) 
			return new Float((Float)o1 + (Float)o2);
		else if ((o1 instanceof String) && (o2 instanceof String)) 
			return new String((String)o1 + (String)o2);
		else
			throw new TypeException("Impossible de calculer la constante, erreur de type", line, column);
	}

	@Override
	public String toString() {
		return "(" + this.getLeft() + " + " + this.getRight() + ")";
	}

}
