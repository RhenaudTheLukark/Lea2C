package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import java.util.Iterator;
import java.util.List;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeCode;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StmSWITCH extends StmList {

	private Expr expr;
	private Stm defaultStm; // default

	public StmSWITCH(Expr expr, List<Stm> stms, Stm stm, int line, int column) {
		super(stms, line, column);
		this.expr = expr;
		this.defaultStm = stm;
	}

	@Override
	public String generateCode() throws CodeException {
		String result = "";
		result += super.generateCode();

		//init
		String checkVal = "switch_check_" + getId();
		String endLabel = "switch_end_" + getId();
		result += tab() + "int " + checkVal + ";" + NL;

		for (int caseNum = 0; caseNum < getStms().size(); caseNum++) {
			String caseLabel = "switch_" + getId() + "_case_" + caseNum;
			Expr caseExpr = ((StmCASE)getStms().get(caseNum)).getExpr();

			result += NL + tab() + "//case " + caseExpr + NL;

			//check case
			result += tab() + checkVal + " = " + expr + " != " + caseExpr.generateCode() + ";" + NL;
			result += tab() + "if (" + checkVal + ") goto " + caseLabel + ";" + NL;

			//case code
			result += getStms().get(caseNum).generateCode();

			//next case/switch end
			result += tab() + "goto " + endLabel + ";" + NL;
			result += tab() + caseLabel + ":" + NL;
		}
		
		//default
		result += defaultStm.generateCode();
		//end
		result += tab() + endLabel + ":" + NL;

		return result;
	}

	@Override
	public void checkType() throws TypeException {
		expr.checkType();
		TypeCode[] typeCodes = {TypeCode.INTEGER, TypeCode.ENUM};
		expr.getType().assertType(this, typeCodes);

		if (defaultStm != null)
			defaultStm.checkType();
		Iterator<Stm> iterator = getStms().iterator();
		while (iterator.hasNext()) {
			StmCASE stmCASE = (StmCASE)iterator.next();
			stmCASE.checkType();
			stmCASE.getExpr().getType().assertType(stmCASE, expr.getType());
			
		}
	}

	@Override
	public void indent() {
		Iterator<Stm> iterator = getStms().iterator();
		while (iterator.hasNext()) {
			Stm stm = iterator.next();
			stm.setIndent(getIndent());
			stm.indent();
		}
		if (defaultStm != null) {
			defaultStm.setIndent(getIndent());
			defaultStm.indent();
		}
	}

	@Override
	public String toString() {
		return "switch (" + expr + ")";
	}

}
