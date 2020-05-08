package fr.ubordeaux.deptinfo.compilation.lea.type;

public class TypeException extends Exception {
	
	private int line;
	private int column;

	public TypeException(String msg, int line, int column) {
		super(msg);
		this.line = line;
		this.column = column;
	}

	public String getMessage() {
		return "--- Erreur de typage " + super.getMessage() + " " + line + "(" + column + ")";
	}
}
