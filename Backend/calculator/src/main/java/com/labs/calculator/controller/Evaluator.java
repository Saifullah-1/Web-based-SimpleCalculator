package com.labs.calculator.controller;

import org.springframework.stereotype.Service;

@Service
public class Evaluator {
    private double a;
	private double b;
	private String op;

	public double getA() {
		return this.a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return this.b;
	}

	public void setB(double b) {
		this.b = b;
	}

	public String getOp() {
		return this.op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String evaluate() {
		try {
			switch (op) {
				case "+":
					return String.valueOf(this.a + this.b);
				case "-":
					return String.valueOf(this.a - this.b);
				case "*":
					return String.valueOf(this.a * this.b);
				case "d":
					return String.valueOf(this.a / this.b);
				case "**":
					return String.valueOf(Math.pow(this.a, this.b));
				case "per":
					return String.valueOf(this.a * this.b / 100);
				default:
					throw new ArithmeticException("Invalid operation1");
			}
		} catch (Exception e) {
			return "Error";
		}
	}
}
