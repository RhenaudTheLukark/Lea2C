package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public abstract class ExprBinary extends Expr {

	private Expr left;
	private Expr right;

	public ExprBinary(Expr left, Expr right, int line, int column) throws TypeException {
		super(line, column);
		this.left = left;
		this.right = right;
	}

	public Expr getLeft() {
		return left;
	}

	public Expr getRight() {
		return right;
	}

}
