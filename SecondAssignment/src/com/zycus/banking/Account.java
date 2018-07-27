package com.zycus.banking;

/**
 * This class have all the account properties.
 * And will responsible to hold all the data of a particular account.
 * @author saurav.kumar
 *
 */
public class Account {
	private Status status;
	private double amount;
	private long accountNumber;
	private String accountHolder;
	private static int branchCode;
	private AccountType accountType;
	
	/**
	 * This is parameterized constructor which will initialize the object of 
	 * 'Account' class on creation of new account.
	 * @param status	Status of the account whether it is closed or active
	 * @param amount	Amount in a particular account.
	 * @param accountNumber		Account number to which a particular account is linked
	 * @param accountHolder		Name of the account holder
	 * @param accountType		Type of the account whether it is current or saving.
	 */
	Account(Status status, double amount, long accountNumber, String accountHolder, AccountType accountType) {
		super();
		this.status = status;
		this.amount = amount;
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.accountType = accountType;
	}

	public Status getStatus() {
		return status;
	}

	public double getAmount() {
		return amount;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public static int getBranchCode() {
		return branchCode;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	void setStatus(Status status) {
		this.status = status;
	}

	void setAmount(double amount) {
		this.amount = amount;
	}

	void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	static void setBranchCode(int branchCode) {
		Account.branchCode = branchCode;
	}

	void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
}
