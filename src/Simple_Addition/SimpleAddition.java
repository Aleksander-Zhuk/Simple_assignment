package Simple_Addition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimpleAddition extends JPanel implements MouseListener {
    private int x = -100,y = -100;
    private final ImageIcon icon = new ImageIcon("src/Simple_Addition/smail.png");

    SimpleAddition () {
        addMouseListener(this);
        setSize(500,500);
        setVisible(true);
    }
   public void paint (Graphics g) {
        g.drawImage(icon.getImage(), x, y, 50,30,null);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
        x = e.getX()-10;
        y = e.getY()-10;
        repaint();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(500, 500);
        frame.add(new SimpleAddition());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}