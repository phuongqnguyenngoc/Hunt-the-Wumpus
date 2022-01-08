/** 
 * Wumpus.java
 * Phuong Nguyen Ngoc 
 * CS231
* Project: Hunt the Wumpus
*/

import java.awt.Graphics;
import java.awt.Color;

public class Wumpus extends Agent{
    private boolean visible;
    private Vertex position;
    private boolean dead;
    
    //constructor indicates initial position
    public Wumpus(Vertex position) {
        super(position.getRow(), position.getCol());
        this.position = position;
        visible = false;
        dead = false;
    }

    //draw the wumpus given its position if it is visible
    public void draw(Graphics g, int scale) {
        if (visible == false) {
            return;
        } else {
            int xpos = position.getCol()*scale;
            int nextX = (position.getCol())*scale/2;
            int ypos = position.getRow()*scale;
            int nextY = (position.getRow())*scale/2;
            int border = 2;
            
            g.setColor(Color.gray);
            g.fillOval(xpos + border, ypos + border, scale - 2*border, (scale * border )/2 - border);
            //if dead, draw a cross
            if (dead == true) {
                g.setColor(Color.red);
                g.drawLine(xpos + border, ypos + border, xpos + nextX - border, ypos + nextY - border);
                g.drawLine(xpos + nextX - border, ypos + border, xpos + border, ypos + nextY - border );
            }
        }
    }

    //turn boolean dead into true
    public void killTheWumpus() {
        dead = true;
    }

    //make the wumpus and its position visible
    public void turnVisible() {
        visible = true;
        position.setVisible(true);
    }

    //getter method for position
    public Vertex getWumpusPosition() {
        return position;
    }

    //getter method for dead
    public boolean isDead() {
        return dead;
    }

    //getter method for visible
    public boolean getVisible() {
        return visible;
    }

    public static void main(String[] args) {
        Wumpus wumpus = new Wumpus(new Vertex(1,1));
        System.out.println("Wumpus's initial state:");
        System.out.println("dead " + wumpus.isDead());
        System.out.println("position " + wumpus.getWumpusPosition().getCol() + wumpus.getWumpusPosition().getRow());
        System.out.println("visible " + wumpus.getVisible());


        wumpus.killTheWumpus();
        wumpus.turnVisible();

        System.out.println("Wumpus's updated state:");
        System.out.println("dead " + wumpus.isDead());
        System.out.println("position " + wumpus.getWumpusPosition().getCol() + wumpus.getWumpusPosition().getRow());
        System.out.println("visible " + wumpus.getVisible());
        
    }



}