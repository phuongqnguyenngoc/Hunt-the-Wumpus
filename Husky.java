/** 
 * Husky.java
 * Phuong Nguyen Ngoc 
 * CS231
* Project: Hunt the Wumpus
*/

import java.awt.Graphics;
import java.awt.Color;

public class Husky extends Agent{
    private boolean visible;
    private Vertex position;
    private boolean busted;
    
    //constructor indicates husky's initial position    
    public Husky(Vertex position) {
        super(position.getRow(), position.getCol());
        this.position = position;
        visible = false;
        busted = false;
    }

    //draw husky if it is visible
    public void draw(Graphics g, int scale) {
        if (visible == false) {
            return;
        } else {
            int xpos = position.getCol()*scale;
            int ypos = position.getRow()*scale;
            // int border = 2;
            g.setColor(Color.black);
            //line7
            g.fillRect(xpos + 4*8, ypos + 4*7, 4, 4);
            g.fillRect(xpos + 4*22, ypos + 4*7, 4, 4);
            //line8
            g.fillRect(xpos + 4*7, ypos + 4*8, 4, 4);
            g.fillRect(xpos + 4*9, ypos + 4*8, 4, 4);
            g.fillRect(xpos + 4*21, ypos + 4*8, 4, 4);
            g.fillRect(xpos + 4*23, ypos + 4*8, 4, 4);
            //line9
            g.fillRect(xpos + 4*6, ypos + 4*9, 4, 4*7);
            g.fillRect(xpos + 4*24, ypos + 4*9, 4, 4*7);
            g.fillRect(xpos + 4*10, ypos + 4*9, 4, 4);
            g.fillRect(xpos + 4*20, ypos + 4*9, 4, 4);
            //line10
            g.fillRect(xpos + 4*11, ypos + 4*10, 4, 4);
            g.fillRect(xpos + 4*19, ypos + 4*10, 4, 4);
            //line11
            g.fillRect(xpos + 4*12, ypos + 4*11, 4*7, 4);
            //line16
            g.fillRect(xpos + 4*5, ypos + 4*16, 4, 4*5);
            g.fillRect(xpos + 4*25, ypos + 4*16, 4, 4*5);
            //line17
            g.fillRect(xpos + 4*12, ypos + 4*17, 4, 4);
            g.fillRect(xpos + 4*18, ypos + 4*17, 4, 4);
            //line18
            g.fillRect(xpos + 4*11, ypos + 4*18, 4*2, 4);
            g.fillRect(xpos + 4*18, ypos + 4*18, 4*2, 4);
            //line20
            g.fillRect(xpos + 4*14, ypos + 4*20, 4*3, 4);
            //line21
            g.fillRect(xpos + 4*15, ypos + 4*21, 4, 4);
            g.fillRect(xpos + 4*6, ypos + 4*21, 4, 4*2);
            g.fillRect(xpos + 4*24, ypos + 4*21, 4, 4*2);
            //line22
            g.fillRect(xpos + 4*13, ypos + 4*22, 4*5, 4);
            //line23
            g.fillRect(xpos + 4*7, ypos + 4*23, 4, 4);
            g.fillRect(xpos + 4*23, ypos + 4*23, 4, 4);
            //line24
            g.fillRect(xpos + 4*8, ypos + 4*24, 4*2, 4);
            g.fillRect(xpos + 4*21, ypos + 4*24, 4*2, 4);
            //line25
            g.fillRect(xpos + 4*10, ypos + 4*25, 4*2, 4);
            g.fillRect(xpos + 4*19, ypos + 4*25, 4*2, 4);
            //line26
            g.fillRect(xpos + 4*12, ypos + 4*26, 4*7, 4);
            //done with black

            
            g.setColor(Color.gray);
            //line8
            g.fillRect(xpos + 4*8, ypos + 4*8, 4, 4);
            g.fillRect(xpos + 4*22, ypos + 4*8, 4, 4);
            //line9
            g.fillRect(xpos + 4*7, ypos + 4*9, 4*3, 4);
            g.fillRect(xpos + 4*21, ypos + 4*9, 4*3, 4);
            //line10
            g.fillRect(xpos + 4*7, ypos + 4*10, 4, 4*9);
            g.fillRect(xpos + 4*23, ypos + 4*10, 4, 4*9);
            g.fillRect(xpos + 4*9, ypos + 4*10, 4*2, 4);
            g.fillRect(xpos + 4*20, ypos + 4*10, 4*2, 4);
            //line11
            g.fillRect(xpos + 4*10, ypos + 4*11, 4*2, 4);
            g.fillRect(xpos + 4*19, ypos + 4*11, 4*2, 4);
            //line12
            g.fillRect(xpos + 4*11, ypos + 4*12, 4*9, 4);
            //line13
            g.fillRect(xpos + 4*10, ypos + 4*13, 4*11, 4);
            //line14
            g.fillRect(xpos + 4*9, ypos + 4*14, 4*13, 4);
            g.fillRect(xpos + 4*14, ypos + 4*14, 4, 4*5);
            g.fillRect(xpos + 4*16, ypos + 4*14, 4, 4*5);
            //line15
            g.fillRect(xpos + 4*8, ypos + 4*15, 4*3, 4);
            g.fillRect(xpos + 4*20, ypos + 4*15, 4*3, 4);
            //line16
            g.fillRect(xpos + 4*6, ypos + 4*16, 4*4, 4);
            g.fillRect(xpos + 4*21, ypos + 4*16, 4*4, 4);
            //line17
            g.fillRect(xpos + 4*6, ypos + 4*17, 4*3, 4);
            g.fillRect(xpos + 4*22, ypos + 4*17, 4*3, 4);
            //line18
            g.fillRect(xpos + 4*6, ypos + 4*18, 4, 4);
            g.fillRect(xpos + 4*24, ypos + 4*18, 4, 4);
            //done with gray

            g.setColor(Color.cyan);
            g.fillRect(xpos + 4*11, ypos + 4*17, 4, 4);
            g.fillRect(xpos + 4*19, ypos + 4*17, 4, 4);

            if (busted == false) {
                //draw happy face
                g.setColor(Color.black);
                //line21
                g.fillRect(xpos + 4*12, ypos + 4*21, 4, 4);
                g.fillRect(xpos + 4*18, ypos + 4*21, 4, 4);

                g.setColor(Color.red);
                g.fillRect(xpos + 4*14, ypos + 4*23, 4*3, 4);
                g.fillRect(xpos + 4*15, ypos + 4*24, 4, 4);
            }
            else  {
                //draw sad face
                g.setColor(Color.black);
                g.fillRect(xpos + 4*12, ypos + 4*23, 4, 4);
                g.fillRect(xpos + 4*18, ypos + 4*23, 4, 4);
            }
            
            
            
        }
    }

