package pl.coderslab.workshop1.task2;

import java.util.*;
import java.util.stream.Collectors;

public class Task2 {
    public static void main(String[] args){
        // lotto game simulator
        System.out.println("witaj w symulatorze lotto, rozpoczynam losowanie");
        ArrayList<Integer> lottoNums = getLottoNumbers();
        if(args.length == 1 && args[0].equalsIgnoreCase("WhoseYourDaddy")){
            System.out.print("wylosowane liczby: ");
            for (int i : lottoNums){
                System.out.print(i + " ");
            }
            System.out.println();
        }
        int numberOfCorrectGuesses = numberOfCorrectGuesses(lottoNums, getUsernumbers());
        String prompt = numberOfCorrectGuesses > 4 || numberOfCorrectGuesses == 0 ? "liczb" : "liczby";
        System.out.format("trafiłes %d %s",numberOfCorrectGuesses, prompt);
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
        return new Random().ints(1,49)
                .distinct()
                .limit(6)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
