package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StmPRINTLN extends Stm {

	private Expr expr;

	public StmPRINTLN(Expr expr, int line, int column) {
		super(line, column);
		this.expr = expr;
	}

	@Override
	public String generateCode() throws CodeException {
		String result = "";
		result += super.generateCode();
		
		switch (expr.getTypeCode()) {
		case FLOAT:
			result += tab() + "printf(\"%f\\n\", ";
			result += expr.generateCode() + ");" + NL;
			break;
		case INTEGER:
		case ENUM:
			result += tab() + "printf(\"%d\\n\", ";
			result += expr.generateCode() + ");" + NL;
			break;
		case BOOLEAN:
			result += tab() + "printf(\"%s\\n\", (";
			result += expr.generateCode() + " == 1) ? \"true\" : \"false\");" + NL;
			break;
		case STRING:
			result += tab() + "printf(\"%s\\n\", ";
			result += expr.generateCode() + ");" + NL;
			break;
		case POINTER:
			result += tab() + "printf(\"%lx\\n\", (unsigned long)";
			if (expr.isPOINTER())
				result += ((ExprPOINTER)expr).getPointerCode() + ");" + NL;
			else
				result += expr.generateCode() + ");" + NL;
			break;
		case ARRAY:
			result += tab() + "printf(\"ARRAY_%lx\\n\", (unsigned long)";
			result += expr.generateCode() + ");" + NL;
			break;
		case STRUCT:
			result += tab() + "printf(\"STRUCT_%lx\\n\", (unsigned long)";
			result += expr.generateCode() + ");" + NL;
			break;
		default:
			throw new CodeException("print");
		}
		return result;
	}

	@Override
	public void checkType() throws TypeException {
		expr.checkType();
	}

	@Override
	public String toString() {
		return "println " + expr;
	}

}
