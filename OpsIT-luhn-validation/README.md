# The Luhn Algorithm

## The brief

You are required to produce a solution to perform credit card validation according to the Luhn algorithm below.  This will be used for web-based validations and therefore should be tolerant of edge conditions and invalid data scenarios which can be exhibited on a website.  In addition, it is required to support the entry of credit card numbers both with spaces and without spaces and to validate each of these appropriately.

You should prove its operation through JUnit test cases.

## The Luhn Algorithm
The formula verifies a number against its included check-digit, which is usually appended to a partial account number to generate the full account number. This account number must pass the following test:

1. Counting from the check digit, which is the rightmost, and moving left, double the value of every second digit.
2. Sum the digits of the products together with the un-doubled digits from the original number.
3. If the total ends in 0 (put another way, if the total modulo 10 is congruent to 0), then the number is valid
according to the Luhn formula; else it is not valid.

### Example
As an illustration, if the account number is 49927398716, it will be validated as follows:

1. Double every second digit, from the rightmost digit:  
    `(1*2) = 2, (8*2) = 16, (3*2) = 6, (2*2) = 4, (9*2) = 18`
2. Sum all digits (digits in parentheses are the products from Step 1):  
    `6 + (2) + 7 + (1 + 6) + 9 + (6) + 7 + (4) + 9 + (1 + 8) + 4 = 70`
3. Take the sum modulo 10: 70 mod 10 = 0; the account number is valid.

## Approach
You will be provided with a skeletal solution including an interface, exception and empty test class.