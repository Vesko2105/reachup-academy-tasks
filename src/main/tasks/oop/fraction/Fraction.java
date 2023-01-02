package tasks.oop.fraction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

// TODO: 2.1.2023 г. Fix whole Fraction class
// TODO: 2.1.2023 г. Write tests
public class Fraction extends Number implements Comparable<Fraction> {
    public final BigInteger numerator;
    public final BigInteger denominator;

    public Fraction(BigInteger numerator, BigInteger denominator) throws InvalidDenominatorException {
        if (denominator.compareTo(BigInteger.ZERO) == 0) {
            throw new InvalidDenominatorException();
        }
        if (denominator.compareTo(BigInteger.ZERO) < 0) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }

        int sign = numerator.compareTo(BigInteger.ZERO);

        if (numerator.compareTo(denominator) == 0) {
            this.numerator = BigInteger.ONE;
            this.denominator = BigInteger.ONE;
        } else {
            BigInteger divisor = BigInteger.TWO;
            BigInteger limit1 = numerator.abs().sqrt();
            BigInteger limit2 = denominator.sqrt();
            numerator = numerator.abs();
            denominator = denominator.abs();
            while (divisor.compareTo(limit1) <= 0 &&
                    divisor.compareTo(limit2) <= 0 &&
                    numerator.compareTo(BigInteger.ONE) != 0 &&
                    denominator.compareTo(BigInteger.ONE) != 0) {
                if (numerator.remainder(divisor).compareTo(BigInteger.ZERO) == 0 &&
                        denominator.remainder(divisor).compareTo(BigInteger.ZERO) == 0) {
                    numerator = numerator.divide(divisor);
                    denominator = denominator.divide(divisor);
                }
                divisor = divisor.add(BigInteger.ONE);
            }
            divisor = numerator.compareTo(denominator) < 0 ? numerator : denominator;
            if (numerator.compareTo(BigInteger.ZERO) == 0) {
                denominator = BigInteger.ONE;
            } else if (numerator.remainder(divisor).compareTo(BigInteger.ZERO) == 0 &&
                    denominator.remainder(divisor).compareTo(BigInteger.ZERO) == 0) {
                numerator = numerator.divide(divisor);
                denominator = denominator.divide(divisor);
            }
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }

    public Fraction(int numerator, int denominator) throws InvalidDenominatorException {
        this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
    }

    @Override
    public int intValue() {
        return numerator.divide(denominator).intValue();
    }

    @Override
    public long longValue() {
        return numerator.divide(denominator).longValue();
    }

    @Override
    public float floatValue() {
        return BigDecimal.valueOf(numerator.floatValue()).divide(BigDecimal.valueOf(denominator.floatValue()), RoundingMode.HALF_EVEN).floatValue();
    }

    @Override
    public double doubleValue() {
        return BigDecimal.valueOf(numerator.doubleValue()).divide(BigDecimal.valueOf(denominator.doubleValue()), RoundingMode.HALF_EVEN).doubleValue();
    }

    @Override
    public int compareTo(Fraction other) {
        return Integer.compare(this.numerator.multiply(other.denominator).compareTo(this.denominator.multiply(other.numerator)), 0);
    }

    @Override
    public String toString() {
        return String.format("%d/%d", numerator, denominator);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (!(other instanceof Fraction otherFraction)) {
            return false;
        } else {
            return this.numerator.multiply(otherFraction.denominator).compareTo(this.denominator.multiply(otherFraction.numerator)) == 0;
        }
    }

    public Fraction negate() throws InvalidDenominatorException {
        return new Fraction(this.numerator.negate(), this.denominator);
    }

    public Fraction inverse() throws InvalidDenominatorException {
        return new Fraction(this.denominator, this.numerator);
    }

    public Fraction add(Fraction other) throws InvalidDenominatorException {
        return new Fraction(this.numerator.add(other.numerator), this.denominator);
    }

    public Fraction subtract(Fraction other) throws InvalidDenominatorException {
        return add(other.negate());
    }

    public Fraction multiply(Fraction other) throws InvalidDenominatorException {
        return new Fraction(this.numerator.multiply(other.numerator), this.denominator.multiply(other.denominator));
    }

    public Fraction divide(Fraction other) throws InvalidDenominatorException {
        return multiply(other.inverse());
    }

    public static Fraction valueOf(long value) throws InvalidDenominatorException {
        return new Fraction(BigInteger.valueOf(value), BigInteger.ONE);
    }

    public static Fraction valueOf(double value) throws InvalidDenominatorException {
        long powerOf10 = (long) Math.pow(10, String.valueOf(value).length());
        return new Fraction(BigInteger.valueOf((long) (value * powerOf10)), BigInteger.valueOf(powerOf10));
    }
}
