package tasks.oop.fraction;

import java.math.BigInteger;
import java.util.StringJoiner;

public class FractionTest {
    public static void main(String[] args) {
        System.out.println(Fraction.valueOf(3.12543));
        Fraction[] fractions = new Fraction[]{
                new Fraction(1, 1),
                new Fraction(21, 49),
                new Fraction(-5, 25),
                new Fraction(6, 17)
        };
        StringJoiner stringJoiner = new StringJoiner(" ");
        for (Fraction fraction : fractions) {
            stringJoiner.add(fraction.toString());
        }
        System.out.println(stringJoiner);
        Fraction sum = new Fraction(0, 1);
        for (Fraction fraction : fractions) {
            sum = sum.add(fraction);
        }
        System.out.println(sum);
        Fraction product = new Fraction(1, 1);
        for (Fraction fraction : fractions) {
            product = product.multiply(fraction);
        }
        System.out.println(product);
    }
}
