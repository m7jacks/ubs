package com.ubs.opsit.interviews.luhn;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidatorTests {
	
	@Test
	public void testNullAndEmpty(){
		CreditCardNumberValidator validator = new CreditCardNumberValidatorImpl();
		String number = null;
		try {
			validator.validate(number);
			Assert.fail("No exception thrown for null value.");
		} catch (CreditCardNumberValidationException e) {
			//do nothing
		}
	
		number = "";
		try {
			validator.validate(number);
			Assert.fail("No exception thrown for empty value.");
		} catch (CreditCardNumberValidationException e) {
			//do nothing
		}
	}
	
	
	@Test
	public void testBadLength(){
		CreditCardNumberValidator validator = new CreditCardNumberValidatorImpl();
		String number = "123456789123"; //12 length
		try {
			validator.validate(number);
			Assert.fail("No exception thrown for failure to meet minimum length requirement.");
		} catch (CreditCardNumberValidationException e) {
			//do nothing
		}
		
		number = "12345678912345678912"; //20 length
		try {
			validator.validate(number);
			Assert.fail("No exception thrown for exceeding maximum length requirement.");
		} catch (CreditCardNumberValidationException e) {
			//do nothing
		}
	}
	
	
	@Test
	public void testContainsNotOnlyNumbers(){
		CreditCardNumberValidator validator = new CreditCardNumberValidatorImpl();
		String number = "A9927398716";
		try {
			validator.validate(number);
			Assert.fail("No exception thrown for containing not only numbers.");
		} catch (CreditCardNumberValidationException e) {
			//do nothing
		}
		
		number = "49927%P8716";
		try {
			validator.validate(number);
			Assert.fail("No exception thrown for containing not only numbers.");
		} catch (CreditCardNumberValidationException e) {
			//do nothing
		}
	}

	
	
	@Test
	public void testValidNumbers(){
		CreditCardNumberValidator validator = new CreditCardNumberValidatorImpl();
		String number = "378282246310005"; //test 1 (AmEx card)
		try {
			validator.validate(number);			
		} catch (CreditCardNumberValidationException e) {
			Assert.fail("Exception should not be thrown for valid length which passes Luhn algorithm. Valid number 1.");
		}
		
		number = "6011 111111111117"; //test 2 (Discover card)
		try {
			validator.validate(number);			
		} catch (CreditCardNumberValidationException e) {
			Assert.fail("Exception should not be thrown for valid length which passes Luhn algorithm. Valid number 2.");
		}
		
		number = "41111111   1111 1111"; //test 3 (VISA card)
		try {
			validator.validate(number);			
		} catch (CreditCardNumberValidationException e) {
			Assert.fail("Exception should not be thrown for valid length which passes Luhn algorithm. Valid number 3.");
		}

	}

}
