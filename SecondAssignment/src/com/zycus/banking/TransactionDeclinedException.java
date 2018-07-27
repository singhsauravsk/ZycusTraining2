package com.zycus.banking;

public class TransactionDeclinedException extends Exception {
	private static final long serialVersionUID = -2283211481614214218L;

	public TransactionDeclinedException() {
		super();
	}

	public TransactionDeclinedException(String arg0) {
		super(arg0);
	}
}
