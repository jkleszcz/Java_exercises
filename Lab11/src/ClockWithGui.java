import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class ClockWithGui extends JPanel {
    LocalTime time = LocalTime.now();
    Image img = Toolkit.getDefaultToolkit().getImage("rolex.png");

    ClockWithGui(){
        new ClockThread().start();
    }

    class ClockThread extends Thread{
        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();
                //System.out.printf("%02d:%02d:%02d\n",time.getHour(),time.getMinute(),time.getSecond());

                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.clearRect(0,0,getWidth(),getHeight());
        g2d.translate(getWidth()/2,getHeight()/2);

        GradientPaint grad = new GradientPaint(-50,0,new Color(255, 215, 0),100,-150, new Color(247, 255, 110));
        g2d.setPaint(grad);
        g2d.setStroke(new BasicStroke(15, CAP_ROUND,JOIN_MITER));
        g2d.drawOval(-141,-141,280,280);
        grad = new GradientPaint(-50,0,new Color(238, 255, 180),100,-150, new Color(245,255, 193));
        g2d.setPaint(grad);
        g2d.fillOval(-132,-132,261,261);
        g.drawImage(img,-140,-90,280,70,this);
        g2d.setColor(Color.black);

        for(int i=1;i<13;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            Point2D src = new Point2D.Float(0,-125);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            g.setFont(new Font("Helvetica", Font.BOLD, 12));
            g2d.drawString(Integer.toString(i),(int)trg.getX()-5,(int)trg.getY()+4);

            Point2D src2 = new Point2D.Float(0,-100);
            Point2D trg2 = new Point2D.Float();
            at.transform(src2,trg2);
            Point2D src3 = new Point2D.Float(0,-110);
            Point2D trg3 = new Point2D.Float();
            at.transform(src3,trg3);
            g2d.setStroke(new BasicStroke(2, CAP_ROUND,JOIN_MITER));
            g2d.drawLine((int)trg3.getX(),(int)trg3.getY(),(int)trg2.getX(),(int)trg2.getY());

        }

        AffineTransform saveAT = g2d.getTransform();
        g2d.setStroke(new BasicStroke(5, CAP_ROUND,JOIN_MITER));
        g2d.rotate(time.getHour()%12*2*Math.PI/12 + (2*Math.PI/12)*time.getMinute()/60);
        g2d.drawLine(0,0,0,-50);
        g2d.setTransform(saveAT);

        AffineTransform saveAT2 = g2d.getTransform();
        g2d.setStroke(new BasicStroke(5, CAP_ROUND,JOIN_MITER));
        g2d.rotate(time.getMinute()%60*2*Math.PI/60 + (2*Math.PI/60)*time.getSecond()/60);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT2);

        AffineTransform saveAT3 = g2d.getTransform();
        g2d.setStroke(new BasicStroke(2, CAP_ROUND,JOIN_MITER));
        g2d.rotate(time.getSecond()%60*2*Math.PI/60);
        g2d.drawLine(0,0,0,-100);
        g2d.setTransform(saveAT3);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");

        frame.setContentPane(new ClockWithGui());

        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
