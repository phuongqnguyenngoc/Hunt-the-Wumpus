/** 
 * Vertex.java
 * Phuong Nguyen Ngoc 
 * CS231
* Project: Hunt the Wumpus
*/

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class Vertex extends Agent {
    private ClosedHashmap<Direction, Vertex> adjacent;
    private boolean visible;
    private int cost;
    private boolean marked;
    private Vertex teleport;
   
    public enum Direction {NORTH, SOUTH, EAST, WEST}

    //constructor
    public Vertex(int row, int col) {
        super(row, col);
        this.adjacent = new ClosedHashmap<Direction, Vertex>(4);
        this.visible = false;
        this.cost = 0;
        this.marked = false;
        this.teleport = null;
                
    }

    //return the vertex at the opposite direction from d
    public static Direction opposite (Direction d) {
        if (d == Direction.NORTH) {
            return Direction.SOUTH;
        } else if (d == Direction.SOUTH) {
            return Direction.NORTH;
        } else if (d == Direction.EAST) {
            return Direction.WEST;
        } else {
            return Direction.EAST;
        }
    }

    //getter method for visible
    public boolean getVisible() {
        return visible;
    }

    //setter method for visible
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    //getter method for cost
    public int getCost() {
        return cost;
    }

    //setter method for cost
    public void setCost(int cost) {
        this.cost = cost;
    }

    //getter method for marked
    public boolean getMarked() {
        return marked;
    }

    //setter method for marked
    public void setMarked( boolean marked) {
        this.marked = marked;
    }

    //connect 2 vertex objects in the given direction
    public void connect(Vertex other, Direction dir) {
        if (adjacent.get(dir) == null) {
            adjacent.put(dir, other);
        }
    }

    //disconnect this vertex from every neighboring vertex
    public void disconnect() {
        adjacent = new ClosedHashmap<Direction, Vertex>(4);
    }

    //return neighboring Vertex in the direction dir
    public Vertex getNeighbor(Direction dir) {
        return adjacent.get(dir);
    }

    //return list of connected neighboring vertex
    public ClosedHashmap<Direction,Vertex> getNeighbors() {
        return this.adjacent;
    }

    //getter method for teleport
    public Vertex getTeleport() {
        return teleport;
    }

    //setter method for teleport
    public void setTeleport(Vertex teleport) {
        this.teleport = teleport;
    }

    //toString method print out the cost, neighbor and marked of the vertex
    public String toString() {
        String vertex =  "\n" + "Cost " + getCost() + "\n";
        vertex += "Neighbors: " + adjacent.size() + "\n";
        vertex += "Mark: " + getMarked();
        return vertex;
    }

    //draw the vertex if it is visible
    public void draw(Graphics g, int scale) {
        int xpos = this.getCol()*scale;
        int ypos = (int)this.getRow()*scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;

        if (teleport != null) {
            g.setColor(Color.black.darker().darker());
            g.fillRect(xpos + 4*25 + 4*1, ypos + 4, 4*4, 4*6);
            g.setColor(Color.red.darker());
            g.fillRect(xpos + 4*25 + 4*2, ypos + 4*2, 4*3, 4*5);
            g.setColor(Color.black.darker().darker());
            g.fillRect(xpos + 4*25 + 4*3, ypos + 4*3, 4, 4);

            
            

        }
        if (!this.visible) {
            return;
            
        } 
        
        
        // draw rectangle for the walls of the room
        if (this.cost < 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.black);
        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
        // draw doorways as boxes
        g.setColor(Color.green.darker().darker());
        if (this.adjacent.containsKey(Direction.NORTH)) 
            g.fillRect(xpos + 16, ypos - 4, scale - 32 , 8);
        if (this.adjacent.containsKey(Direction.SOUTH))
            g.fillRect(xpos + 16, ypos + scale - 4, scale - 32 , 8);
        if (this.adjacent.containsKey(Direction.WEST))
            g.fillRect(xpos - 4, ypos + 16, 8 , scale - 32);
        if (this.adjacent.containsKey(Direction.EAST))
            g.fillRect(xpos + scale - 4, ypos + 16, 8 , scale - 32);

        
    }



    public static void main(String[] args) {
        Vertex test1 = new Vertex(0,0);
        Vertex test2 = new Vertex(1,1);

        test1.setVisible(true);
        test1.setMarked(true);
        System.out.println(test1.opposite(Direction.SOUTH));
        System.out.println(test1.opposite(Direction.NORTH));
        System.out.println(test1.opposite(Direction.WEST));
        System.out.println(test1.opposite(Direction.EAST));

        test1.setCost(3);
        test2.setCost(6);
        

        

        test1.connect(test2, Direction.EAST);
        System.out.println(test1);
        System.out.println(test1.getNeighbors());

    }




}