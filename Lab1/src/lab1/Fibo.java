package lab1;
import java.util.Scanner;

public class Fibo {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        if(n>=1 && n <=45){
            int[] tab = new int[n];
            tab[0] = 1;
            tab[1] = 1;
            for(int i=0 ; i<n ; ++i){
                if(i==0 || i==1){
                    tab[i] = 1;
                }
                else{
                    tab[i] = tab[i-1] + tab[i-2];
                }
                System.out.println(tab[i]);
            }
        }
    }
}
