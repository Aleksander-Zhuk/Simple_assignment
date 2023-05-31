package Easy_emoval;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class EasyRemoval {
    public static void main(String[] args) {
        new Myframe();
    }
}
class Myframe extends JFrame {
    Myframe () {
        setTitle("Один клик картинка, два клика удаление");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add( new Mypanel());
        setVisible(true);
    }
}
class Mypanel extends JPanel implements MouseListener {
    private final ImageIcon icon = new ImageIcon("src/Easy_emoval/smail.png");
    private final ArrayList list = new ArrayList();
    static class MyObject {
        private final ImageIcon image;
        private final int x;
        private final int y;
        public MyObject(ImageIcon image, int x, int y) {
            this.image = image;
            this.x = x;
            this.y = y;
        }
        public ImageIcon getImage() {
            return image;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
    Mypanel () {
        addMouseListener(this);
        setSize(600,600);
        setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < list.size(); i++) {
            MyObject obj = (MyObject) list.get(i);
            ImageIcon im = obj.getImage();
            int x = obj.getX();
            int y = obj.getY();
            g.drawImage(im.getImage(), x, y,50,30, null);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            MyObject myObject = new MyObject(icon,e.getX()-25,e.getY()-15);
            list.add(myObject);
            repaint();
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            for (int i = 0; i < list.size(); i++) {
                MyObject obj = (MyObject) list.get(i);
                Rectangle rect = new Rectangle(obj.getX(), obj.getY(), 50, 30);
                if (rect.contains(e.getX(), e.getY())) {
                    list.remove(i);
                    repaint();
                    break;
                }
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}