package Simple_object_movement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class SimpleObjectMovement {
    public static void main(String[] args) {
        new Myframe();
    }
}
class Myframe extends JFrame {
    Myframe() {
        setTitle("Клик");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add( new Mypanel());
        setVisible(true);
    }
}
class Mypanel extends JPanel  {
    private final ImageIcon icon = new ImageIcon("src/Simple_object_movement/smail.png");
    private final ArrayList<MyObject> list = new ArrayList<MyObject>();
    class MyObject {
        private final ImageIcon image;
        private int x;
        private int y;
        private boolean dragging;
        private int offsetX;
        private int offsetY;
        public MyObject(ImageIcon image, int x, int y) {
            this.image = image;
            this.x = x;
            this.y = y;
            this.dragging = false;
            this.offsetX = 0;
            this.offsetY = 0;
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
        public boolean isDragging() {
            return dragging;
        }
        public void setDragging(boolean dragging) {
            this.dragging = dragging;
        }
        public int getOffsetX() {
            return offsetX;
        }
        public void setOffsetX(int offsetX) {
            this.offsetX = offsetX;
        }
        public int getOffsetY() {
            return offsetY;
        }
        public void setOffsetY(int offsetY) {
            this.offsetY = offsetY;
        }
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }
    }
    Mypanel () {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getClickCount() >= 2){
                MyObject myObject = new MyObject(icon,e.getX()-25,e.getY()-15);
                list.add(myObject);
                repaint();
                }
                if (e.getButton() == MouseEvent.BUTTON3) {
                    for (int i = 0; i < list.size(); i++) {
                        MyObject obj =  list.get(i);
                        Rectangle rect = new Rectangle(obj.getX(), obj.getY(), 50, 30);
                        if (rect.contains(e.getX(), e.getY())) {
                            list.remove(i);
                            repaint();
                            break;
                        }
                    }
                }
                if (e.getButton() == MouseEvent.BUTTON1) {
                    for (MyObject obj : list) {
                        Rectangle rect = new Rectangle(obj.getX(), obj.getY(), 50, 30);
                        if (rect.contains(e.getX(), e.getY())) {
                            obj.setDragging(true);
                            obj.setOffsetX(e.getX() - obj.getX());
                            obj.setOffsetY(e.getY() - obj.getY());
                        }
                    }
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    for (MyObject obj :  list) {
                        obj.setDragging(false);
                    }
                }
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                for (MyObject obj : list) {
                    if (obj.isDragging()) {
                        obj.setX(e.getX() - obj.getOffsetX());
                        obj.setY(e.getY() - obj.getOffsetY());
                        repaint();
                    }
                }
            }
        });
        setSize(600, 600);
        setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < list.size(); i++) {
            MyObject obj = list.get(i);
            ImageIcon im = obj.getImage();
            int x = obj.getX();
            int y = obj.getY();
            g.drawImage(im.getImage(),x,y,50,30, null);
        }
    }
}