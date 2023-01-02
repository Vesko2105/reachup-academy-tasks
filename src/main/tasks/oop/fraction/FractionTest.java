package tasks.oop.fraction;

import java.math.BigInteger;
import java.util.StringJoiner;

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
        Fraction[] fractions = new Fraction[]{};
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (Fraction fraction : fractions) {
            stringJoiner.add(fraction.toString());
        }
        System.out.println(stringJoiner);
        try {
            Fraction product = new Fraction(1, 1);
            for (Fraction fraction : fractions) {
                product = product.multiply(fraction);
            }
            System.out.println(product);
        } catch (InvalidDenominatorException e) {
            System.out.println(e.getMessage());
        }
    }
}
