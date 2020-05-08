package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StmREADLN extends Stm {

	private Expr expr;

	public StmREADLN(Expr expr, int line, int column) {
		super(line, column);
		this.expr = expr;
	}

	@Override
	public String generateCode() throws CodeException {
		String result = "";
		result += super.generateCode();
		switch (expr.getType().getTypeCode()) {
		case ARRAY:
			break;
		case BOOLEAN:
			result += tab() + "scanf(\"%d\", &" + expr.generateCode() + "));" + NL;
			break;
		case ENUM:
			result += tab() + "scanf(\"%hu\", &" + expr.generateCode() + ");" + NL;
			break;
		case INTEGER:
			result += tab() + "scanf(\"%d\", &" + expr.generateCode() + ");" + NL;
			break;
		case FEATURE:
			break;
		case FLOAT:
			result += tab() + "scanf(\"%f\", &" + expr.generateCode() + ");" + NL;
			break;
		default:
			throw new CodeException("readln");
		}
		return result;
	}

	@Override
	public void checkType() throws TypeException {
		expr.checkType();
	}

	@Override
	public String toString() {
		return "readln...";
	}

}
