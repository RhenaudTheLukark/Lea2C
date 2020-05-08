package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

public abstract class ExprStm extends Stm {

	private Stm son;

	public ExprStm(Stm son, int line, int column) {
		super(line, column);
		this.son = son;
	}

	public Stm getSon() {
		return son;
	}

}
