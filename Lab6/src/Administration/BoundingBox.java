package Administration;

public class BoundingBox{
    double xmin;
    double ymin;
    double xmax;
    double ymax;
    boolean empty;

    public BoundingBox(double x1, double y1, double x2, double y2){
        this.xmin = (x1<x2)? x1:x2;
        this.xmax = (x1<x2)? x2:x1;
        this.ymin = (y1<y2)? y1:y2;
        this.ymax = (y1<y2)? y2:y1;
        this.empty = false;
    }

    public BoundingBox(){
        this.empty = true;

    }

    void addPoint(double x, double y){
        if(this.contains(x,y))
            return;
        if(x<this.xmin)
            this.xmin = x;
        if(x>this.xmax)
            this.xmax = x;
        if(y<ymin)
            this.ymin = y;
        if(y>this.ymax)
            this.ymax = y;
    }

    boolean contains(double x, double y){
        if(this.xmin<=x && this.ymin<=y && this.xmax>=x && this.ymax >=y)
            return true;
        return false;
    }

    boolean contains(BoundingBox bb){
        if(this.xmin <= bb.xmin && this.ymin <= bb.ymin && this.xmax >= bb.xmax && this.ymax >= bb.xmax)
            return true;
        return false;
    }

    /*Jezeli jeden bbox zawiera jakikolwiek wierzcholek innego to jest to sie przecinaja i ten warunek obsluguje
     *wiekszosc przypadkow. Istnieje jeden przypadek, kiedy zaden bbox nie zawiera wierzcholkow drugiego, ale mimo
     * to sie przecinaja.
     */
    boolean intersects(BoundingBox bb){
        if(this.contains(bb.xmin,bb.ymin) || this.contains(bb.xmax,bb.ymax) ||
                this.contains(bb.xmin,bb.ymax) || this.contains(bb.xmax,bb.ymin) ||
                bb.contains(this.xmin,this.ymin) || bb.contains(this.xmax,this.ymax) ||
                bb.contains(this.xmin,this.ymax) || bb.contains(this.xmax,this.ymin) ||
                (bb.xmin < this.xmin && bb.xmax>this.xmax && bb.ymin>this.ymin && bb.ymax < this.ymax) ||
                (this.xmin < bb.xmin && this.xmax>bb.xmax && this.ymin>bb.ymin && this.ymax < bb.ymax))
            return true;
        return false;
    }

    BoundingBox add(BoundingBox bb){
        this.addPoint(bb.xmin,bb.ymin);
        this.addPoint(bb.xmax,bb.ymax);
        this.addPoint(bb.xmin,bb.ymax);
        this.addPoint(bb.xmax,bb.ymin);
        return this;
    }

    boolean isEmpty(){
        return this.empty;
    }

    double getCenterX(){
        if(this.isEmpty())
            throw new NullPointerException("Coordinates of BoundingBox are not implemented");
        return (this.xmin+this.xmax)/2;
    }

    double getCenterY(){
        if(this.isEmpty())
            throw new NullPointerException("Coordinates of BoundingBox are not implemented");
        return (this.ymin+this.ymax)/2;
    }

    double distanceTo(BoundingBox bbx){
        if(this.isEmpty() || bbx.isEmpty())
            throw new NullPointerException("Coordinates of BoundingBox are not implemented");
        double lambda1 = this.getCenterX();
        lambda1 = lambda1 * 180/Math.PI;
        double fi1 = this.getCenterY();
        fi1 = fi1 * 180/Math.PI;
        double lambda2 = bbx.getCenterX();
        lambda2 = lambda2 * 180/Math.PI;
        double fi2 = bbx.getCenterY();
        fi2 = fi2 * 180/Math.PI;
        double result = Math.pow(Math.sin((fi2-fi1)/2),2) + Math.cos(fi1)*Math.cos(fi2)*Math.pow(Math.sin((lambda2-lambda1)/2),2);
        return 2*6378.14*Math.asin(Math.sqrt(result));
    }
    public String toString(){
        StringBuilder buf = new StringBuilder();
        buf.append("LINESTRING(");
        buf.append(xmin + " " + ymin + ", ");
        buf.append(xmin + " " + ymax + ", ");
        buf.append(xmax + " " + ymax + ", ");
        buf.append(xmax + " " + ymin + ", ");
        buf.append(xmin + " " + ymin + ")\n");
        buf.append("WSPÓŁRZĘDNE ŚRODKA: ");
        buf.append(this.getCenterX() +" "+ this.getCenterY());
        return buf.toString();
    }

}
