package fr.river.systemsexperts.ihm.implentation;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import fr.river.systemsexperts.ihm.HumanInterface;
import fr.river.systemsexperts.moteur.IFact;
import fr.river.systemsexperts.moteur.Motor;
import fr.river.systemsexperts.moteur.implementation.Rule;

public class Program implements HumanInterface {
		  
	public static void main(String[] args) {
		Program p = new Program();
		p.run();
	}
	
	public void run(){
		//Moteur
		System.out.println("**Cr�ation du moteur **");
		Motor m = new Motor(this);
		
		//R�gles
		System.out.println("**Ajout des r�gles **");
		m.addRule("R1 : IF (Ordre=3(Quel est l'ordre ?)) THEN Triangle");
		m.addRule("R2 : IF (Triangle AND Angle Droit(La figure a-t-elle au moins un angle droit ?)) THEN Triangle Rectangle");
        m.addRule("R3 : IF (Triangle AND Cotes Egaux=2(Combien la figure a-t-elle de c�t�s �gaux ?)) THEN Triangle Isoc�le");
        m.addRule("R4 : IF (Triangle Rectangle AND Triangle Isoc�le) THEN Triangle Rectangle Isoc�le");
        m.addRule("R5 : IF (Triangle AND Cotes Egaux=3(Combien la figure a-t-elle de c�t�s �gaux ?)) THEN Triangle Equilateral");
        m.addRule("R6 : IF (Ordre=4(Quel est l'ordre ?)) THEN Quadrilat�re");
        m.addRule("R7 : IF (Quadrilat�re AND Cotes Paralleles=2(Combien y'a-t-il de c�t�s parall�les entre eux - 0, 2 ou 4)) THEN Trapeze");
        m.addRule("R8 : IF (Quadrilat�re AND Cotes Paralleles=4(Combien y'a-t-il de c�t�s parall�les entre eux - 0, 2 ou 4)) THEN Parall�logramme");
        m.addRule("R9 : IF (Parall�logramme AND Angle Droit(La figure a-t-elle au moins un angle droit ?)) THEN Rectangle");
        m.addRule("R10 : IF (Parall�logramme AND Cotes Egaux=4(Combien la figure a-t-elle de c�t�s �gaux ?)) THEN Losange");
        m.addRule("R11 : IF (Rectangle AND Losange THEN Carr�");
        
        //R�solution
        while(true){
        	System.out.println("\n** R�solution **");
        	m.solve();
        }
	}
	
	@Override
	public int askIntValue(String question) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println(question);
		int value = in.nextInt();
		return value;
	}

	@Override
	public Boolean askBoolValue(String question) {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println(question + " (yes, no)");
		String value = in.nextLine();
		if ("yes".equalsIgnoreCase(value)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void printFacts(List<IFact> facts) {
		String res = "Solution(s) trouv�e(s) : \n";

		// Tri en fonction de level d�croissant
		Collections.sort(facts);

		for (IFact iFact : facts) {
			if (iFact.getLevel()>0){
				res += iFact.toString() + "\n";
			}
		}
		System.out.println(res);
	}

	@Override
	public void printRules(List<Rule> rules) {
		String res = "";
		for (Rule rule : rules) {
			res += rule.toString() + "\n";
		}
		System.out.println(res);
	}

}
