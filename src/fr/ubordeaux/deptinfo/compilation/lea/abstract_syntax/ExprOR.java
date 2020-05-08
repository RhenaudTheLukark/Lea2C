package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class ExprOR extends ExprBinary {

	public ExprOR(Expr left, Expr right, int line, int column) throws Exception {
		super(left, right, line, column);
		setType(getLeft().getType());
	}

	@Override
	public void checkType() throws TypeException {
		this.getLeft().checkType();
		this.getRight().checkType();
		getLeft().getType().assertType(this, TypeCode.BOOLEAN);
		getLeft().getType().assertType(this, getRight().getType());
	}

	@Override
	public String generateCode() throws CodeException {
		String result;
		result = "(" + getLeft().generateCode()
				+ " || " + getRight().generateCode() + ")";
		return result;
	}

	@Override
	public Object constEval(Environment<ExprVALUE> environment) throws EnvironmentException, TypeException {
		Object o1 = getLeft().constEval(environment);
		Object o2 = getRight().constEval(environment);
		if ((o1 instanceof Boolean) && (o2 instanceof Boolean)) 
			return new Boolean((Boolean)o1 || (Boolean)o2);
		else
			throw new TypeException("Impossible de calculer la constante, erreur de type", line, column);
	}

	@Override
	public String toString() {
		return "(" + this.getLeft() + " || " + this.getRight() + ")";
	}

}
