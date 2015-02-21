package fr.river.systemsexperts.moteur.implementation;

import java.util.ArrayList;
import java.util.List;

public class RulesBase {

	public RulesBase() {
		super();
		rules = new ArrayList<>();
	}

	private List<Rule> rules;

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public void clearBase() {
		rules.clear();
	}

	public void addRule(Rule r) {
		rules.add(r);
	}

	public void remove(Rule r) {
		rules.remove(r);
	}
}
