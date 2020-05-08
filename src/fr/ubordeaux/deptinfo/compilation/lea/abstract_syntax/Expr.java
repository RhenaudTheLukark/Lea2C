package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public abstract class Expr extends Code {

	private Type type;

	public Expr(int line, int column) throws TypeException {
		super(line, column);
	}

	public abstract void checkType() throws TypeException;
	public abstract Object constEval(Environment<ExprVALUE> const_environment) throws EnvironmentException, TypeException;

	public Type getType() {
		return type;
	}

	public TypeCode getTypeCode() {
		return type.getTypeCode();
	}

	public int getSize() {
		return type.getSize();
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public void indent() {
	}

	protected boolean isVARIABLE() {
		return this instanceof ExprVARIABLE;
	}

	public boolean isPOINTER() {
		return this instanceof ExprPOINTER;
	}

	public boolean isADRESS() {
		return this instanceof ExprADDRESS;
	}

	public boolean isDOT() {
		return this instanceof ExprDOT;
	}

	public boolean isARRAY() {
		return this instanceof ExprARRAY;
	}

	public boolean isVALUE() {
		return this instanceof ExprVALUE;
	}

	protected boolean isOP() {
		return this instanceof ExprPLUS
				|| this instanceof ExprMINUS
				|| this instanceof ExprMUL
				|| this instanceof ExprDIV
				|| this instanceof ExprMOD
				|| this instanceof ExprUMINUS
				|| this instanceof ExprAND
				|| this instanceof ExprOR
				|| this instanceof ExprNOT
				|| this instanceof ExprLT
				|| this instanceof ExprLE
				|| this instanceof ExprGT
				|| this instanceof ExprGE
				|| this instanceof ExprEQ
				|| this instanceof ExprNE;
		}

	public String generateCode() throws CodeException {
		// Produit un commentaire sommaire pour rappeler
		// l'expression du langage Léa
		return NL + tab() + "/* " + this.toString() + " */" + NL;
	}

}
