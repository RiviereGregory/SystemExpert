package fr.river.systemsexperts.moteur;


public interface IFact extends Comparable<IFact>{
	String name();
	Object value();
	int level();
	String question();
	
	void setLevel(int p);
	int getLevel();
}
