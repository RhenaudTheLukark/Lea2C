package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import java.util.Map;

import fr.ubordeaux.deptinfo.compilation.lea.environment.*;
import fr.ubordeaux.deptinfo.compilation.lea.type.*;

public class ExprDOT extends ExprUnary {

	private String name;

	public ExprDOT(Expr son, String name, int line, int column) throws Exception {
		super(son, line, column);
		this.name = name;
		Type type = findSlot(name);
		if (type == null)
			throw new TypeException("champs inconnu: " + name, line, column);
		setType(type);
	}

	private Type findSlot(String name) {
		@SuppressWarnings("unchecked")
		Map<String, Type> map = (Map<String, Type>) getSon().getType().getData();
		Type type = map.get(name);
		return type;
	}

	@Override
	public void checkType() throws TypeException {
		this.getSon().checkType();
		getSon().getType().assertType(this, TypeCode.STRUCT);
	}

	@Override
	public String generateCode() throws CodeException {
		String result = "";
		String lhs = "";
		if (getSon().isPOINTER()) {
			lhs = ((ExprPOINTER)getSon()).getPointerCode();
		} 
		else {
			lhs = getSon().generateCode();
		}
		switch (getTypeCode()) {
		case BOOLEAN:
		case ENUM:
		case FLOAT:
		case INTEGER:
			result += "*(" + lhs + "+" + this.getType().getOffset() + ")";
			break;
		case POINTER:
		case STRING:
		case STRUCT:
			result += "(" + lhs + "+" + this.getType().getOffset() + ")";
			break;
		default:
			throw new CodeException("***erreur système" + getType().getTypeCode());
		}
		return result;
	}

	@Override
	public Object constEval(Environment<ExprVALUE> environment) throws EnvironmentException, TypeException {
		throw new TypeException("erreur système", line, column);
	}

	@Override
	public String toString() {
		return this.getSon() + "." + name;
	}

	
}
