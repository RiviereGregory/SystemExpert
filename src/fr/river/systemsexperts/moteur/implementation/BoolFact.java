package fr.river.systemsexperts.moteur.implementation;

import fr.river.systemsexperts.moteur.IFact;

public class BoolFact implements IFact {	

	public BoolFact(String name, Boolean value, int level, String question) {
		super();
		this.name = name;
		this.value = value;
		this.level = level;
		this.question = question;
	}
	
	public BoolFact(String name, Boolean value, String question) {
		super();
		this.name = name;
		this.value = value;
		this.question = question;
	}
	
	private String name;

	@Override
	public String name() {

		return name;
	}

	private Boolean value;

	@Override
	public Object value() {
		return value;
	}

	private int level = 0;

	@Override
	public int level() {
		return level;
	}

	@Override
	public void setLevel(int l) {
		level = l;
	}
	
	@Override
	public int getLevel() {
		return level;
	}
	
	private String question = null;
	@Override
	public String question() {
		return question;
	}
	
	@Override
	public String toString() {
		String res = "";
		if(!value){
			res +="!";
		}
		res += name + "("+ level + ")";
		return res;
	}

	@Override
	public int compareTo(IFact o) {
		if (this.level > o.level()) {
			return -1;
		} else if (this.level < o.getLevel()) {
			return 1;
		} else {
			return 0;
		}
	}

}
