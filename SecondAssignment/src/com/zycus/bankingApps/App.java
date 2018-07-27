package com.zycus.bankingApps;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import com.zycus.banking.Account;
import com.zycus.banking.AccountType;
import com.zycus.banking.Branch;
import com.zycus.banking.ErrorCode;
import com.zycus.banking.Status;
import com.zycus.banking.Transaction;
import com.zycus.banking.TransactionDeclinedException;

public class App {
	static Branch branch = new Branch();
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		openFirstTenAccounts();
		
		while(menuCustomer()) {
			
		}
	}
	
	private static void openFirstTenAccounts() {
		branch.openNewAccount(AccountType.CURRENT, 100000d, "Saurav Kumar");
		branch.openNewAccount(AccountType.SAVING, 1000d, "Ansuman Sasmal");
		branch.openNewAccount(AccountType.CURRENT, 102300d, "Anjani Kumar");
		branch.openNewAccount(AccountType.CURRENT, 2343124d, "Rishabh Gupta");
		branch.openNewAccount(AccountType.SAVING, 14234d, "Saurav Kumar");
		branch.openNewAccount(AccountType.CURRENT, 14252340d, "Ansuman Sasmal");
		branch.openNewAccount(AccountType.SAVING, 222300d, "Anjani Kumar");
		branch.openNewAccount(AccountType.SAVING, 234344d, "Rishabh Gupta");
		branch.openNewAccount(AccountType.CURRENT, 14324d, "Aditya");
		branch.openNewAccount(AccountType.SAVING, 65463d, "Amar");
		branch.openNewAccount(AccountType.CURRENT, 456345d, "Amar");
	}
	
	private static boolean menuCustomer() {
		String action;
		long accountNo;
		
		System.out.println("Enter an action to perform");
		System.out.println("Press 'i' to create new account");
		System.out.println("Press 'c' to close an account" );
		System.out.println("Press 'g' to get account details.");
		System.out.println("Press 'w' to withdraw");
		System.out.println("Press 'd' to deposit");
		System.out.println("Press 't' to transfer fund");
		System.out.println("Press 'l' to list all accounts");
		System.out.println("Press 'ls' to list all acounts in sorted manner by amount");
		System.out.println("Press 'lsa' to list all acounts in sorted manner by account");
		System.out.println("Press 'h' to list accounts by holder name");
		System.out.println("Press 'f' to get filtered list");
		System.out.println("Press 'q' to quit application");
		
		action = scanner.next();
		
		if(action.equalsIgnoreCase("i")) {
			openNewAccountButton();
		}
		else if(action.equalsIgnoreCase("c")) {
			closeAccountButton();
		}
		else if(action.equalsIgnoreCase("w")) {
			withdrawButton();
		}
		else if(action.equalsIgnoreCase("d")) {
			depositButton();
		}
		else if(action.equalsIgnoreCase("t")) {
			transferButton();
		}
		else if(action.equalsIgnoreCase("g")) {
			System.out.println("Enter account number");
			accountNo = scanner.nextLong();
			branch.printAccountDetails(branch.getAccount(accountNo));
		}
		else if(action.equalsIgnoreCase("l")) {
			listAllAccounts();
		}
		else if(action.equalsIgnoreCase("ls")) {
			listAllAccountsSortedAmount();
		}
		else if(action.equalsIgnoreCase("lsa")) {
			listAllAccountsSortedAccount();
		}
		else if(action.equalsIgnoreCase("h")) {
			listAccountsByHolderName();
		}
		else if(action.equalsIgnoreCase("f")) {
			filterButton();
		}
		else if(action.equalsIgnoreCase("q")) {
			System.out.println("Are you sure to quit application(y/n) : ");
			action = scanner.next();
			
			if(action.equalsIgnoreCase("y")) {
				return false;
			}
		}
		else {
			System.out.println("Not a valid key");
		}
		
		return true;
	}

	private static void closeAccountButton() {
		System.out.println("Enter account number");
		long accountNo = scanner.nextLong();
		
		if(branch.closeAccount(accountNo)) {
			System.out.println("Account is closed successfully");
		}
		else {
			System.out.println("There is no such account");
		}
		
	}

	private static void openNewAccountButton() {
		long amount = 0l;
		String newAccountType, suerity, newHolderName = new String();
		
		System.out.println("Enter 'c' for current account or 's' for saving account");
		newAccountType = scanner.next();
		
		if(newAccountType.equalsIgnoreCase("c")) {
			System.out.println("Enter your name:");
			scanner.nextLine();
			newHolderName = scanner.nextLine();
			
			System.out.println("Enter amount to be deposited");
			amount = scanner.nextLong();
			
			System.out.println("Are you sure to create this account(y/n)");
			suerity = scanner.next();
			
			if(suerity.equalsIgnoreCase("y")) {
				Account newAccount =  branch.openNewAccount(AccountType.CURRENT, amount, newHolderName);
				System.out.println("Please! note down account number for further refrences");
				branch.printAccountDetails(newAccount);
			}
			else {
				System.out.println("Account creation is terminated");
			}
		}
		else if(newAccountType.equalsIgnoreCase("s")) {
			System.out.println("Enter your name : ");
			newHolderName = scanner.nextLine();
			
			System.out.println("Enter amount to be deposited");
			amount = scanner.nextLong();
			
			System.out.println("Are you sure to create this account(y/n");
			suerity = scanner.next();
			
			if(suerity.equalsIgnoreCase("y")) {
				Account newAccount =  branch.openNewAccount(AccountType.SAVING, amount, newHolderName);
				System.out.println("Please! note down account number for further refrences");
				branch.printAccountDetails(newAccount);
			}
			else {
				System.out.println("Account creation is terminated");
			}
		}
		else {
			System.out.println("Invalid account type is entered");
			System.out.println("Account creation is terminated");
		}
	}
	
	private static void withdrawButton() {
		double amount;
		long accountNo;
		Transaction transaction = new Transaction(branch);
		
		System.out.println("Enter account number from which you want to withdraw.");
		accountNo = scanner.nextLong();
		System.out.println("Enter amount to be withdrawn");
		amount = scanner.nextDouble();
		
		try {
			transaction.withdraw(accountNo, amount);
			branch.printAccountDetails(branch.getAccount(accountNo));
		} catch(TransactionDeclinedException tr) {
			System.out.println(tr.getMessage());
		}
	}
	
	private static void depositButton() {
		double amount;
		long accountNo;
		Transaction transaction = new Transaction(branch);
		
		System.out.println("Enter account number to which you want to deposit");
		accountNo = scanner.nextLong();
		System.out.println("Enter amount to be diposited");
		amount = scanner.nextDouble();
		
		try {
			transaction.deposit(accountNo, amount);
			branch.printAccountDetails(branch.getAccount(accountNo));
		} catch(TransactionDeclinedException tr) {
			System.out.println(tr.getMessage());
		}
	}
	
	private static void transferButton() {
		double amount;
		long accountNo, payeeAccountNo;
		Transaction transaction = new Transaction(branch);
		
		System.out.println("Enter your account number");
		accountNo = scanner.nextLong();
		System.out.println("Enter payee account number");
		payeeAccountNo = scanner.nextLong();
		System.out.println("Enter amount to be transfered");
		amount = scanner.nextDouble();

		try {
			transaction.transferFund(accountNo, payeeAccountNo, amount);
			branch.printAccountDetails(branch.getAccount(accountNo));
		} catch(TransactionDeclinedException tr) {
			System.out.println(tr.getMessage());
		}
	}
	
	private static void listAllAccounts() {
		List <Account> allAccounts = branch.getAll();
	
		if(allAccounts.size() == 0) {
			System.out.println(ErrorCode.noSuchAccount);
		}
		else {
			
			for(Account account: allAccounts) {
				branch.printAccountDetails(account);
			}
		}
	}
	
	private static void listAccountsByHolderName() {
		String holderName;
		
		System.out.println("Enter account holder's name");
		holderName = scanner.nextLine();
		
		Set <Account> holderSet = branch.findByHolderName(holderName);
		
		if(holderSet.size() == 0) {
			System.out.println(ErrorCode.noAccountLinked+holderName);
		}
		else {
			
			for(Account account : holderSet) {
				branch.printAccountDetails(account);
			}
		}
	}
	
	private static void filterButton() {
		List <Account> filteredList = null;
		String action;
		
		System.out.println("Press 1 to see ACTIVE accounts");
		System.out.println("Press 2 to see CLOSED accounts");
		System.out.println("Press 3 to see CURRENT accounts");
		System.out.println("Press 4 to see SAVING accounts");
		action = scanner.next();
		
		if(action.equalsIgnoreCase("1")) {
			filteredList = branch.search((x)->x.getStatus() == Status.ACTIVE);
		}
		else if(action.equalsIgnoreCase("2")) {
			filteredList = branch.search((x)->x.getStatus() == Status.CLOSED);
		}
		else if(action.equalsIgnoreCase("3")) {
			filteredList = branch.search((x)->x.getAccountType() == AccountType.CURRENT);
		}
		else if(action.equalsIgnoreCase("4")) {
			filteredList = branch.search((x)->x.getAccountType() == AccountType.SAVING);
		}
		else {
			System.out.println("Please! Enter a valid key");
		}
		
		if(filteredList.size() == 0) {
			System.out.println(ErrorCode.noSuchAccount);
		}
		else {
		
			for(Account account: filteredList) {
				branch.printAccountDetails(account);
			}
		}
	}
	
	private static void listAllAccountsSortedAccount() {
		List <Account> sortedList = branch.getAll();
		Collections.sort(sortedList, (a1, a2)->(int)(a1.getAccountNumber()-a2.getAccountNumber()));
		
		if(sortedList.size() == 0) {
			System.out.println(ErrorCode.noSuchAccount);
		}
		else {
			
			for(Account account : sortedList) {
				branch.printAccountDetails(account);
			}
		}
	}

	private static void listAllAccountsSortedAmount() {
		List <Account> sortedList = branch.getAll();
		Collections.sort(sortedList, (a1, a2)->(int)(a1.getAmount()-a2.getAmount()));
		
		if(sortedList.size() == 0) {
			System.out.println(ErrorCode.noSuchAccount);
		}
		else {
			
			for(Account account : sortedList) {
				branch.printAccountDetails(account);
			}
		}
	}
}
