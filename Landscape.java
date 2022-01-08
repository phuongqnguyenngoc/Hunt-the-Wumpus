/** 
 * Landscape.java
 * Phuong Nguyen Ngoc 
 * CS231
* Project: Hunt the Wumpus
*/

import java.lang.Math; 
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

public class Landscape {
    private int width;
    private int height;
    private LinkedList<Agent> agentList;
    private LinkedList<Agent> foreground;
    private LinkedList<Agent> background;


    //a constructor that sets the width and height fields, and initializes the agent list.
    public Landscape(int w, int h) {
        this.width = w;
        this.height = h;
        this.agentList = new LinkedList<Agent>();
        this.background = new LinkedList<Agent>();
        this.foreground = new LinkedList<Agent>();

    }

    //returns the height.
    public int getHeight() {
        return this.height;
    }

    //returns the width.
    public int getWidth() {
        return this.width;
    }

    //inserts an agent at the beginning of its list of agents.
    public void addAgent(Agent a) {
        agentList.addFirst(a);
    }

    public void addBackgroundAgent(Agent a) {
        background.addFirst(a);
    }

    public void addForegroundAgent(Agent a) {
        foreground.addFirst(a);
    }

    //returns a String representing the Landscape.
    public String toString() {
        String landscape = "The number of agents is " + agentList.size();
        return landscape;
    }

    //an added method to access the private field agentList
    public LinkedList<Agent> getLL() {
        return this.agentList;
    }

    
    
    //draw each agent on the agent 
    public void draw(Graphics g, int scale) {
        for (Agent v: background ) {
            v.draw(g, scale);
        }
        for (Agent v: foreground ) {
            v.draw(g, scale);
        }
    }

    

    // public static void main(String [] args) {
    //     Landscape test = new Landscape(500, 500);
    //     SocialAgent a = new SocialAgent(5, 5, 10);
    //     SocialAgent b = new SocialAgent(10, 10, 10);
    //     SocialAgent c = new SocialAgent(15, 15, 10);
    //     SocialAgent d = new SocialAgent(20, 20, 10);
    //     test.addAgent(a);
    //     test.addAgent(b);
    //     test.addAgent(c);
    //     test.addAgent(d);
    //     a.updateState(test);
    //     System.out.println(test);
    //     System.out.println(test.getHeight());
    //     System.out.println(test.getWidth());
    //     System.out.println(test.getNeighbors(a.getX(),a.getY(),10).size() - 1);

    // }

    
}