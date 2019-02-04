package pl.coderslab.workshop1.task1;

import java.util.Random;
import java.util.Scanner;
public class Task1 {
    public static void main(String[] args){
        // guesswork game 1
        int random = new Random().nextInt(100)+1;
        int guess = 0;
        int tryCount = 0;
        do{
            tryCount++;
            guess = getNumber("podaj liczbę");
            if(guess > random){
                System.out.println("za dużo");
            }
            else if(guess < random){
                System.out.println("za mało");
            }
        }while (guess != random);
        System.out.format("brawo zadłeś w %d próbie", tryCount);
    }
    static int getNumber(String prompt){
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);

        while (!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.println(prompt);
        }
        return scanner.nextInt();
    }
}
