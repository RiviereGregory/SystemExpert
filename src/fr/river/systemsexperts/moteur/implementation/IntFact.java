package fr.river.systemsexperts.moteur.implementation;

import fr.river.systemsexperts.moteur.IFact;

public class IntFact implements IFact {

	public IntFact(String name, int value, int level, String question) {
		super();
		this.name = name;
		this.value = value;
		this.level = level;
		this.question = question;
	}
	
	public IntFact(String name, int value, String question) {
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

	private int value;

	@Override
	public Object value() {
		return value;
	}

	private int level = 0;

	@Override
	public int level() {
		return level;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public void setLevel(int l) {
		level = l;
	}

	private String question = null;

	@Override
	public String question() {
		return question;
	}

	@Override
	public String toString() {
		return name + "=" + value + "(" + level + ")";
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
