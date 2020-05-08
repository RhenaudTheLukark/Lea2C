package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import java.util.List;

public abstract class StmList extends Stm {

	private List<Stm> stms;

	public StmList(List<Stm> stms, int line, int column) {
		super(line, column);
		this.stms = stms;
	}

	public List<Stm> getStms() {
		return stms;
	}

}
