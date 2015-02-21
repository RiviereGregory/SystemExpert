package fr.river.systemsexperts.moteur;

import java.util.ArrayList;
import java.util.List;

import fr.river.systemsexperts.factory.FactsFactory;
import fr.river.systemsexperts.ihm.HumanInterface;
import fr.river.systemsexperts.moteur.implementation.FactsBase;
import fr.river.systemsexperts.moteur.implementation.Rule;
import fr.river.systemsexperts.moteur.implementation.RulesBase;
import fr.river.systemsexperts.tuple.Tuple;

public class Motor {
	private FactsBase fDB;
	private RulesBase rDB;
	private HumanInterface ihm;

	public Motor(HumanInterface ihm) {
		super();
		this.fDB = new FactsBase();
		this.rDB = new RulesBase();
		this.setIhm(ihm);
	}

	public int askIntValue(String p) {
		return ihm.askIntValue(p);
	}

	public Boolean askBoolValue(String p) {
		return ihm.askBoolValue(p);
	}

	private int canApply(Rule r) {
		int maxLevel = -1;

		for (IFact f : r.getPremises()) {
			IFact foundFact = null;

			foundFact = fDB.search(f.name());

			if (foundFact == null) {
				// ce fait n'existe pas dans la base actuellement
				if (f.question() != null) {
					// On le demande à l'utilisateur et on l'ajoute en base
					foundFact = FactsFactory.fact(f, this);
					fDB.addFact(foundFact);
					maxLevel = Math.max(maxLevel, 0);
				} else {
					// la règle ne s'applique pas
					return -1;
				}
			}

			// On a un fait en base
			if (!foundFact.value().equals(f.value())) {
				// Elle ne correspond pas
				return -1;
			} else {
				// Elle correspond
				maxLevel = Math.max(maxLevel, foundFact.level());
			}
		}

		return maxLevel;
	}

	private Tuple<Rule, Integer> FindUsableRule(RulesBase rBase) {
		for (Rule r : rBase.getRules()) {
			Integer level = canApply(r);
			if (level != -1) {
				Tuple<Rule, Integer> tuple = new Tuple<Rule, Integer>(r, level);
				return tuple;
			}
		}
		return null;
	}

	public void solve() {
		Boolean moreRules = true;
		RulesBase usableRules = new RulesBase();
		usableRules.setRules(new ArrayList<>(rDB.getRules()));
		fDB.clear();
		while (moreRules) {
			Tuple<Rule, Integer> t = FindUsableRule(usableRules);
			if (t != null) {
				IFact newFact = t.x.getConclusion();
				newFact.setLevel(t.y + 1);
				fDB.addFact(newFact);
				usableRules.remove(t.x);
			} else {
				moreRules = false;
			}
		}
		ihm.printFacts(fDB.getFacts());
	}

	public void addRule(String ruleStr) {
		String[] splitName = ruleStr.split(":");
		if (splitName.length == 2) {
			String name = splitName[0];
			String regex = "IF | THEN ";
			String[] splitIF = splitName[1].trim().split("IF ");
			String[] splitPremConcl = splitIF[1].trim().split(" THEN ");
			if (splitPremConcl.length == 2) {
				List<IFact> premises = new ArrayList<>();
				String[] premisesStr = splitPremConcl[0].split(" AND ");
				for (String prem : premisesStr) {
					IFact premise = FactsFactory.fact(prem);
					premises.add(premise);
				}
				String conclusionStr = splitPremConcl[1].trim();
				IFact conclusion = FactsFactory.fact(conclusionStr);
				rDB.addRule(new Rule(premises, conclusion, name));
			}
		}
	}

	public FactsBase getfDB() {
		return fDB;
	}

	public void setfDB(FactsBase fDB) {
		this.fDB = fDB;
	}

	public RulesBase getrDB() {
		return rDB;
	}

	public void setrDB(RulesBase rDB) {
		this.rDB = rDB;
	}

	public HumanInterface getIhm() {
		return ihm;
	}

	public void setIhm(HumanInterface ihm) {
		this.ihm = ihm;
	}

}
