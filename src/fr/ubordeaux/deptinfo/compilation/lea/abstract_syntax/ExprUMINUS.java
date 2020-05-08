package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class ExprUMINUS extends ExprUnary {

	public ExprUMINUS(Expr son, int line, int column) throws Exception {
		super(son, line, column);
	}

	@Override
	public void checkType() throws TypeException {
		this.getSon().checkType();
		TypeCode[] typeCodes = {TypeCode.INTEGER, TypeCode.FLOAT};
		getSon().getType().assertType(this, typeCodes);
	}

	@Override
	public String generateCode() throws CodeException {
		String result;
		result = "(-" + getSon().generateCode() + ")";
		return result;
	}

	@Override
	public Object constEval(Environment<ExprVALUE> environment) throws EnvironmentException, TypeException {
		Object o = getSon().constEval(environment);
		if ((o instanceof Integer)) 
			return new Integer(-(Integer)o);
		else if ((o instanceof Float)) 
			return new Float(-(Float)o);
		else
			throw new TypeException("Impossible de calculer la constante, erreur de type", line, column);
	}

	@Override
	public String toString() {
		return "-" + this.getSon();
	}

}
