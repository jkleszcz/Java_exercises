package Lab2;

public class Punkt2D {
    double x;
    double y;

    Punkt2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    double getX(){
        return this.x;
    }

    double getY(){
        return this.y;
    }

    void setX(double x){
        this.x = x;
    }

    void setY(double y){
        this.y = y;
    }

    double distance(Punkt2D p){
        return Math.sqrt(Math.pow(p.x-this.x,2) + Math.pow(p.y - this.y,2));
    }
}
