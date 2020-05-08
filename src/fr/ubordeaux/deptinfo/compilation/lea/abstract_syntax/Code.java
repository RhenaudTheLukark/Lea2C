package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

public abstract class Code implements CodeInt {

	private int id;
	private static int staticId;
	private int indent;
	protected int line;
	protected int column;
	protected static String NL = "\n";

	public Code(int line, int column) {
		this.line = line;
		this.column = column;
		this.id = Code.staticId++;
	}

	public int getLine() {
		return this.line;
	}

	public int getColumn() {
		return this.column;
	}

	public int getIndent() {
		return indent;
	}

	public void setIndent(int indent) {
		this.indent = indent;
	}

	public abstract String generateCode() throws CodeException;

	public String tab() {
		String result = "";
		for (int i=1 ; i <= indent*3 ; ++i)
			result += " ";
		return result;
	}
	
	public void incIndent() {
		++indent;
		indent();
	}

	public void decIndent() {
		--indent;
		indent();
	}

	public int getId() {
		return id;
	}

	public abstract void indent();

}
