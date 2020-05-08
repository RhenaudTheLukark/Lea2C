package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class ExprADDRESS extends ExprUnary {


	public ExprADDRESS(Expr son, int line, int column) throws Exception {
		super(son, line, column);
		setType(new TreeType(TypeCode.POINTER, son.getType()));
	}

	@Override
	public void checkType() throws TypeException {
		this.getSon().checkType();
	}

	@Override
	public Object constEval(Environment<ExprVALUE> environment) throws EnvironmentException, TypeException {
		throw new TypeException("erreur système", line, column);
	}

	public String getAdressCode() throws CodeException {
		return "(void *)&" + getSon().generateCode();
	}

	@Override
	public String generateCode() throws CodeException {
		String result;
		result = getSon().generateCode();
		return result;
	}

	@Override
	public String toString() {
		return "@" + this.getSon();
	}

}
