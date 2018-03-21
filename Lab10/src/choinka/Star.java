package choinka;

import java.awt.*;
import java.util.Random;

public class Star implements XmasShape{
    int x;
    int y;
    double scale;
    Color fillColor;

    Star(int x, int y, double scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.fillColor = new Color(212, 187, 45);
    }

    @Override
    public void render(Graphics2D g2d) {
        int xstar[] = {100,125,200,150,160,100,40,50,0,75};
        int ystar[] = {10,75,85,125,190,150,190,125,85,75};
        g2d.setColor(fillColor);
        g2d.fillPolygon(xstar,ystar,xstar.length);

    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
