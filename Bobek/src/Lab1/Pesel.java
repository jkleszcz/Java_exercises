package Lab1;

public class Pesel {
    static boolean check(String p){
        if(p.length() != 11)
            return false;
        int sum = 0;
        for(int i = 0; i < p.length() - 1 ; ++i){
            if(i == 0 || i == 4 || i == 8)
                sum = sum + (p.charAt(i)-48)*9;
            else if(i == 1 || i == 5 || i == 9)
                sum = sum + (p.charAt(i)-48)*7;
            else if(i == 2 || i == 6)
                sum = sum + (p.charAt(i)-48)*3;
            else
                sum = sum + p.charAt(i)-48;
        }
        System.out.println(sum);
        if(sum%10 != p.charAt(10)-48)
            return false;
        return true;
    }
}
