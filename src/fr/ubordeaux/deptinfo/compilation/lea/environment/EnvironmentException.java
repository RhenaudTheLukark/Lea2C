package fr.ubordeaux.deptinfo.compilation.lea.environment;

public class EnvironmentException extends Exception {

	public EnvironmentException(String msg) {
		super("--- Erreur sur l'environnement " + msg);
	}

}
