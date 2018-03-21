package Lab1;

import java.util.Scanner;

public class PeselMain {
    public static void main(String[] argv) {
        Scanner read = new Scanner(System.in);
        String pesel = read.nextLine();
        System.out.println(Pesel.check(pesel));
    }
}
