package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

public abstract class StmTernary extends Stm {

	private Stm first;
	private Stm second;
	private Stm third;

	public StmTernary(Stm first, Stm second, Stm third, int line, int column) {
		super(line, column);
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public Stm getFirst() {
		return first;
	}

	public Stm getSecond() {
		return second;
	}

	public Stm getThird() {
		return third;
	}

	@Override
	public void indent() {
		if (this.first != null) {
			first.setIndent(getIndent());
			first.indent();
		}
		if (second != null) {
			second.setIndent(getIndent());
			second.indent();
		}
		if (third != null) {
			third.setIndent(getIndent());
			third.indent();
		}
	}
}
