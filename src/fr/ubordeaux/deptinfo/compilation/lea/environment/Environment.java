package fr.ubordeaux.deptinfo.compilation.lea.environment;

import java.util.List;

import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.CodeException;

// Environement:
// Outil pour enregistrer une variable, le nom d'un type, etc.

// Cette interface s'utilise avec l'implémentation
// MapEnvironment
//
// Exemple:
// Environment variable_environment = new MapEnvironment<Type>();

public interface Environment<T> {

	// Ajoute une liste de variables à l'environnement
	void put(List<String> list, T value);
	
	// Ajoute une variable à l'environnement
	void put(String id, T value);
	
	// Retrouve une variable dans l'environnement
	T get(String id);

	// Code C généré
	String generateCode() throws CodeException;

}
