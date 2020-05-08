package fr.ubordeaux.deptinfo.compilation.lea.type;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.Code;
import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.CodeException;
import fr.ubordeaux.deptinfo.compilation.lea.environment.Environment;
import fr.ubordeaux.deptinfo.compilation.lea.environment.MapEnvironment;

public class TreeType implements Type {

	private TypeCode typeCode;
	private Type left;
	private Type right;
	private Object data;
	private int size;
	private int offset;
	
	public TreeType(TypeCode typeCode, Type left, Type right) {
		this.typeCode = typeCode;
		this.left = left;
		this.right = right;
		setSize();
	}

	public TreeType(TypeCode typeCode, Type left) {
		this.typeCode = typeCode;
		this.left = left;
		setSize();
	}

	public TreeType(TypeCode typeCode) {
		this.typeCode = typeCode;
		setSize();
	}

	public TreeType(TypeCode typeCode, Type left, Object data) {
		this.typeCode = typeCode;
		this.left = left;
		this.data = data;
		setSize();
	}

	public TreeType(TypeCode typeCode, Object data) {
		this.typeCode = typeCode;
		this.data = data;
		setSize();
	}

	public TreeType(Type t) {
		this.typeCode = t.getTypeCode();
		if (t.getLeft() != null)
			this.left = new TreeType(t.getLeft());
		if (t.getRight() != null)
			this.right = new TreeType(t.getRight());
		this.data = t.getData();
		this.size = t.getSize();
		this.offset = t.getOffset();
	}

	@Override
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public Type getLeft() {
		return left;
	}

	@Override
	public Type getRight() {
		return right;
	}

	private void setSize() {
		switch (typeCode) {
		case ARRAY:
			size = (Integer)data * left.getSize();
			break;
		case BOOLEAN:
		case FLOAT:
		case INTEGER:
		case POINTER:
		case ENUM:
		case STRING:
			size = typeCode.getSize();
			break;
		case FEATURE:
			size = left.getSize();
			break;
		case PRODUCT:
			size = left.getSize() + right.getSize();
			break;
		case STRUCT:
				Map<String, Type> map = (Map<String, Type>) data;
				Set<String> listKeys = map.keySet();
				Iterator<String> iterator = listKeys.iterator();
				while(iterator.hasNext()) {
					String key = iterator.next();
					size += map.get(key).getSize();
				}
			break;
		case FUNCTION:
			break;
		case LIST:
			break;
		case NAME:
			break;
		default:
			break;
		}		
	}

	@Override
	public String toString() {
		switch (typeCode) {
		case FUNCTION:
			return " -> " + left;
		case INTEGER:
			return "integer";
		case LIST:
			return "list(" + left + ")";
		case STRING:
			return "string";
		case BOOLEAN:
			return "boolean";
		case ARRAY:
			return "array(" + left + ")";
		case ENUM:
			return "enum(" + data.toString() + ")";
		case POINTER:
			return "pointer(" + left + ")";
		case STRUCT:
			String s1 = "data:" + data;
			String s2 = "size:" + size;
			String s3 = "id:" + offset;
			return "struct[" + s1 + ", " + s2 + ", " + s3 + "]";
		case PRODUCT:
			return left + "" + right;
		case FEATURE:
			return data.toString() + ":" + left + ";";
		case FLOAT:
			return "float";
		case NAME:
			return "name(" + data.toString() + ")";
		}
		return null;
	}

	@Override
	public TypeCode getTypeCode() {
		return typeCode;
	}

	@Override
	public void assertType(Code code, Type type) throws TypeException {
		if (!this.equals(type))
			throw new TypeException("erreur de type: " + toString() + " ≠ " + type, code.getLine(), code.getColumn());
	}

	@Override
	public void assertType(Code code, TypeCode typeCode) throws TypeException {
		if (this.getTypeCode() != typeCode)
			throw new TypeException("erreur de type: " + toString(), code.getLine(), code.getColumn());
	}

	@Override
	public void assertType(Code code, TypeCode[] typeCodes) throws TypeException {
		boolean flag = false;
		for (TypeCode typeCode : typeCodes) {
			if (this.getTypeCode() == typeCode) {
				flag = true;
				break;
			}
		}
		if (!flag) {
			String types = "{";
			for (TypeCode typeCode : typeCodes) {
				types += typeCode.toString() + " ";
			}
			types += "}";
			throw new TypeException("erreur de type: " + toString() + " ≠ " + types, code.getLine(), code.getColumn());
		}
	}

	@Override
	public String generatePrefCode() {
		String result = "";
		switch (typeCode) {
		case ARRAY:
			result += left.generatePrefCode();
			break;
		case BOOLEAN:
		case ENUM:
		case FLOAT:
		case INTEGER:
		case STRING:
		case STRUCT:
		case VOID:
			result += typeCode.getPref();
			break;
		case POINTER:
			result = left.generatePrefCode();
			result += typeCode.getPref();
			break;
		case FUNCTION:
			result += left.generatePrefCode();
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	public String generateSuffCode() throws CodeException {
		String result = "";
		switch (typeCode) {
		case ARRAY:
			result = "[" + data + "]" + left.generateSuffCode();
			break;
		case STRING:
			result = typeCode.getSuff();
			break;
		case STRUCT:
			result = "[" + size + "]";
			break;
		case FUNCTION:
			result += "(";
			result += ((Environment)data).generateCode();
			result += ")";
			
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime * result + size;
		result = prime * result + ((typeCode == null) ? 0 : typeCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeType other = (TreeType) obj;
		if ((typeCode == TypeCode.NAME) || (other.typeCode == TypeCode.NAME))
			return true;
		if (typeCode != other.typeCode)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

}