    //turn busted to true
    public void busted() {
        busted = true;
    }

    //return visible
    public boolean getVisible() {
        return visible;
    }

    //make husky and its position visible
    public void turnVisible() {
        visible = true;
        position.setVisible(true);
    }

    //change the visible state from true to false and vice versa
    public void switchVisible() {
        if (visible == true) {
            visible = false;
        } else {
            visible = true;
        }
    }

    //return the current position
    public Vertex getPosition() {
        return position;
    }

    //return busted
    public boolean isBusted() {
        return busted;
    }

    //update position
    public void run(Vertex position ) {
        this.position = position;
    }

    //reset husky to its initial state with Vertex v being its position
    public void reset(Vertex v) {
        visible = false;
        busted = false;
        position = v;
    }

    public static void main(String[] args) {
        Husky husky = new Husky(new Vertex(1,1));
        System.out.println("Husky's initial state:");
        System.out.println("busted " + husky.isBusted());
        System.out.println("position " + husky.getPosition().getCol() + husky.getPosition().getRow());
        System.out.println("visible " + husky.getVisible());


        husky.busted();
        husky.switchVisible();
        husky.run(new Vertex(3,2));

        System.out.println("Corgi's updated state:");
        System.out.println("busted " + husky.isBusted());
        System.out.println("position " + husky.getPosition().getCol() + husky.getPosition().getRow());
        System.out.println("visible " + husky.getVisible());

    }



}