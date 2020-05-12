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
		
		String label_debut = "_while_label_debut__" + this.getId();
		String label_then = "_while_label_then__" + this.getId();
		String label_fin = "_while_label_fin__" + this.getId();

		result += tab() + label_debut + ": {" + NL;
			incIndent();

			result += tab() + "if (" + test.generateCode() + ")" + NL;
				incIndent();
				result += tab() + "goto " + label_then + ";" + NL;
				decIndent();
			result += tab() + "else" + NL;
				incIndent();
				result += tab() + "goto " + label_fin + ";" + NL;
				decIndent();
			
			result += tab() + label_then + ": {" + NL;
				incIndent();
				result += getSon().generateCode();
				result += tab() + "goto " + label_debut + ";" + NL;
				decIndent();
			result += tab() + "}" + NL;
			
			decIndent();
		result += tab() + "}" + NL;
		
		result += tab() + label_fin + ": { }" + NL;
		
		return result;
	}

	@Override
	public void checkType() throws TypeException {
		test.checkType();
		if (this.getSon() != null)
			getSon().checkType();
		test.getType().assertType(this, TypeCode.BOOLEAN);
	}

	@Override
	public String toString() {
		return "while ("+ test + ")";
	}

}
