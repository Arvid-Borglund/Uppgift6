package application;

import java.util.ArrayList;

public class Person {
	private String pNbr;
	private String name;
	private ArrayList<Account> register = new ArrayList<Account>();
	
	
	public Person(String pNbr, String name) {
		this.pNbr = pNbr;
		this.name = name;
	}
	
	
	
	public ArrayList<Account> getRegister() {
		return register;
	}

	public void setRegister(ArrayList<Account> register) {
		this.register = register;
	}

	public String getpNbr() {
		return pNbr;
	}

	public void setpNbr(String pNbr) {
		this.pNbr = pNbr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
public void addAccount(Account x) {
	register.add(x);
		
	}
public Account findAccount(String accountNbr) {
	for (Account iFack : register) {
		if (iFack.getNbr().equals(accountNbr)) {
			return iFack;
		}
	} return null;
}
public double totBalance() {
	double total = 0;
	for (Account iFack : register) {
		total += iFack.getBalance();
	} return total;	
}
}
