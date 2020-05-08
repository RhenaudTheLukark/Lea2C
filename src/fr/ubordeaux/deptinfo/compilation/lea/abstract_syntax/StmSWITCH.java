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
		// TODO
		result += tab() + "_SWITCH_" + this.getId() + ":" + NL;
		this.incIndent();
		result += tab() + "// Code SWITCH ici..." + NL;
		result += tab() + "printf(\"--- Manque SWITCH...\\n\");" + NL;
		this.decIndent();
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
