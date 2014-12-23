package com.ubs.opsit.interviews.luhn;

public class CreditCardNumberValidationException extends Exception {

	private static final long serialVersionUID = 8992805674819578785L;
	
	public CreditCardNumberValidationException(){
		super();
	}
	
	public CreditCardNumberValidationException(String message){
		super(message);
	}

}
