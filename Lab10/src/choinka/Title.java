package choinka;

import java.awt.*;

public class Title implements XmasShape {
    int x;
    int y;
    int xcoordinate[]={286,286,223,200,148,119,69,45,0};
    int ycoordinate[]={0,128,89,108,79,95,66,80,56};
    double scale;
    Color lineColor;
    Color fillColor;

    Title(int x, int y, double scale){
        this.x = x;
        this.y = y;
        this.scale = scale;


    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.rotate(Math.PI/(-4));
        g2d.setFont(new Font("Helvetica", Font.BOLD, 18));
        g2d.drawString("Wesołych Świąt", 20, 20);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(this.scale,this.scale);
    }
}
