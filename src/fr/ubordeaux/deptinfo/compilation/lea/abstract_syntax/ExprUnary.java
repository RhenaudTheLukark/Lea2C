package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public abstract class ExprUnary extends Expr {

	private Expr son;

	public ExprUnary(Expr son, int line, int column) throws TypeException {
		super(line, column);
		this.son = son;
	}

	public Expr getSon() {
		return son;
	}

}
