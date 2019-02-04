package pl.coderslab.workshop1.task3;

import java.util.Scanner;

public class Task3 {
    // guesswork game 2
    final static int LESS = 1, MORE = 2, CORRECT = 0;

    public static void main(String[] args) throws InterruptedException {
        int min = 0, max = 1001;
        int number = 0, answer = 0;
        int counter = 0;
        int guess = -1;
        System.out.println("Pomyśl i wpisz liczbę z zakresu 0 do 1000 a ja ją zgadnę w max 10 ruchach");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt() || ((number = scanner.nextInt()) > max || number < min)) {
            scanner.nextLine();
        }
        while (guess != number){
            System.out.println("zgaduję...");
            counter++;
            Thread.sleep(1000);
            System.out.println(guess = doGuess(min, max));
            System.out.printf("wybierz liczbę: %d -za mało, %d -za dużo, %d -trafiłeś", LESS, MORE, CORRECT);
            while (!scanner.hasNextInt() || ((answer = scanner.nextInt()) > 2 || answer < 0)){
                scanner.nextLine();
            }
            switch (answer){
                case 0:
                    if(guess != number){
                        System.out.println("oszukujesz");
                        guess = number;
                    }
                    else System.out.printf("zagadłem w %d próbie", counter);
                    break;
                case 1:
                    if(guess == number || guess > number){
                        System.out.println("oszukujesz");
                        guess = number;
                    }
                    else {
                        min = guess;
                    }
                    break;
                case 2:
                    if(guess == number || guess < number){
                        System.out.println("oszukujesz");
                        guess = number;
                    }
                    else {
                        max = guess;
                    }
            }
        }
        scanner.close();

    }

    static int doGuess(int min, int max) {
        return (max - min) / 2 + min;
    }
}
