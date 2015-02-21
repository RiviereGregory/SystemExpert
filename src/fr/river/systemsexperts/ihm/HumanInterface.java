package fr.river.systemsexperts.ihm;

import java.util.List;

import fr.river.systemsexperts.moteur.IFact;
import fr.river.systemsexperts.moteur.implementation.Rule;

public interface HumanInterface {
	
	public int askIntValue(String question);
	public Boolean askBoolValue(String question);
	public void printFacts(List<IFact> facts);
	public void printRules(List<Rule> rules);

}
