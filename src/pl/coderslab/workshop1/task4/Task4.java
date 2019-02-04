package pl.coderslab.workshop1.task4;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Task4 {
    // Dice roll program
    public static void main(String[] args) {
        int diceRoll = diceRoll(getDiceRollFromUser());
        System.out.println(diceRoll);
    }

    private static String getDiceRollFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter code of dice roll - (xDy+z) where:\n" +
                "x = number of throws of dice\n" +
                "Dy = type of dice (D6, D10 etc.\n" +
                "z = modifier");
        String diceRoll;
        while (!isFormatOk(diceRoll = scanner.nextLine())) {
            System.out.println("enter correct format");
        }
        return diceRoll;
    }

    private static int diceRoll(String roll) {
        LinkedList<String> rollList = Arrays.stream(roll.split(""))
                .filter(x -> !x.isEmpty() && !x.equals(" "))
                .collect(Collectors.toCollection(LinkedList<String>::new));

        int timesThrown = getTimesThrown(rollList);
        int diceNumber = getDiceNumber(rollList);
        int addition = getAddition(rollList);

        int result = new Random().ints(1, diceNumber + 1)
                .limit(timesThrown)
                .sum();
        return result + addition;
    }

    private static int getAddition(LinkedList<String> roll) {
        StringBuilder addition = new StringBuilder();
        int result = 0;
        if (roll.size() > 0) {
            String sign = roll.poll();
            while (roll.size() > 0) {
                addition.append(roll.poll());
            }
            result = sign.equals("+") ? Integer.parseInt(addition.toString()) : -Integer.parseInt(addition.toString());
        }
        return result;
    }

    private static int getDiceNumber(LinkedList<String> roll) {
        StringBuilder diceNumber = new StringBuilder();
        while (roll.size() > 0 &&
                !roll.peek().equals("+") &&
                !roll.peek().equals("-")) {
            diceNumber.append(roll.poll());
        }
        return Integer.parseInt(diceNumber.toString());
    }

    private static int getTimesThrown(LinkedList<String> roll) {
        StringBuilder timesThrown = new StringBuilder();
        while (!roll.peek().equals("D")) {
            timesThrown.append(roll.poll());
        }
        roll.poll();
        String timesThrown_result = timesThrown.toString();

        return timesThrown_result.isEmpty() ? 1 : Integer.parseInt(timesThrown_result);
    }

    private static boolean isFormatOk(String roll) {
        return Pattern.matches("\\s*\\d*\\s*D\\s*(3|4|6|8|10|12|20|100)\\s*((\\+|\\-)\\s*\\d+)*\\s*", roll);
    }
}
