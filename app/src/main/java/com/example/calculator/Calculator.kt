package com.example.calculator

/**
 * This class provides methods for performing basic calculations.
 */
class Calculator {
    /**
     * Adds two numbers.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The sum of the two numbers.
     */
    fun add(a: Double, b: Double) = a + b

    /**
     * Subtracts two numbers.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The difference between the two numbers.
     */
    fun subtract(a: Double, b: Double) = a - b

    /**
     * Multiplies two numbers.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The product of the two numbers.
     */
    fun multiply(a: Double, b: Double) = a * b

    /**
     * Divides two numbers.
     *
     * @param a The first number.
     * @param b The second number.
     */
    fun divide(a: Double, b: Double): Double {
        if (b == 0.0) return Double.NaN
        return a / b
    }
}