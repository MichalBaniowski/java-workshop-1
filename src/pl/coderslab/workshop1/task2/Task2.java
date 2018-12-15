package pl.coderslab.workshop1.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 {
    public static void main(String[] args){
        System.out.println("witaj w symulatorze lotto, rozpoczynam losowanie");
        System.out.format("trafiłes %d liczb",numberOfCorrectGuesses(getLottoNumbers(), getUsernumbers()));
    }
    static int numberOfCorrectGuesses(ArrayList<Integer> lotto, ArrayList<Integer> user){
        user.removeAll(lotto);
        return 6 -user.size();
    }
    static ArrayList<Integer> getUsernumbers(){
        ArrayList<Integer> userNumbers = new ArrayList<>();
        for(int i=0; i<6; i++){
            int number;
            do{
                number = getNumber(i+1);
            }while (number <= 0 || number > 49 || userNumbers.contains(number));
            userNumbers.add(number);
        }
        return  userNumbers;
    }
    static int getNumber(int count){
        Scanner scanner = new Scanner(System.in);
        System.out.format("podaj %d liczbę ",count);
        while (!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.println("podaj poprawną liczbę");
        }
        return scanner.nextInt();
    }
    static ArrayList<Integer> getLottoNumbers(){
        ArrayList<Integer> lottoNumbers = new ArrayList<>();
        lottoNumbers = Stream.iterate(1, x->x+1)
                .limit(49)
                .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(lottoNumbers);
        lottoNumbers = lottoNumbers.stream()
                .limit(6)
                .collect(Collectors.toCollection(ArrayList::new));
        return lottoNumbers;
    }
}
