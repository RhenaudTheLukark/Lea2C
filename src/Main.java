/*
 * 
 * Lionel Clément 
 * 12/04/2020
 * Léa Projet de Compilation
 * 
 * Ne pas éditer ce fichier
 * 
 * */



import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.CodeInt;
import fr.ubordeaux.deptinfo.compilation.lea.abstract_syntax.CodeException;
import fr.ubordeaux.deptinfo.compilation.lea.environment.EnvironmentException;
import fr.ubordeaux.deptinfo.compilation.lea.parser.Parser;
import fr.ubordeaux.deptinfo.compilation.lea.tokenizer.Tokenizer;
import fr.ubordeaux.deptinfo.compilation.lea.type.TypeException;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;


public class Main {

	public static void main(String[] args) {
		java.io.FileInputStream stream;
		try {
			stream = new java.io.FileInputStream(args[0]);
			java.io.Reader reader = new java.io.InputStreamReader(stream, "UTF-8");
			Tokenizer tokenizer = new Tokenizer(reader);
			SymbolFactory symbolFactory = new ComplexSymbolFactory();
			Parser parser = new Parser(tokenizer, symbolFactory);
			Symbol code = parser.parse();
			System.err.println("--- Analyse OK");
		} catch (FileNotFoundException e) {
			System.err.println("--- Fichier introuvable");
		} catch (UnsupportedEncodingException e) {
			System.err.println("--- Problème d'encodage");
			System.err.println("--- Erreur d'analyse syntaxique");
		} catch (EnvironmentException e) {
			System.err.println(e.getMessage());
		} catch (TypeException e) {
			System.err.println(e.getMessage());
		} catch (CodeException e) {
			System.err.println(e.getMessage());
			//e.printStackTrace();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
