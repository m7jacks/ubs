package com.ubs.opsit.interviews.luhn;


public interface CreditCardNumberValidator {
	
	public void validate(String number) throws CreditCardNumberValidationException;

}
