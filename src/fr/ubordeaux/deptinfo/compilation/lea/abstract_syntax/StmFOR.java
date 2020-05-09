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
	public String generateCode() throws CodeException {
		String result = "";
		result += super.generateCode();

		String var_test = "_var_for_test__" + this.getId();
		String label_for_test = "_for_label_test_" + this.getId();
		String label_for_code = "_for_label_code_" + this.getId(); 
		String label_for_end = "_for_label_end_" + this.getId(); 
		
		result += tab() + getFirst().generateCode() + NL; //initialization
		result += tab() + "int " + var_test + ";" + NL;
		
		result += tab() + label_for_test + ":{}" + NL;
		
		result += tab() + var_test + " = " + expr.generateCode() + ";" + NL;
		
		result += tab() + "if (" + var_test + ")" + NL; //stopping condition
		incIndent();
			result += tab() + "goto " + label_for_code + ";" + NL;
		decIndent();
		result += tab() + "goto " + label_for_end + ";" + NL;
		result += tab() + label_for_code + ":{" + NL;
		incIndent();
			result += getThird().generateCode();
			result += getSecond().generateCode(); //increment
			result += tab() + "goto " + label_for_test + ";" + NL;
		decIndent();
		result += tab() + "}" + NL;
		result += tab() + label_for_end + ":{}" + NL;
		
		return result;
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
	public String toString() {
		return "for (... ; ... ; ...)";
	}

}
