package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeCode;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StmCASE extends StmUnary {

	private Expr expr;

	public StmCASE(Expr expr, Stm succeed, int line, int column) {
		super(succeed, line, column);
		this.expr = expr;
	}

	public StmCASE(Stm succeed, int line, int column) {
		super(succeed, line, column);
	}

	@Override
	public String generateCode() throws CodeException {
		return getSon().generateCode();
	}

	@Override
	public void checkType() throws TypeException {
		if (expr != null) {
			expr.checkType();
			TypeCode[] typeCodes = {TypeCode.INTEGER, TypeCode.ENUM};
			expr.getType().assertType(this, typeCodes);
		}
		if (this.getSon()!=null)
			getSon().checkType();
	}

	public Expr getExpr() {
		return expr;
	}

	@Override
	public String toString() {
		return "case " + expr + ":";
	}

}
