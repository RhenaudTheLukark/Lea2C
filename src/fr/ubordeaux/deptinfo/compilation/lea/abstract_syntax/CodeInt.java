package fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax;

import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;

public interface CodeInt {
	// Vérifie le type
	void checkType() throws TypeException;
	
	// Produit le code C correspondant
	String generateCode() throws CodeException;
}
