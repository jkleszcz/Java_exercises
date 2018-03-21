package choinka;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {

    List<XmasShape> shapes = new ArrayList<>();
    Image img = Toolkit.getDefaultToolkit().getImage("background.jpg");

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img,0,0,getWidth(),getHeight(),this);
        for(XmasShape s:shapes)
            s.draw((Graphics2D)g);
    }

    public void addElements(){

        this.shapes.add(new Tree());
        this.shapes.add((new Bubble(225,515,0.3)));
        this.shapes.add((new Bubble(300,522,0.3)));
        this.shapes.add((new Bubble(405,535,0.3)));
        this.shapes.add((new Bubble(535,535,0.3)));
        this.shapes.add((new Bubble(610,520,0.3)));
        this.shapes.add((new Bubble(703,510,0.3)));

        this.shapes.add((new Bubble(315,382,0.3)));
        this.shapes.add((new Bubble(600,392,0.3)));
        this.shapes.add((new Bubble(522,415,0.3)));
        this.shapes.add((new Bubble(672,390,0.3)));
        this.shapes.add((new Bubble(420,408,0.3)));

        this.shapes.add((new Bubble(380,290,0.3)));
        this.shapes.add((new Bubble(514,310,0.3)));
        this.shapes.add((new Bubble(450,305,0.3)));
        this.shapes.add((new Bubble(600,290,0.3)));

        this.shapes.add((new Bubble(450,220,0.3)));
        this.shapes.add((new Bubble(525,217,0.3)));

        this.shapes.add((new Star(470,105,0.3)));
        this.shapes.add(new Title(50,400,3));

    }

    DrawPanel(){
        setBackground(new Color(240, 76, 31));
        setOpaque(true);
    }
}