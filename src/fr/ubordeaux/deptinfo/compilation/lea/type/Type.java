package fr.ubordeaux.deptinfo.compilation.lea.type;

import java.util.Map;

import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.Code;
import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.CodeException;
import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.ExprMINUS;
import fr.ubordeaux.deptinfo.compilation.lea.environment.MapEnvironment;

// Type:
// Représentation d'un type simple ou d'un type complexe

//Pour l'instant cet arbre contient:
//- Le code (qu'on trouve dans TypeCode)
//- Sa structure syntaxique (left, right)
//- Le type (par exemple d'une expression)
//- La ligne et la colonne du code source correspondant


// Pour vérifier un type:
// assertType vérifie que deux types sont égaux ou
// qu'un type soit égal à un type donné

// Cette interface s'utilise avec l'implémentation
// TreeType
//
// Exemple:
// Type type = new TreeType(TypeCode.STRING);

public interface Type {

	TypeCode getTypeCode();
	boolean equals(Object object);
	void assertType(Code code, Type e) throws TypeException;
	void assertType(Code code, TypeCode typeCode) throws TypeException;
	void assertType(Code code, TypeCode[] typeCodes) throws TypeException;
	String generatePrefCode();
	String generateSuffCode() throws CodeException;
	int getSize();
	int getOffset();
	void setOffset(int i);
	Type getLeft();
	Type getRight();
	Object getData();
}
