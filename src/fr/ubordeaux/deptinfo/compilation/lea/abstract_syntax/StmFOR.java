package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeCode;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StmFOR extends StmTernary {

	private Expr expr;

	public StmFOR(Expr expr, Stm stm1, Stm stm2, Stm stm3, int line, int column) {
		super(stm1, stm2, stm3, line, column);
		this.expr = expr;
	}
	
	@Override
	public void checkType() throws TypeException {
		expr.checkType();
		if (this.getFirst() != null)
			this.getFirst().checkType();
		if (this.getSecond() != null)
			this.getSecond().checkType();
		if (this.getThird() != null)
			this.getThird().checkType();
		expr.getType().assertType(this, TypeCode.BOOLEAN);
	}

	@Override
	public String generateCode() throws CodeException {
		String result = "";
		result += super.generateCode();
		// TODO
		result += tab() + "_FOR_" + this.getId() + ":" + NL;
		this.incIndent();
		result += tab() + "// Code FOR ici..." + NL;
		result += tab() + "printf(\"--- Manque FOR...\\n\");" + NL;
		this.decIndent();
		return result;
	}

	@Override
	public String toString() {
		return "for (... ; ... ; ...)";
	}

}
