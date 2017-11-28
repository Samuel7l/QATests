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

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;

public class MyCalculator implements Calculator {

	private static final String NOT_ALLOWED = "Negative Number Not Allowed ";
	private static final String DIVIDED_ZERO = "Division by Zero";
	private static final IntPredicate filterNumbersAllowed = number -> number < 1000;
	private static final IntPredicate filterNumbersNotAllowed = number -> number < 0;
	private static final DoublePredicate filterNumbersAllowedDouble = number -> number < 1000.0;
	private static final DoublePredicate filterNumbersNotAllowedDouble = number -> number < 0.0;

	private enum OPERATIONS {
		ADD, SUBTRACT, MULTIPLY, DIVIDE
	}

	public int[] convertStringToArrayInt(String numbersString) {
		if (numbersString == null || numbersString.isEmpty()) {
			return new int[0];
		}
		return Arrays.stream(numbersString.split(",")).mapToInt(number -> Integer.parseInt(number.trim())).toArray();
	}

	public double[] convertStringToArrayDouble(String numbersString) {
		if (numbersString == null || numbersString.isEmpty()) {
			return new double[0];
		}
		return Arrays.stream(numbersString.split(",")).mapToDouble(number -> Double.parseDouble(number.trim()))
				.toArray();
	}

	private double Calculate(String params, OPERATIONS operation) throws NegativeNumberException {

		if (params == null || params.length() == 0) {
			return 0;
		}
		double sum;

		int[] numbers = convertStringToArrayInt(params);
		int[] numbersNotAllowed = Arrays.stream(numbers).filter(filterNumbersNotAllowed).toArray();
		double[] numbersDouble = convertStringToArrayDouble(params);
		double[] numbersNotAllowedDouble = Arrays.stream(numbersDouble).filter(filterNumbersNotAllowedDouble).toArray();
		DecimalFormat decimalFormat = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.ENGLISH));

		if (numbersNotAllowed.length > 0 || numbersNotAllowedDouble.length > 0) {
			throw new NegativeNumberException(NOT_ALLOWED, numbersNotAllowedDouble);
		} else {
			switch (operation) {
			case ADD:
				sum = Arrays.stream(numbers).filter(filterNumbersAllowed).sum();
				break;
			case SUBTRACT:
				sum = Arrays.stream(numbers).filter(filterNumbersAllowed).reduce((a, b) -> a - b).orElse(0);
				break;
			case MULTIPLY:
				sum = Arrays.stream(numbersDouble).filter(filterNumbersAllowedDouble)
						.reduce((a, b) -> Double.valueOf(decimalFormat.format(a * b))).orElse(0.0);
				break;
			case DIVIDE:
				sum = Arrays.stream(numbersDouble).filter(filterNumbersAllowedDouble).reduce((a, b) -> {
					if (b == 0.0) {
						throw new DivisionByZeroException(DIVIDED_ZERO);
					}
					return Double.valueOf(decimalFormat.format(a / b));
				}).orElse(0.0);
				break;
			default:
				return 0.0;
			}
		}
		return sum;
	}

	@Override
	public int add(String params) throws NegativeNumberException {
		return (int) Calculate(params, OPERATIONS.ADD);
	}

	@Override
	public int subtract(String params) throws NegativeNumberException {
		return (int) Calculate(params, OPERATIONS.SUBTRACT);
	}

	@Override
	public double multiply(String params) throws NegativeNumberException {
		return Calculate(params, OPERATIONS.MULTIPLY);
	}

	@Override
	public double divide(String params) throws NegativeNumberException {
		return Calculate(params, OPERATIONS.DIVIDE);
	}
}
