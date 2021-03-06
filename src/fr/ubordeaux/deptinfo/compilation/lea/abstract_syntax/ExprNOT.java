package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class ExprNOT extends ExprUnary {

	public ExprNOT(Expr son, int line, int column) throws Exception {
		super(son, line, column);
		setType(this.getSon().getType());
	}

	@Override
	public void checkType() throws TypeException {
		this.getSon().checkType();
		getSon().getType().assertType(this, TypeCode.BOOLEAN);
	}

	@Override
	public String generateCode() throws CodeException {
		String result;
		result = "(! " + getSon().generateCode() + ")";
		return result;
	}

	@Override
	public Object constEval(Environment<ExprVALUE> environment) throws EnvironmentException, TypeException {
		Object o = getSon().constEval(environment);
		if ((o instanceof Boolean)) 
			return new Boolean(!(Boolean)o);
		else
			throw new TypeException("Impossible de calculer la constante, erreur de type", line, column);
	}

	@Override
	public String toString() {
		return "!" + this.getSon();
	}

}
