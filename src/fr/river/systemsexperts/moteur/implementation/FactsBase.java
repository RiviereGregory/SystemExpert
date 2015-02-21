package fr.river.systemsexperts.moteur.implementation;

import java.util.ArrayList;
import java.util.List;

import fr.river.systemsexperts.moteur.IFact;

public class FactsBase {
	private List<IFact> facts;

	public FactsBase() {
		super();
		this.facts = new ArrayList<>();
	}

	public List<IFact> getFacts() {
		return facts;
	}

	public void clear() {
		facts.clear();
	}

	public void addFact(IFact f) {
		facts.add(f);
	}

	public IFact search(String name) {
		int index =-1;
		for(IFact iFact : facts){
			index = iFact.name().indexOf(name);
			if (index != -1){
				return iFact;
			}
		}
		
		return null;	
	}

	public Object value(String name) {
		IFact f = facts.get(facts.indexOf(name));
		if (f != null) {
			return f.value();
		} else {
			return null;
		}
	}
}
