package com.ubs.opsit.interviews.luhn;

import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * This implementation of CreditCardNumberValidator exposes a method validate(String number) which performs validation
 * by using the Luhn's algorithm and a series of basic checks.  Among these checks is a length sanity check based
 * on the current min (13) and max (19) lengths available for credit cards. More advanced checks (such as checking the 
 * number pattern to see if it conforms to at least one credit card provider) are left out purposefully
 * as this solution does not attempt to encompass every scenario.
 * 
 * @author Marlon Jackson
 *
 */

public class CreditCardNumberValidatorImpl implements CreditCardNumberValidator {
	
	private static Logger log = Logger.getLogger("com.ubs.opsit.interviews.luhn.CreditCardNumberValidatorImpl");

	//Primary method for validation.  Performs length validation logic and calls helper methods.
	public void validate(String number)
			throws CreditCardNumberValidationException {

		if (number == null || number.equals("")) {
			throw new CreditCardNumberValidationException("Card number is null or empty");
		}

		//It is required to support the entry of credit card numbers both with spaces and without spaces.  
		//So we just remove all spaces we encounter.
		number = number.replace(" ", "");

		if (number.length() < 13) {
			throw new CreditCardNumberValidationException("Card number is too short.");
		} else if (number.length() > 19) {
			throw new CreditCardNumberValidationException("Card number is too long.");
		} else if (!this.containsAllNumbers(number)) {
			throw new CreditCardNumberValidationException("Card number contains invalid character.");
		} else {
			doLuhnsAlgorithm(number);
		}
	}

	//Simple check to make sure the characters in the credit card number are all numbers by inspecting their ASCII values.
	private boolean containsAllNumbers(String number) {
		for (int i = 0; i < number.length(); i++) {
			int asciiVal = (int) number.charAt(i);
			if (asciiVal < 48 || asciiVal > 57) {
				return false;
			}
		}
		return true;
	}

	//Run the credit card number against Luhns Algorithm
	private void doLuhnsAlgorithm(String number) throws CreditCardNumberValidationException {
		int singleDigitSum = 0; // sums of rightmost number and every other number after
		ArrayList<Integer> doubleDigits = new ArrayList<Integer>();

		boolean willDouble = false; //indicates whether or not this number is to be treated as a "doubled digit"

		//Sort the digits based on position and go ahead and add up all the digits that will not be doubled.
		log.debug("----Sorting digits based on position----");
		for (int i = number.length() - 1; i >= 0; i--) {
			int digit = Integer.parseInt(number.substring(i, i + 1));
			if (willDouble) {
				doubleDigits.add(digit * 2); // double the value of every second digit
				log.debug("Added digit " + digit + " to double digit list.");
			} else {
				singleDigitSum += digit;
			}

			willDouble = !willDouble; // flip flag on each iteration
		}

		String input = "";
		int doubleDigitSum = 0;
		
		//Add up each individual digit in all the credit card digits that were doubled.
		log.debug("----Separating individual digits in doubled digits and adding them all up----");
		for (Integer val : doubleDigits) {
			input = val.toString();
			for (int i = 0; i < input.length(); i++) {
				String individual = input.substring(i, i + 1);
				doubleDigitSum = doubleDigitSum	+ Integer.parseInt(individual);
				log.debug("Adding invidual digit " + individual + " from doubled digit " + input + " to sum and now total is " + doubleDigitSum + ".");
			}
		}

		//If the total sum modded by 10 equals 0; the account number is valid.  Of course, a sum of 0 is invalid.
		int totalSum = singleDigitSum + doubleDigitSum;
		if (totalSum == 0 && totalSum % 10 != 0) {
			throw new CreditCardNumberValidationException("Card account number is invalid; fails Luhns algorithm validation.");
		}
	}

}
