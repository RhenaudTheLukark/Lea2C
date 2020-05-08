package fr.ubordeaux.deptinfo.compilation.lea.type;

public enum TypeCode {
	LIST(0, "", ""),
	FUNCTION(0, "", ""),
	INTEGER(2, "short int ", ""),
	FLOAT(4, "float ", ""),
	STRING(256, "char ", "[256]"),
	POINTER(4, "*", ""),
	BOOLEAN(1, "char ", ""), 
	ARRAY(0, "", ""), 
	STRUCT(0, "char ", ""), 
	PRODUCT(0, "", ""), 
	FEATURE(0, "", ""), 
	ENUM(2, "short int ", ""), 
	NAME(0, "", ""), 
	VOID (0, "void ", "");
	
	private int size;
	private String pref;
	private String suff;
	
	TypeCode(int size, String pref, String suff) {
		this.size = size;
		this.pref = pref;
		this.suff = suff;
	}

	public String getPref() {
		return pref;
	}

	public String getSuff() {
		return suff;
	}

	public int getSize() {
		return size;
	}
	
}
