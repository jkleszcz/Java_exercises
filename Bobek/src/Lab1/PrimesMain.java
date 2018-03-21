package Lab1;

import java.util.Scanner;

public class PrimesMain {
    public static void main(String[] argv){
        Scanner read = new Scanner(System.in);
        int number = read.nextInt();
        new Primes(number);
    }
}
