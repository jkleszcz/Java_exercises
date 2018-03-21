package Lab2;

import java.util.LinkedList;
import java.util.Scanner;

public class Test {
    static LinkedList<Punkt3D> punkty = new LinkedList<>();

    static void readPoint(double x, double y, double z){
        Punkt3D p = new Punkt3D(x,y,z);
        punkty.add(p);
    }

    static void showPoints(){
        for(Punkt3D p : punkty){
            System.out.print("("+p.x+", "+p.y+", "+p.z+")");
        }
    }

    static double countDistance(Punkt3D p1, Punkt3D p2){
        return p1.distance(p2);
    }

    public static void main(String[] argv){
        Scanner input = new Scanner(System.in);
        Loop:
        while(true){
            System.out.println("Oczekuje na rozkazy: ");
            int order = input.nextInt();
            switch (order){
                case 1:
                    double x, y, z;
                    x = input.nextDouble();
                    y = input.nextDouble();
                    z = input.nextDouble();
                    readPoint(x,y,z);
                    continue;
                case 2:
                    showPoints();
                    continue;
                case 3:
                    int p1,p2;
                    p1 = input.nextInt();
                    p2 = input.nextInt();
                    System.out.println(countDistance(punkty.get(p1),punkty.get(p2)));
                    continue;
                case 4:
                    break Loop;
                default:
                    continue;
            }
        }
    }
}
