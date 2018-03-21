package Lab1;

import java.util.Scanner;

/**
 * Klasa sprawdzająca, czy podany login i hasło są zgodne z loginem i hasłem podanymi przez autora
 * @author Jakub Kleszcz
 */

public class Login {
    static String login = "UsuryjskiStrzelec";
    static String password = "passphrase";

    static void check(String u, String p){
        /**
         * Metoda wykonująca sprawdzenie poprawności
         * @param u Nazwa użytkownika podana przez klienta
         * @param p Hasło podane przez klienta
         * @return nie zwraca kompletnie nic
         */
        if(u.equals(Login.login) && p.equals(Login.password))
            System.out.println("Correct!!");
        else
            System.out.println("Incorrect!!");
    }

    public static void main(String[] argv){
        Scanner read = new Scanner(System.in);
        String userName = read.nextLine();
        String userPass = read.nextLine();

        Login.check(userName,userPass);
    }
}
