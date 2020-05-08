package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class ExprARRAY extends ExprBinary {

	public ExprARRAY(Expr left, Expr right, int line, int column) throws Exception {
		super(left, right, line, column);
		setType(left.getType().getLeft());
	}

	@Override
	public void checkType() throws TypeException {
		this.getLeft().checkType();
		this.getRight().checkType();
		getLeft().getType().assertType(this, TypeCode.ARRAY);
		getRight().getType().assertType(this, TypeCode.INTEGER);
	}

	@Override
	public String generateCode() throws CodeException {
		String result;
		result = getLeft().generateCode() + "[" + getRight().generateCode() + "]";
		return result;
	}

	@Override
	public Object constEval(Environment<ExprVALUE> environment) throws EnvironmentException, TypeException {
		throw new TypeException("erreur système", line, column);
	}
	
	@Override
	public String toString() {
		return this.getLeft() + "[" + this.getRight() + "]";
	}


}
