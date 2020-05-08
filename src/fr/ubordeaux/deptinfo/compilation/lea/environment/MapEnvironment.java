package fr.ubordeaux.deptinfo.compilation.lea.environment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.CodeException;
import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.Expr;
import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.ExprVALUE;
import fr.ubordeaux.deptinfo.compilation.lea.type.Type;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeCode;

public class MapEnvironment<T> implements Environment<T> {

	private static final String NL = "\n";
	private Map<String, T> map;
	private String name;
	private boolean isArg;

	public MapEnvironment(String name, boolean isArg) {
		super();
		map = new HashMap<String, T>();
		this.name = name;
		this.isArg = isArg;
	}

	public void put(String id, T t) {
		System.err.println ("*** Add " + id + ":" + t + " in " + this.getName());
		map.put(id, t);
	}

	public void put(List<String> list, T t) {
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext())  {
			String id = iterator.next();
			put(id, t);
		}
	}

	private String getName() {
		return this.name;
	}

	public T get(String id) {
		T t = map.get(id);
		System.err.println ("*** Get " + id + ":" + t + " from " + this.getName());
		return t;
	}

	public String toString() {
		String result = "(" + NL;
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String)iterator.next();
		    T value = map.get(key);
			result += key + ": " + value.toString() + NL;
		}
		result += ")";
		return result;
	}

	@Override
	public String generateCode() throws CodeException {
		String result = "";
		boolean first = true;
			for (String str: map.keySet()) {
				T t = map.get(str);
				if (t instanceof Type) {
					if (!first && this.isArg)
						result += ", ";
					else
						first = false;
					Type type = (Type)t;
					if (type != null)
						result += type.generatePrefCode();
					result += str;
					if (type != null)
						result += type.generateSuffCode();
					if (!this.isArg)
						result += ";" + NL;
					}
			}
		return result;
	}

}
