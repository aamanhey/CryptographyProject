package DataStructureTools.Visuals;

import DataStructureTools.Graph.Graph;

import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Drawing extends Canvas {

    public static void main(String[] args) {
        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new Drawing();
        canvas.setSize(400, 400);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        paint_node(g, 100, 100, 100);
    }

    public void paint_node(Graphics g, int x, int y, int size){
        // size should be the percentage of full size
        int size_transform = size / 100;
        if(size_transform < 0){
            size_transform = size_transform * -1;
        }
        g.fillOval(x, y, 200 * size_transform, 200 * size_transform);
    }

    public void paint_graph(Graph graph, Graphics g){
        paint_node(g, 100, 100, 100);
    }
}