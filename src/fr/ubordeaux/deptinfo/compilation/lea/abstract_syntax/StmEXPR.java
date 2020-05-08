package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StmEXPR extends Stm {

	private Expr expr;

	public StmEXPR(Expr expr, int line, int column) {
		super(line, column);
		this.expr = expr;
	}

	public Expr getExpr() {
		return expr;
	}

	@Override
	public String generateCode() throws CodeException {
		String result;
		result = expr.generateCode();
		return result;
	}

	@Override
	public String toString() {
		return expr.toString();
	}

	@Override
	public void checkType() throws TypeException {
	}

}
