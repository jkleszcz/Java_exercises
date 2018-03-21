package choinka;

import java.awt.*;
import java.util.Random;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color gradColor;
    Color fillColor;

    Bubble(int x, int y, double scale){
        this.x = x;
        this.y = y;
        this.scale = scale;
        Random a = new Random();
        this.fillColor = new Color(a.nextInt(256),a.nextInt(256),a.nextInt(256));
        this.gradColor = new Color(a.nextInt(256),a.nextInt(256),a.nextInt(256));
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,fillColor,0,100, gradColor);
        g2d.setPaint(grad);
        g2d.fillOval(0,0,100,100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}
