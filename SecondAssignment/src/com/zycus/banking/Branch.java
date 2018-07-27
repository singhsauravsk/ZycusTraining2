package com.zycus.banking;

import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is class where all the branch operation will be taken care.
 * @author saurav.kumar
 *
 */
public class Branch {
	private Map <Long, Account> allAccounts = new HashMap<>();
	private static long ACCOUNT_NO_GENERATOR = 1;
	
	/**
	 * This method is responsible to create new account of any type.
	 * @param accountType	'AccountType' enum object to specify type of account to be created.
	 * @param amount		Amount to be deposited at the creation of account.
	 * @param holderName	Name of holder against which account will be created.
	 * @return	Object of 'Account' class
	 */
	public Account openNewAccount(AccountType accountType, double amount, String holderName) {
		long accountNumber = (ACCOUNT_NO_GENERATOR++) + 1232443l;
		Account newAccount = new Account(Status.ACTIVE, amount, accountNumber, holderName, accountType);
		Account.setBranchCode(1234);
		allAccounts.put(accountNumber, newAccount);
		
		return newAccount;
	}
	
	/**
	 * Takes account number as input and returns the object of account to which it is linked
	 * by finding the object in map.
	 * @param accountNumber		Account number for which details have to be found
	 * @return		Account to which account number is linked.
	 */
	public Account getAccount(long accountNumber) {
		
		if(allAccounts.containsKey(accountNumber)) {
			return allAccounts.get(accountNumber);
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method will change the status of account to closed.
	 * @param accountNumber		Account number of account that has to be closed.	
	 * @return		True on success and false on fail.
	 */
	public boolean closeAccount(long accountNumber) {
		
		if(allAccounts.containsKey(accountNumber)) {
			allAccounts.get(accountNumber).setStatus(Status.CLOSED);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * This method will generate list of all accounts 
	 * @return 	LinkedList containing all accounts.
	 */
	public List <Account> getAll() {
		return new ArrayList<>(allAccounts.values());
	}
	
	/**
	 * This method will return set of accounts against account holder's name.
	 * @param holder	Name of the account holder 
	 * @return		Set containing all accounts against a name.
	 */
	public Set <Account> findByHolderName(String holder) {
		return allAccounts.values().stream().filter((x)->x.getAccountHolder().equalsIgnoreCase(holder)).collect(Collectors.toSet());
	}

	/**
	 * This method will print the details of a account.
	 * @param newAccount	Account of which details has to be printed.
	 */
	public void printAccountDetails(Account newAccount) {
		
		if(newAccount == null) {
			System.out.println("There is no such account");
		}
		else {
			System.out.print("Name: "+newAccount.getAccountHolder()+", Account Number: "+newAccount.getAccountNumber());
			System.out.print(", Branch Code: "+Account.getBranchCode()+", Amount: "+newAccount.getAmount());
			System.out.println(", Account Type: "+newAccount.getAccountType()+", Status: "+newAccount.getStatus());
		}
	}
	
	/**
	 * This method will return the filtered list of accounts according to predicate
	 * @param predicate		Predicate by which list of account need to be sorted
	 * @return		LinkedList of filtered account
	 */
	public List <Account> search(Predicate <Account> predicate) {
		return allAccounts.values().stream().filter(predicate).collect(Collectors.toCollection(ArrayList::new));
	}
} 
