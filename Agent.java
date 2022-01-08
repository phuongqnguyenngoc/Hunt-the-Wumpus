
import java.awt.Graphics;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;

public class Agent {
    private int row;
    private int column; 
    //a constructor that sets the position
    public Agent(int row, int column) {
        this.row = row;
        this.column = column;
    }

    //returns the x position
    public int getRow() {
        return row;
    }

    //returns the y position
    public int getCol() {
        return column;
    }

    //sets the x position
    public void setRow(int newX) {
        this.row = newX;
    }


    //sets the y position
    public void setColumn(int newY) {
        this.column = newY;
    }

    //returns a String containing the x and y positions
    public String toString() {
        String position = "the agent position is: ";
        position += row + ", " + column;
        return position;
    }

    
    //draw the agent according to its state (will be overriden later)
    public void draw(Graphics g, int scale) {
    }

    // public static void main (String [] args) {
    //     Agent test = new Agent(1, 2);
    //     System.out.println(test.getRow());
    //     System.out.println(test.getColumn());
    //     test.setRow(3);
    //     test.setColumn(4);
    //     System.out.println(test);

    // }
}