package fr.river.systemsexperts.factory;

import fr.river.systemsexperts.moteur.IFact;
import fr.river.systemsexperts.moteur.Motor;
import fr.river.systemsexperts.moteur.implementation.BoolFact;
import fr.river.systemsexperts.moteur.implementation.IntFact;

public class FactsFactory {
	
	public static IFact fact(IFact f, Motor m){
		IFact newFact;
		if(f instanceof IntFact){
			int value = m.getIhm().askIntValue(f.question());
			newFact = new IntFact(f.name(), value, 0, null);
		}else {
			Boolean value = m.getIhm().askBoolValue(f.question());
			newFact = new BoolFact(f.name(), value, 0, null);
		}		
		return newFact;
	}
	
	public static IFact fact(String factStr){
		factStr = factStr.trim();
		
		if(factStr.contains("=")){
			String delimiters = "=|\\(|\\)";
			String[] nameValue = factStr.split(delimiters);
			if(nameValue[0].equalsIgnoreCase("")){
				String[] nameTmp = new String[nameValue.length -1];
				for(int i = 1; i<nameValue.length ;i++){
					nameTmp[i-1] = nameValue[i];
				}
				nameValue = nameTmp;
			}
			if(nameValue.length >= 2){
				String question = null;
				if(nameValue.length == 3){
					question = nameValue[2].trim();
				}
				return new IntFact(nameValue[0].trim(), Integer.parseInt(nameValue[1].trim()), question);
			}else{
				return null;
			}
		}else {
			String delimiters = "\\(|\\)";
			Boolean value = true;
			if(factStr.startsWith("!")){
				value = false;
				factStr = factStr.substring(1).trim();
			}
			String[] nameQuestion = factStr.split(delimiters);
			if(nameQuestion[0].equalsIgnoreCase("")){
				String[] nameTmp = new String[nameQuestion.length -1];
				for(int i = 1; i<nameQuestion.length ;i++){
					nameTmp[i-1] = nameQuestion[i];
				}
				nameQuestion = nameTmp;
			}
			String question = null;
			if(nameQuestion.length==2){
				question = nameQuestion[1].trim();
			}
			return new BoolFact(nameQuestion[0].trim(), value, question);
		}
	}
}
