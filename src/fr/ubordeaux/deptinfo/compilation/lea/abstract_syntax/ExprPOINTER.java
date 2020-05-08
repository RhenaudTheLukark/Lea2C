package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class ExprPOINTER extends ExprUnary {

	public ExprPOINTER(Expr son, int line, int column) throws Exception {
		super(son, line, column);
		setType(son.getType().getLeft());
	}

	@Override
	public void checkType() throws TypeException {
		this.getSon().checkType();
		getSon().getType().assertType(this, TypeCode.POINTER);
	}

	@Override
	public Object constEval(Environment<ExprVALUE> environment) throws EnvironmentException, TypeException {
		throw new TypeException("erreur système", line, column);
	}

	@Override
	public String toString() {
		return this.getSon() + "^";
	}

	public String getPointerCode() throws CodeException {
		String result;
		result = getSon().generateCode();
		return result;
	}

	@Override
	public String generateCode() throws CodeException {
		String result;
		result = "*" + getSon().generateCode();
		return result;
	}
}
