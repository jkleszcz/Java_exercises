package choinka;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tree implements XmasShape {
    List<XmasShape> branches = new ArrayList<XmasShape>();
    int x;
    int y;
    double scale;

    Tree(){
        x = 0;
        y = 0;
        scale = 1;
        this.addBranch();
    }

    @Override
    public void render(Graphics2D g2d) {
        int xtrunk[] = {485,495,505,515};
        int ytrunk[] = {650,155,155,650};
        g2d.setColor(new Color(110, 54, 11));
        g2d.fillPolygon(xtrunk,ytrunk,xtrunk.length);
        for(XmasShape b:branches)
            b.draw(g2d);


    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }

    public void addBranch(){
        this.branches.add(new Branch(214,450,1,0.95));
        this.branches.add(new Branch(300,340,0.7,0.8));
        this.branches.add(new Branch(357,255,0.5,0.6));
        this.branches.add(new Branch(414,183,0.3,0.5));
        this.branches.add(new Branch(471,150,0.1,0.2));
    }
}
