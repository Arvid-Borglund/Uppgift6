package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import application.Person;
import application.Account;
import application.PersonRegister;

public class SampleController {

	PersonRegister reg = new PersonRegister();

	@FXML
	private TextField txtFieldName;
	@FXML
	private TextField txtFieldpNbr;
	@FXML
	private TextField txtFieldAccount;
	@FXML
	private TextField txtFieldSum;
	@FXML
	private TextArea txtAreaOutput;
	@FXML
	private Button btnAddPerson;
	@FXML
	private Button btnRemove;
	@FXML
	private Button btnFind;
	@FXML
	private Button btnAddAccount;
	@FXML
	private Button btnWithdraw;
	@FXML
	private Button btnCredit;
	@FXML
	private Button btnBalance;
	@FXML
	private Button btnAllAccounts;

	@FXML
	public void btnAddPerson_Click(ActionEvent event) {
		String name = txtFieldName.getText();
		String pNbr = txtFieldpNbr.getText();
		Person x = new Person(pNbr, name);
		if (name.isBlank())
			txtAreaOutput.setText("Inget namn angavs, ange namn.");
		else if (pNbr.isBlank())
			txtAreaOutput.setText("Inget personnummer angavs, ange personnummer.");
		else {
			Person found = reg.findPerson(pNbr);
			if (found == null) {
				reg.addPerson(x);
				txtAreaOutput.setText("Lagt till: " + x.getName() + " med personnummer: " + x.getpNbr());
			} else
				txtAreaOutput.setText("Person finns redan.");

		}
	}

	@FXML
	public void btnRemove_Click(ActionEvent event) {
		String pNbr = txtFieldpNbr.getText();
		Person x = reg.findPerson(pNbr);
		if (pNbr.isBlank())
			txtAreaOutput.setText("Inget personnummer angavs, ange personnummer.");
		else {
			if (x == null)
				txtAreaOutput.setText("Person finns inte i registret");
			else {
				reg.removePerson(pNbr);
				txtAreaOutput.setText(x.getName() + " med personnummer: " + pNbr + " är borttagen.");
			}
		}
	}

	@FXML
	public void btnFind_Click(ActionEvent event) {
		String pNbr = txtFieldpNbr.getText();
		Person x = reg.findPerson(pNbr);
		if (pNbr.isBlank())
			txtAreaOutput.setText("Inget personnummer angavs, ange personnummer.");
		else {
			if (x == null)
				txtAreaOutput.setText("Person finns inte i registret");
			else {
				txtAreaOutput.setText("hittade: " + x.getName());
			}

		}

	}

	@FXML
	public void btnAddAccount_Click(ActionEvent event) {
		String pNbr = txtFieldpNbr.getText();
		String nbr = txtFieldAccount.getText();
		Person x = reg.findPerson(pNbr);
		if (pNbr.isBlank())
			txtAreaOutput.setText("Inget personnummer angavs, ange personnummer.");
		else if (nbr.isBlank())
			txtAreaOutput.setText("Inget kontonummer angavs, ange kontonummer.");
		else {
			if (x == null)
				txtAreaOutput.setText("Person finns inte i registret");
			else if (x.findAccount(nbr) != null)
				txtAreaOutput.setText("Konto finns redan.");
			else {
				double balance = 0;
				Account y = new Account(nbr, balance);
				x.addAccount(y);
				txtAreaOutput.setText("Konto: " + y.getNbr() + " är skapat till " + x.getName());
			}
		}
	}

	@FXML
	public void btnCredit_Click(ActionEvent event) {

		String pNbr = txtFieldpNbr.getText();
		String nbr = txtFieldAccount.getText();
		double sum = Double.parseDouble(txtFieldSum.getText());

		Person x = reg.findPerson(pNbr);
		if (pNbr.isBlank())
			txtAreaOutput.setText("Inget personnummer angavs, ange personnummer.");
		else if (nbr.isBlank())
			txtAreaOutput.setText("Inget kontonummer angavs, ange kontonummer.");
		else if (sum == 0)
			txtAreaOutput.setText("Ingen summa angiven, ange summa.");
		else {
			if (x == null)
				txtAreaOutput.setText("Person finns inte i registret");
			else if (x.findAccount(nbr) == null)
				txtAreaOutput.setText("Konto finns inte i registret.");
			else {
				x.findAccount(nbr).credit(sum);
				txtAreaOutput.setText(sum + "kr är insatt på konto: " + x.findAccount(nbr).getNbr());
			}

		}
	}

	@FXML
	public void btnWithdraw_Click(ActionEvent event) {

		String pNbr = txtFieldpNbr.getText();
		String nbr = txtFieldAccount.getText();
		double sum = Double.parseDouble(txtFieldSum.getText());
		Person x = reg.findPerson(pNbr);

		if (pNbr.isBlank())
			txtAreaOutput.setText("Inget personnummer angavs, ange personnummer.");
		else if (nbr.isBlank())
			txtAreaOutput.setText("Inget kontonummer angavs, ange kontonummer.");
		else if (sum == 0)
			txtAreaOutput.setText("Ingen summa angiven, ange summa.");
		else {
			if (x == null)
				txtAreaOutput.setText("Person finns inte i registret");
			else if (x.findAccount(nbr) == null)
				txtAreaOutput.setText("Konto finns inte i registret.");
			else {
				x.findAccount(nbr).withdraw(sum);
				txtAreaOutput.setText(sum + "kr är uttaget från konto: " + x.findAccount(nbr).getNbr());
			}

		}

	}

	@FXML
	public void btnBalance_Click(ActionEvent event) {

		String pNbr = txtFieldpNbr.getText();
		Person x = reg.findPerson(pNbr);

		if (pNbr.isBlank())
			txtAreaOutput.setText("Inget personnummer angavs, ange personnummer.");
		else {
			if (x == null)
				txtAreaOutput.setText("Person finns inte i registret");
			else {
				txtAreaOutput.setText(x.getName() + " totala saldo är: " + x.totBalance());
			}
		}

	}

	@FXML
	public void btnAllAccounts_Click(ActionEvent event) {

		String pNbr = txtFieldpNbr.getText();
		Person x = reg.findPerson(pNbr);

		if (pNbr.isBlank())
			txtAreaOutput.setText("Inget personnummer angavs, ange personnummer.");
		else {
			if (x == null)
				txtAreaOutput.setText("Person finns inte i registret");
			else if (x.getRegister().isEmpty())
				txtAreaOutput.setText(x.getName() + " har inga konton");
			else {
				ArrayList<Account> tmpaccountlist = reg.findPerson(pNbr).getRegister();
				txtAreaOutput.setText(x.getName() + " Har " + tmpaccountlist.size() + " konton:" + "\n");
				for (Account i : tmpaccountlist) {
					txtAreaOutput.appendText("Konto: " + i.getNbr() + " med " + i.getBalance() + " kr" + "\n");
				}

			}
		}
	}
}
