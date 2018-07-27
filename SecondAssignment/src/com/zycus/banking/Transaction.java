package com.zycus.banking;

/**
 * This class will take care of all the types of transactions/
 * @author saurav.kumar
 *
 */
public class Transaction {
	private Branch branch;
	private static final int MINIMUM_AMOUNT = 1000;

	public Transaction(Branch branch) {
		super();
		this.branch = branch;
	}
	
	/**
	 * Withdraw method will withdraw a particular amount from a account and also update the new amount.
	 * @param accountNumber		Withdrawing account
	 * @param amount			Withdrawing amount
	 */
	public void withdraw(long accountNumber, double amount) throws TransactionDeclinedException {
		Account account = branch.getAccount(accountNumber);
		
		if(account == null) {
			throw new TransactionDeclinedException(ErrorCode.noAccount);
		}
		else {
			double temp = account.getAmount() - amount;
		
			if(account.getStatus() == Status.CLOSED) {
				throw new TransactionDeclinedException(ErrorCode.closedAccount);
			}
			else if(account.getAmount() <= MINIMUM_AMOUNT) {
				throw new TransactionDeclinedException(ErrorCode.insufficientBalance);
			}
			else if(temp < MINIMUM_AMOUNT) {
				throw new TransactionDeclinedException(ErrorCode.insufficientBalance);
			}
			else {
				account.setAmount(temp);
				throw new TransactionDeclinedException(ErrorCode.successTransaction);
			}
		}
	}
	
	/**
	 * It will deposit a given amount to the specified account
	 * @param accountNumber		Depositing Account
	 * @param amount			Depositing Amount
	 */
	public void deposit(long accountNumber, double amount) throws TransactionDeclinedException{
		Account account = branch.getAccount(accountNumber);
		
		if(account == null) {
			throw new TransactionDeclinedException(ErrorCode.noAccount);
		}
		else {
			
			if(account.getStatus() == Status.CLOSED) {
				throw new TransactionDeclinedException(ErrorCode.closedAccount);
			}
			else {
				account.setAmount(account.getAmount()+amount);
				throw new TransactionDeclinedException(ErrorCode.successTransaction);
			}
		}
	}
	
	/**
	 * This method will transfer given amount from source account to destination account.
	 * @param sourceAccountNumber		Account number of source account
	 * @param destinationAccountNumber	Account number of payee
	 * @param amount					Amount to be transferred
	 */
	public void transferFund(long sourceAccountNumber, long destinationAccountNumber, double amount) throws TransactionDeclinedException{
		Account sourceAccount = branch.getAccount(sourceAccountNumber);
		
		if(sourceAccount == null) {
			throw new TransactionDeclinedException("Source: "+ErrorCode.noAccount);
		}
		else if(sourceAccount.getStatus() == Status.CLOSED){
			throw new TransactionDeclinedException("Source: "+ErrorCode.closedAccount);
		}
		else {
			Account destinationAccount = branch.getAccount(destinationAccountNumber);
			
			if(destinationAccount == null) {
				throw new TransactionDeclinedException("Destination: "+ErrorCode.noAccount);
			}
			else if(destinationAccount.getStatus() == Status.CLOSED){
				throw new TransactionDeclinedException("Destination: "+ErrorCode.noAccount);
			}
			else {
				
				try {
					withdraw(sourceAccountNumber, amount);
				} catch(TransactionDeclinedException tr) {
					System.out.println("Source"+tr.getMessage());
				}
				
				try {
					deposit(destinationAccountNumber, amount);
				} catch(TransactionDeclinedException tr) {
					System.out.println(tr.getMessage());
				}
			}
		}
	}
}
