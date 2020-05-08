package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

public abstract class StmBinary extends Stm {

	private Stm left;
	private Stm right;

	public StmBinary(Stm left, Stm right, int line, int column) {
		super(line, column);
		this.left = left;
		this.right = right;
	}

	public Stm getLeft() {
		return left;
	}

	public Stm getRight() {
		return right;
	}

	//@Override
	public void indent() {
		if (left != null) {
			left.setIndent(getIndent());
			left.indent();
		}
		if (right != null) {
			right.setIndent(getIndent());
			right.indent();
		}
	}

}
