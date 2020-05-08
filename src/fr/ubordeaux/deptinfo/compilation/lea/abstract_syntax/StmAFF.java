package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public class StmAFF extends Stm {

	private Expr lhs;
	private Expr rhs;

	public StmAFF(Expr lhs, Expr rhs, int line, int column) {
		super(line, column);
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public String generateCode() throws CodeException {
		String result = "";
		result += super.generateCode();
		String left = "";
		String right = "";
		boolean leftAdress = false;
		boolean rightAdress = false;
		
		if (lhs.isVARIABLE()
				|| lhs.isARRAY()
				|| lhs.isDOT()) {
			left = lhs.generateCode();
		}
		else if (lhs.isPOINTER()) {
			left = ((ExprPOINTER)lhs).getPointerCode();
			leftAdress = true;
		}
		else {
			throw new CodeException("affectation non prévue StmAFF(35)" .concat(lhs.getClass().getSimpleName()));
		}
		
		if (rhs.isVALUE()
				|| rhs.isVARIABLE() 
				|| rhs.isARRAY()
				|| rhs.isDOT()
				|| rhs.isOP()) {
			right = rhs.generateCode();
		}
		else if (rhs.isPOINTER()) {
			right = ((ExprPOINTER)rhs).getPointerCode();
			rightAdress = true;
		}
		else if (rhs.isADRESS()) {
			right = ((ExprADDRESS)rhs).getAdressCode();
			rightAdress = true;
		}
		else {
			throw new CodeException("affectation non prévue StmAFF(54)" .concat(rhs.getClass().getSimpleName()));
		}
		
		switch (rhs.getTypeCode()) {
		case BOOLEAN:
		case FLOAT:
		case INTEGER:
		case ENUM:
			if (leftAdress && rightAdress) {
				//#include <string.h>
				//void *memcpy(void *dest, const void *src, size_t n);
				result += tab() + "memcpy(" + left + ", " + right + ", " + lhs.getType().getSize() + ");" + NL;
			}
			else if (leftAdress) {
				if (lhs.isPOINTER()) {
					result += tab() + "*" + left + " = " + right + ";" + NL;
				}
				else {
					result += tab() + left + " = " + right + ";" + NL;
				}
			}
			else {
				result += tab() + left + " = " + right + ";" + NL;
			}
			break;

		case POINTER:
			//result += NL + "//" + leftAdress + "-" + rightAdress + NL;
			//result += "//" + lhs.getTypeCode() + "-" + rhs.getTypeCode() + NL;
			//result += "//" + lhs.getClass().getSimpleName() + "-" + rhs.getClass().getSimpleName() + NL;

			if ((lhs.isARRAY() || lhs.isDOT()) && rhs.isVARIABLE()) {
				//#include <string.h>
				//void *memcpy(void *dest, const void *src, size_t n);
				result += tab() + "memcpy(" + left + ", " + right + ", " + lhs.getType().getSize() + ");" + NL;
			}
			else {
				result += tab() + left + " = " + right + ";" + NL;
			}
			break;

		case STRING:
			//char *__string_657_ = "src";
			// int _iterator_657_ = 0;
			//for (char *i = __string_657_ ; *i ; i++) {
			// (*(dest + _iterator_657_++)) = *(i);
			//}
			result += tab() + "char *__string_" + getId() + "_ = " + right + ";" + NL;
			result += tab() + "int __iterator_" + getId() + "_ = 0;" + NL;
			result += tab() + "for (char *__i_ = __string_" + getId() + "_ ; *__i_ ; ++__i_)" + NL;
			if (left.substring(0,  1).equals("*")) {
				result += tab() + "\t(*(" + left.substring(1) + " + __iterator_" + getId() + "_++)) = *__i_;" + NL;
			}
			else {
				result += tab() + "\t(*(" + left + " + __iterator_" + getId() + "_++)) = *__i_;" + NL;
			}
			break;

		case STRUCT:
			result += tab() + "memcpy(" + left + ", " + right + ", " + lhs.getType().getSize() + ");" + NL;
			break;

		default:
			throw new CodeException("affectation");
		}
		return result;
	}

	@Override
	public void checkType() throws TypeException {
		lhs.checkType();
		rhs.checkType();
		if (!(lhs.getType().equals(rhs.getType())))
			throw new TypeException("affectation: " + lhs.getType() + " différent de " + rhs.getType(), line, column);
	}

	@Override
	public String toString() {
		return lhs + " = " + rhs;
	}

}
