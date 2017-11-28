//**********************************************************************
// Copyright (c) 2017 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************
package br.org.fitec.cpi.tdd.ex1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyCalculatorTest {

	Calculator myCalculator;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myCalculator = new MyCalculator();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		myCalculator = null;
	}

	@Test
	public void testAddSumTwoNumbersSuccess() throws NegativeNumberException {
		String s = "1,2";
		int result = myCalculator.add(s);
		Assert.assertEquals(3, result);
	}

	@Test
	public void testAddSumOneNumberSuccess() throws NegativeNumberException {
		String s = "1";
		int result = myCalculator.add(s);
		Assert.assertEquals(1, result);

	}

	@Test
	public void testAddSumNoNumberSuccess() throws NegativeNumberException {
		String s = "";
		int result = myCalculator.add(s);
		Assert.assertEquals(0, result);

		result = myCalculator.add(null);
		Assert.assertEquals(0, result);
	}

	@Test
	public void testAddAnyNumberSuccess() throws NegativeNumberException {
		String s = "1,2,3,4,5,6,7,8,9,0";
		int result = myCalculator.add(s);
		Assert.assertEquals(45, result);
	}

	@Test
	public void testAddNegativeNumberShouldFailException() throws NegativeNumberException {
		String s = "-1";
		try {
			myCalculator.add(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("Negative Number Not Allowed -1.0", msg);
		}
	}

	@Test
	public void testAddNegativeNumber2ShouldFailException() throws NegativeNumberException {
		String s = "3,-1";
		try {
			myCalculator.add(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("Negative Number Not Allowed -1.0", msg);
		}
	}

	@Test
	public void testAddNegativeNumber3ShouldFailException() throws NegativeNumberException {
		String s = " -3,-1";
		try {
			myCalculator.add(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("Negative Number Not Allowed -3.0 -1.0", msg);
		}
	}

	@Test
	public void testAddNegativeNumber4ShouldFailException() throws NegativeNumberException {
		String s = "-2, -1";
		try {
			myCalculator.add(s);
		} catch (NegativeNumberException e) {
			String msg = e.getMessage();
			Assert.assertEquals("Negative Number Not Allowed -2.0 -1.0", msg);
		}
	}

	@Test
	public void testAddBiggerThan4DigitsSuccess() throws NegativeNumberException {
		String s = "2,1001";
		int result = myCalculator.add(s);
		Assert.assertEquals(2, result);
	}

	@Test
	public void testAddOnlyTwoNumbersBiggerThan4DigitsSuccess() throws NegativeNumberException {
		String s = "1111,1001";
		int result = myCalculator.add(s);
		Assert.assertEquals(0, result);
	}

	@Test
	public void testSubtractTwoNumbersSuccess() throws NegativeNumberException {
		String s = "2,4";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(-2, result);

	}

	@Test
	public void testSubtractMultipleNumbersSuccess() throws NegativeNumberException {
		String s = "1,2,3,4,5";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(-13, result);
	}

	@Test
	public void testSubtractBiggerThan4DigitsSuccess() throws NegativeNumberException {
		String s = "1111,2,3,4222,1";
		int result = myCalculator.subtract(s);
		Assert.assertEquals(-2, result);
	}

	@Test
	public void testMultiplyBiggerThan4DigitsSuccess() throws NegativeNumberException {
		String s = "1111,2,3,4222,1,2,5,10000";
		double result = myCalculator.multiply(s);
		Assert.assertEquals(Double.valueOf(60.0), Double.valueOf(result));
	}

	@Test
	public void testMultiplyOneNumbersSuccess() throws NegativeNumberException {
		String s = "1111,2";
		double result = myCalculator.multiply(s);
		Assert.assertEquals(Double.valueOf(2.0), Double.valueOf(result));
	}

	@Test
	public void testMultiplyNoneSuccess() throws NegativeNumberException {
		String s = "1111,1112";
		double result = myCalculator.multiply(s);
		Assert.assertEquals(Double.valueOf(0.0), Double.valueOf(result));
	}

	@Test
	public void testDivideThreeNumbersSuccess() throws NegativeNumberException {
		String s = "20,1111,5,2,1112";		
		double result =	myCalculator.divide(s);		
		Assert.assertEquals(Double.valueOf(2.0), Double.valueOf(result));	
	}

	@Test
	public void testDivideOneNumberSuccess() throws NegativeNumberException {
		String s = "20";
		double result = myCalculator.divide(s);
		Assert.assertEquals(Double.valueOf(20.0), Double.valueOf(result));
	}

	@Test
	public void testDivideNoNumberSuccess() throws NegativeNumberException {
		String s = "1000,1345";
		double result = myCalculator.divide(s);
		Assert.assertEquals(Double.valueOf(0.0), Double.valueOf(result));
	}

	@Test
	public void testDivideMultipleNumbersSuccess() throws NegativeNumberException {
		String s = "12,2,2,3,2";
		double result = myCalculator.divide(s);
		Assert.assertEquals(Double.valueOf(0.5), Double.valueOf(result));
	}

	@Test
	public void testDivideZeroShouldFailException() throws NegativeNumberException, DivisionByZeroException {
		String s = "12,0";
		try {
			myCalculator.divide(s);
		} catch (DivisionByZeroException e) {
			String msg = e.getMessage();
			Assert.assertEquals("Division by Zero", msg);
		}
	}

	@Test
	public void testDivideMultipleNumberWithZeroShouldFailException()
			throws NegativeNumberException, DivisionByZeroException {
		String s = "12,0,6,2";
		try {
			myCalculator.divide(s);
		} catch (DivisionByZeroException e) {
			String msg = e.getMessage();
			Assert.assertEquals("Division by Zero", msg);
		}
	}

	@Test
	public void testDivideTwoNumbersSuccess() throws NegativeNumberException, DivisionByZeroException {
		String s = "10,3";
		double result = myCalculator.divide(s);
		Assert.assertEquals(Double.valueOf(3.3), Double.valueOf(result));

	}
}
