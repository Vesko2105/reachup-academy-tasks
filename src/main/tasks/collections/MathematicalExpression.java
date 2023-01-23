package tasks.collections;

import tasks.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MathematicalExpression {
//    static List<Pair<Integer>> getBrackets(String expression) {
//        List<Pair<Integer>> list = new ArrayList<>();
//        String regex = ".*\\(.+\\).*";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(expression);
//        while (matcher.find(1)) {
//            Pair<Integer> pair = new Pair<>(matcher.start(), matcher.end());
//            list.add(pair);
//        }
//        return list;
//    }

    static List<Pair<Integer, Integer>> getBrackets(String expression) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        expression = expression.replaceAll(" ", "");
        for (int index = 0; index < expression.length(); index++) {
            if (expression.charAt(index) == '(') {
                stack.push(index);
            } else if (expression.charAt(index) == ')'){
                list.add(new Pair<>(stack.pop(), index));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(getBrackets("(10 / 2) + (20 + 5) * ((5 - 3) / 2)"));
    }
}
