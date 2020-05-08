package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

public abstract class StmUnary extends Stm {

	private Stm son;

	public StmUnary(Stm son, int line, int column) {
		super(line, column);
		this.son = son;
	}

	public Stm getSon() {
		return son;
	}

	@Override
	public void indent() {
		if (son != null) {
			son.setIndent(this.getIndent());
			son.indent();
		}
	}

}
