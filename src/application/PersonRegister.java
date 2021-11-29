package application;

import java.util.ArrayList;

public class PersonRegister {

	private ArrayList<Person> register = new ArrayList<Person>();

	public void addPerson(Person p) {
		register.add(p);
		
	}
	public ArrayList<Person> getRegister() {
		return register;
	}
	public void setRegister(ArrayList<Person> register) {
		this.register = register;
	}
	public Person findPerson(String pNbr) {
		for (Person iFack : register) {
			if (iFack.getpNbr().equals(pNbr)) {
				return iFack;
			}
		} return null;
	
}
	public Person removePerson(String pNbr) {
		Person x = this.findPerson(pNbr);
		if (x != null) {
			register.remove(x);
		} return null;
		
	}
	
	public Account findAccount(String pNbr, String accountNbr) {
		Person p = findPerson(pNbr);
		return p.findAccount(accountNbr);
		
	}
	public double totBalancePerson(String pNbr) {
		Person p = findPerson(pNbr);
		return p.totBalance();
		
	}

}
