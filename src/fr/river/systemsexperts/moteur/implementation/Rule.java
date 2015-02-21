package fr.river.systemsexperts.moteur.implementation;

import java.util.List;

import fr.river.systemsexperts.moteur.IFact;

public class Rule {
	
	public Rule(List<IFact> premises, IFact conclusion, String name) {
		super();
		this.premises = premises;
		this.conclusion = conclusion;
		this.name = name;
	}
	
	private List<IFact> premises;
	private IFact conclusion;
	private String name;
	
	public List<IFact> getPremises() {
		return premises;
	}
	public void setPremises(List<IFact> premises) {
		this.premises = premises;
	}
	public IFact getConclusion() {
		return conclusion;
	}
	public void setConclusion(IFact conclusion) {
		this.conclusion = conclusion;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		String res = "";
		res += name +" : IF (";
		for (int i = 0; i<premises.size();i++) {
			res += premises.get(i).toString();
			if(i<(premises.size()-1)){
				res += " AND ";
			}
		}
		res += ") THEN " + conclusion.toString();
		
		return res;
	}
	
}
