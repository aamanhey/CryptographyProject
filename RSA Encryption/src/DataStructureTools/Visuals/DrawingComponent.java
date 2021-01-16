package DataStructureTools.Visuals;

import javax.swing.JComponent;
import java.awt.*;

public class DrawingComponent extends JComponent {
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Rectangle rect1 = new Rectangle(200,200, 100, 100);
//        g2.draw(rect1);
        g2.fill(rect1);
    }
}
