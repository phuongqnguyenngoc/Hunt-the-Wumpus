/** 
 * Hunter.java
 * Phuong Nguyen Ngoc 
 * CS231
* Project: Hunt the Wumpus
*/

import java.awt.Graphics;
import java.awt.Color;

public class Hunter extends Agent {
    private Vertex position;
    private boolean armed;
    private boolean eaten;

    //constructor
    public Hunter(Vertex start) {
        super(start.getRow(), start.getCol());
        position = start;
        armed = false;
        start.setVisible(true);
    }

    //draw the hunter according to the coordinate of its position
    public  void draw(Graphics g, int scale) {
        int xpos = position.getCol()*scale;
        int ypos = position.getRow()*scale;
        int border = 2;
        
        //hunter is a round pink agent
        g.setColor(Color.pink);
        g.fillOval(xpos + border, ypos + border, scale - 2*border, (scale * border )/2 - border);
        if (eaten == true) {
            //if eaten, draw a cross
            g.setColor(Color.red);
            g.fillOval(xpos + border, ypos + border, scale - 2*border, (scale * border )/2 - border);
        }
        if (armed == true) {

            g.setColor(Color.magenta);
            String shoot = "SHOOT";
            g.drawString(shoot, (position.getCol() + 1) * scale, (position.getRow() + 1) * scale);
        }
    }

    //return current position
    public Vertex getPosition() {
        return position;
    }

    //set current position
    public void updatePosition(Vertex v) {
        position = v;
        v.setVisible(true);
    }

    //change boolean armed into true
    public void getArmed() {
        armed = true;
    }

    //return armed
    public boolean armed() {
        return armed;
    }

    //shoot to the neighboring Vertex given the direction
    public Vertex shoot(Vertex.Direction dir) {
        return position.getNeighbor(dir);
    }

    //turn boolean eaten into true
    public void die() {
        eaten = true;
    }

    //return eaten
    public boolean isEaten() {
        return eaten;
    }

    public static void main(String[] args) {
        Vertex start = new Vertex(1,1);
        Vertex neighbor = new Vertex(1,2);
        start.connect(neighbor, Vertex.Direction.EAST);
        Hunter hunter = new Hunter(start);
        System.out.println("Hunter's initial state:");
        System.out.println("eaten " + hunter.isEaten());
        System.out.println("armed " + hunter.armed());
        System.out.println("shooting East: Vertex " + hunter.shoot(Vertex.Direction.EAST).getRow() + hunter.shoot(Vertex.Direction.EAST).getCol());
        System.out.println("armed " + hunter.armed());
        System.out.println("position " + hunter.getPosition().getRow() + hunter.getPosition().getCol());

        hunter.die();
        hunter.getArmed();
        hunter.updatePosition(new Vertex(3,2));

        System.out.println("Hunter's updated state:");
        System.out.println("eaten " + hunter.isEaten());
        System.out.println("armed " + hunter.armed());
        System.out.println("armed " + hunter.armed());
        System.out.println("position " + hunter.getPosition().getRow() + hunter.getPosition().getCol());

        
    }
}