package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeCode;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StmWHILE extends StmUnary {

	private Expr test;

	public StmWHILE(Expr test, Stm stm, int line, int column) {
		super(stm, line, column);
		this.test = test;
	}

	@Override
	public String generateCode() throws CodeException {
		String result = "";
		result += super.generateCode();
		// TODO
		result += tab() + "_WHILE_" + this.getId() + ":" + NL;
		this.incIndent();
		result += tab() + "// Code WHILE ici..." + NL;
		result += tab() + "printf(\"--- Manque WHILE...\\n\");" + NL;
		this.decIndent();
		return result;
	}

	@Override
	public void checkType() throws TypeException {
		test.checkType();
		if (this.getSon()!=null)
			getSon().checkType();
		test.getType().assertType(this, TypeCode.BOOLEAN);
	}

	@Override
	public String toString() {
		return "while ("+ test + ")";
	}

}
