package Lab2;

public class Punkt3D extends Punkt2D {
    double z;

    Punkt3D(double x, double y, double z){
        super(x,y);
        this.z = z;
    }

    double getZ(){
        return this.z;
    }

    void setZ(double z){
        this.z = z;
    }

    double distance(Punkt3D p){
        Punkt2D p2d = p;
        return Math.sqrt(Math.pow(super.distance(p2d),2) + Math.pow(p.z-this.z,2));
    }
}
