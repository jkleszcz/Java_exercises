package Matrix;

public class Main {
    public static void main(String[] args){
       /* double[][] tablica = new double[4][];
        tablica[0] = new double[4];
        tablica[1] = new double[2];
        tablica[2] = new double[1];
        tablica[3] = new double[3];
        System.out.println("TABLICA WEJSCIOWA");
        for(int i=0 ; i<tablica.length ; i++){
            for(int j=0 ;j<tablica[i].length ; j++){
                tablica[i][j] = 1;
                System.out.print(tablica[i][j]+" ");
            }
            System.out.println();
        }
        double[][] dodaj = new double[4][];
        dodaj[0] = new double[4];
        dodaj[1] = new double[2];
        dodaj[2] = new double[1];
        dodaj[3] = new double[3];
        System.out.println("TABLICA POMOCNICZA");
        for(int i=0 ; i<dodaj.length ; i++){
            for(int j=0 ;j<dodaj[i].length ; j++){
                dodaj[i][j] = 2;
                System.out.print(dodaj[i][j]+" ");
            }
            System.out.println();
        }
        Matrix nowa = new Matrix(tablica);
        Matrix nowa2 = new Matrix(dodaj);
        System.out.println("\nMETODA asArray");
        nowa.asArray();
        System.out.println("\nMETODA get");
        System.out.println(nowa.get(4,4));
        System.out.println(nowa.get(2,2));
        System.out.println(nowa.get(3,2));
        System.out.println("\nMETODA toString");
        System.out.println(nowa.toString());
        System.out.println("\nMETODA reshape");
        nowa.reshape(8,2);
        System.out.println(nowa.toString());
        nowa.reshape(4,4);
        System.out.println("\nMETODA add");
        System.out.println(nowa.add(nowa));
        System.out.println("\nMETODA sub");
        System.out.println(nowa.sub(nowa));

        System.out.println("\nMETODA forbenius");
        System.out.println(nowa.frobenius());
        System.out.println();
        System.out.println(nowa);
        System.out.println(nowa.getTransposition());*/

        Matrix m = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8,9}});
        Matrix mT = m.getTransposition();
        System.out.println(mT);
    }
}
