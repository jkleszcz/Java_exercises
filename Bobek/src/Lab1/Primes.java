package Lab1;

public class Primes {
    boolean[] table;
    int max;

    Primes(int n){
        this.max = n;
        this.table = new boolean[n-2];
        findPrimes();
        writePrimes();
    }

    void findPrimes(){
        for(int i=2 ; i<Math.sqrt(max) ; ++i){
            if(!table[i-2]){
                int n = 2*i;
                while(n<max){
                    table[n-2] = true;
                    n = n+i;
                }
            }
        }
    }

    void writePrimes(){
        for(int i = 0 ; i<table.length ; ++i){
            if(!table[i])
                System.out.println(i+2);
        }
    }
}
