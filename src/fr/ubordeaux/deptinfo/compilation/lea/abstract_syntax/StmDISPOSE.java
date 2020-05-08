package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeCode;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StmDISPOSE extends Stm {

	private Expr expr;

	public StmDISPOSE(Expr expr, int line, int column) {
		super(line, column);
		this.expr = expr;
	}

	@Override
	public String generateCode() throws CodeException {
		String result = "";
		result += super.generateCode();
		switch (expr.getType().getTypeCode()) {
		case POINTER:
			result += tab() + "free(" + expr.generateCode() + ");" + NL;
			result += tab() + expr.generateCode() + " = 0;" + NL;
			break;
		default:
			throw new CodeException("dispose");
		
		}
		return result;
	}

	@Override
	public void checkType() throws TypeException {
		expr.checkType();
		expr.getType().assertType(this, TypeCode.POINTER);
		expr.checkType();
	}

	@Override
	public String toString() {
		return "dispose " + expr;
	}
}
