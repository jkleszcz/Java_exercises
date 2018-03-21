package lab1;
import java.util.Scanner;

public class SimpleIO {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        int i = input.nextInt();
        double d = input.nextDouble();
        System.out.printf("Wczytano %s  %d  %f",s,i,d);
    }
}

