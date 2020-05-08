package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class ExprVARIABLE extends Expr {

	private String id;

	public ExprVARIABLE(Type type, String id, int line, int column) throws Exception {
		super(line, column);
		this.id = id;
		setType(type);
	}

	@Override
	public String generateCode() throws CodeException {
		return id;
	}

	@Override
	public void checkType() throws TypeException {
	}

	@Override
	public Object constEval(Environment<ExprVALUE> environment) throws EnvironmentException, TypeException {
		Object o = environment.get(id);
		if (o == null)
			throw new EnvironmentException(id + " non trouvé dans " + environment);
		return o;
	}

	@Override
	public String toString() {
		return id;
	}

}
