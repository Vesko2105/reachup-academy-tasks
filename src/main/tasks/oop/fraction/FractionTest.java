package tasks.oop.fraction;

import java.math.BigInteger;

public class FractionTest {
    public static void main(String[] args) {
        try {
            Fraction fraction = new Fraction(BigInteger.valueOf(2L), BigInteger.valueOf(10L));
            System.out.println(new Fraction(1, 1).equals(new Fraction(1, 1)));
            System.out.println(new Fraction(1, 1).equals(new Fraction(2, 2)));
            System.out.println(new Fraction(1, -1).equals(new Fraction(-5, 5)));
            System.out.println(new Fraction(0, 1).equals(new Fraction(0, 5)));
            System.out.println(Fraction.valueOf(3.12543));
        } catch (InvalidDenominatorException e) {
            throw new RuntimeException(e);
        }

    }
}
