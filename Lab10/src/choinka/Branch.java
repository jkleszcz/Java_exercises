package choinka;

import java.awt.*;
import java.util.Random;

public class Branch implements XmasShape {
    int x;
    int y;
    int xcoordinate[] = {286,286,223,200,148,119,69,45,0};
    int ycoordinate[] = {0,128,89,108,79,95,66,80,56};
    double scalex;
    double scaley;

    Branch(int x, int y, double scalex, double scaley){
        this.x = x;
        this.y = y;
        this.scalex = scalex;
        this.scaley = scaley;


    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,new Color(0,255,0),0,100, new Color(0,10,0));
        g2d.setPaint(grad);
        g2d.fillPolygon(this.xcoordinate,this.ycoordinate,this.xcoordinate.length);
        g2d.translate(572,0);
        g2d.scale(-1,1);
        g2d.fillPolygon(this.xcoordinate,this.ycoordinate,this.xcoordinate.length);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scalex,scaley);
    }
}
