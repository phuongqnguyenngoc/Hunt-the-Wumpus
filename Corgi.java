/** 
 * Corgi.java
 * Phuong Nguyen Ngoc 
 * CS231
* Project: Hunt the Wumpus
*/

import java.awt.Graphics;
import java.awt.Color;

public class Corgi extends Agent {
    private Vertex position;
    private boolean armed;
    private boolean busted;
    private int dir;
    public boolean evenMove;
    private boolean teleport;
    private boolean win;
    

    //constructor indicates corgi's initial position
    public Corgi(Vertex start) {
        super(start.getRow(), start.getCol());
        position = start;
        armed = false;
        start.setVisible(true);
        dir = 0;
        evenMove = true;
        busted = false;
        teleport = false;
        win = false;        
    }

    //return direction of shooting
    public int getDir() {
        return dir;
    }

    //set direction of shooting
    public void setDir(int dir) {
        this.dir = dir;
    }

    //draw corgi and the pokeball if corgi is armed
    public  void draw(Graphics g, int scale) {
        
        int xpos = position.getCol()*scale;
        int ypos = position.getRow()*scale;

        g.setColor(Color.black);
        //line5
        g.fillRect(xpos + 4*7, ypos + 4*5, 4*2, 4*1);
        g.fillRect(xpos + 4*22, ypos + 4*5, 4*2, 4*1);
        //line6
        g.fillRect(xpos + 4*6, ypos + 4*6, 4, 4);
        g.fillRect(xpos + 4*9, ypos + 4*6, 4, 4);
        g.fillRect(xpos + 4*21, ypos + 4*6, 4, 4);
        g.fillRect(xpos + 4*24, ypos + 4*6, 4, 4);
        //line7
        g.fillRect(xpos + 4*5, ypos + 4*7, 4, 4);
        g.fillRect(xpos + 4*10, ypos + 4*7, 4, 4*2);
        g.fillRect(xpos + 4*20, ypos + 4*7, 4, 4*2);
        g.fillRect(xpos + 4*25, ypos + 4*7, 4, 4);
        //line8
        g.fillRect(xpos + 4*5, ypos + 4*8, 4, 4*5);
        g.fillRect(xpos + 4*25, ypos + 4*8, 4, 4*5);
        //line9
        g.fillRect(xpos + 4*11, ypos + 4*9, 4, 4*2);
        g.fillRect(xpos + 4*19, ypos + 4*9, 4, 4*2);
        //line11
        g.fillRect(xpos + 4*12, ypos + 4*11, 4*7, 4);
        //line13
        g.fillRect(xpos + 6*4, ypos + 13*4, 4, 4);
        g.fillRect(xpos + 24*4, ypos + 13*4, 4, 4);
        //line14
        g.fillRect(xpos + 7*4, ypos + 14*4, 4, 4);
        g.fillRect(xpos + 23*4, ypos + 14*4, 4, 4);
        //line15
        g.fillRect(xpos + 6*4, ypos + 15*4, 4, 4*9);
        g.fillRect(xpos + 24*4, ypos + 15*4, 4, 4*9);
        //line16
        g.fillRect(xpos + 4*12, ypos + 4*16, 4, 4*2);
        g.fillRect(xpos + 4*18, ypos + 4*16, 4, 4*2);
        //line17
        g.fillRect(xpos + 4*11, ypos + 4*17, 4, 4);
        g.fillRect(xpos + 4*19, ypos + 4*17, 4, 4);
        //line20
        g.fillRect(xpos + 4*14, ypos + 4*20, 4, 4*2);
        g.fillRect(xpos + 4*16, ypos + 4*20, 4, 4*2);
        //line21
        g.fillRect(xpos + 4*12, ypos + 4*21, 4, 4);
        g.fillRect(xpos + 4*18, ypos + 4*21, 4, 4);
        g.fillRect(xpos + 4*15, ypos + 4*21, 4, 4*2);
        //line22
        g.fillRect(xpos + 4*13, ypos + 4*22, 4, 4);
        g.fillRect(xpos + 4*17, ypos + 4*22, 4, 4);
        //line23
        g.fillRect(xpos + 4*14, ypos + 4*23, 4*3, 4);
        //line24
        g.fillRect(xpos + 7*4, ypos + 24*4, 4, 4);
        g.fillRect(xpos + 23*4, ypos + 24*4, 4, 4);
        //line25
        g.fillRect(xpos + 8*4, ypos + 25*4, 4*2, 4);
        g.fillRect(xpos + 21*4, ypos + 25*4, 4*2, 4);
        //line26
        g.fillRect(xpos + 10*4, ypos + 26*4, 4*2, 4);
        g.fillRect(xpos + 19*4, ypos + 26*4, 4*2, 4);
        //line27
        g.fillRect(xpos + 12*4, ypos + 27*4, 7*4, 4);
        //done with black

        g.setColor(Color.orange);
        //line6
        g.fillRect(xpos + 4*7, ypos + 4*6, 4*2, 4);
        g.fillRect(xpos + 4*22, ypos + 4*6, 4*2, 4);
        //line7
        g.fillRect(xpos + 4*6, ypos + 4*7, 4, 4*6);
        g.fillRect(xpos + 4*24, ypos + 4*7, 4, 4*6);
        g.fillRect(xpos + 4*9, ypos + 4*7, 4, 4*2);
        g.fillRect(xpos + 4*21, ypos + 4*7, 4, 4*2);
        //line9
        g.fillRect(xpos + 4*10, ypos + 4*9, 4, 4*2);
        g.fillRect(xpos + 4*20, ypos + 4*9, 4, 4*2);
        //line11
        g.fillRect(xpos + 4*11, ypos + 4*11, 4, 4);
        g.fillRect(xpos + 4*19, ypos + 4*11, 4, 4);
        //line12
        g.fillRect(xpos + 4*11, ypos + 4*12, 4*3, 4*4);
        g.fillRect(xpos + 4*17, ypos + 4*12, 4*3, 4*4);
        g.fillRect(xpos + 4*14, ypos + 4*12, 4*3, 4);
        //line13
        g.fillRect(xpos + 4*7, ypos + 4*13, 4, 4);
        g.fillRect(xpos + 4*23, ypos + 4*13, 4, 4);
        //line14
        g.fillRect(xpos + 4*8, ypos + 4*14, 4*3, 4*4);
        g.fillRect(xpos + 4*20, ypos + 4*14, 4*3, 4*4);
        //line15
        g.fillRect(xpos + 4*7, ypos + 4*15, 4, 4*5);
        g.fillRect(xpos + 4*23, ypos + 4*15, 4, 4*5);
        //line16
        g.fillRect(xpos + 4*13, ypos + 4*16, 4, 4*3);
        g.fillRect(xpos + 4*17, ypos + 4*16, 4, 4*3);
        //line17
        g.fillRect(xpos + 4*14, ypos + 4*17, 4, 4);
        g.fillRect(xpos + 4*16, ypos + 4*17, 4, 4);
        //line18
        g.fillRect(xpos + 4*10, ypos + 4*18, 4*3, 4);
        g.fillRect(xpos + 4*18, ypos + 4*18, 4*3, 4);
        //line19
        g.fillRect(xpos + 4*11, ypos + 4*19, 4*2, 4);
        g.fillRect(xpos + 4*18, ypos + 4*19, 4*2, 4);
        //done with orange

        g.setColor(Color.pink);
        //line7
        g.fillRect(xpos + 4*7, ypos + 4*7, 4*2, 4*6);
        g.fillRect(xpos + 4*22, ypos + 4*7, 4*2, 4*6);
        //line9
        g.fillRect(xpos + 4*9, ypos + 4*9, 4, 4*4);
        g.fillRect(xpos + 4*21, ypos + 4*9, 4, 4*4);
        //line11
        g.fillRect(xpos + 4*10, ypos + 4*11, 4, 4*2);
        g.fillRect(xpos + 4*20, ypos + 4*11, 4, 4*2);
        //line13
        g.fillRect(xpos + 4*8, ypos + 4*13, 4*3, 4);
        g.fillRect(xpos + 4*20, ypos + 4*13, 4*3, 4);
        //line20
        // g.fillRect(xpos + 4*14, ypos + 4*20, 4*3, 4*2);
        //done with pink

        g.setColor(Color.red);
        //line24
        g.fillRect(xpos + 4*14, ypos + 4*24, 4*3, 4);
        //line25
        g.fillRect(xpos + 4*15, ypos + 4*25, 4, 4);
        
        //if corgi is busted, overdraw so that happy face -> sad face
        if (busted == true ) {
            g.setColor(Color.white);
            g.fillRect(xpos + 4*12, ypos + 4*21, 4, 4);
            g.fillRect(xpos + 4*18, ypos + 4*21, 4, 4);
            g.fillRect(xpos + 4*13, ypos + 4*22, 4, 4);
            g.fillRect(xpos + 4*17, ypos + 4*22, 4, 4);
            g.fillRect(xpos + 4*14, ypos + 4*24, 4*3, 4);
            g.fillRect(xpos + 4*15, ypos + 4*25, 4, 4);

            g.setColor(Color.black);
            g.fillRect(xpos + 4*13, ypos + 4*24, 4, 4);
            g.fillRect(xpos + 4*17, ypos + 4*24, 4, 4);
            
        }
        //if corgi is armed, draw the pokeball
        if (armed == true  ) {
            //if dir == 0, corgi has not shot yet
            //else, change the coordinate according to the shooting direction
            if (dir == 1) {
                ypos = (position.getRow() - 1)*scale;                
            } else if (dir ==2) {
                ypos = (position.getRow() + 1)* scale;
            } else if (dir ==3) {
                xpos = (position.getCol() - 1)* scale;
            } else if (dir ==4) {
                xpos = (position.getCol() + 1)* scale;
            }

            g.setColor(Color.black);
            //line1
            g.fillRect(xpos + 4*12 + 2*5, ypos + 4 + 2*1, 2*4, 2);
            //line2
            g.fillRect(xpos + 4*12 + 2*3, ypos + 4 + 2*2, 2*2, 2);
            g.fillRect(xpos + 4*12 + 2*9, ypos + 4 + 2*2, 2*2, 2);
            //line3
            g.fillRect(xpos + 4*12 + 2*2, ypos + 4 + 2*3, 2, 2*2);
            g.fillRect(xpos + 4*12 + 2*11, ypos + 4 + 2*3, 2, 2*2);
            //line5
            g.fillRect(xpos + 4*12 + 2*1, ypos + 4 + 2*5, 2, 2*4);
            g.fillRect(xpos + 4*12 + 2*12, ypos + 4 + 2*5, 2, 2*4);
            //line6
            g.fillRect(xpos + 4*12 + 2*6, ypos + 4 + 2*6, 2*2, 2);
            //line7
            g.fillRect(xpos + 4*12 + 2*2, ypos + 4 + 2*7, 2*4, 2);
            g.fillRect(xpos + 4*12 + 2*8, ypos + 4 + 2*7, 2*4, 2);
            //line8
            g.fillRect(xpos + 4*12 + 2*3, ypos + 4 + 2*8, 2*3, 2);
            g.fillRect(xpos + 4*12 + 2*8, ypos + 4 + 2*8, 2*3, 2);
            //line9
            g.fillRect(xpos + 4*12 + 2*6, ypos + 4 + 2*9, 2*2, 2);
            g.fillRect(xpos + 4*12 + 2*2, ypos + 4 + 2*9, 2, 2*2);
            g.fillRect(xpos + 4*12 + 2*11, ypos + 4 + 2*9, 2, 2*2);
            //line11
            g.fillRect(xpos + 4*12 + 2*3, ypos + 4 + 2*11, 2*2, 2);
            g.fillRect(xpos + 4*12 + 2*9, ypos + 4 + 2*11, 2*2, 2);
            //line12
            g.fillRect(xpos + 4*12 + 2*5, ypos + 4 + 2*12, 2*4, 2);
            //done with black

            g.setColor(Color.red);
            //line2
            g.fillRect(xpos + 4*12 + 2*5, ypos + 4 + 2*2, 2*4, 2*4);
            //line3
            g.fillRect(xpos + 4*12 + 2*3, ypos + 4 + 2*3, 2*2, 2*5);
            g.fillRect(xpos + 4*12 + 2*9, ypos + 4 + 2*3, 2*2, 2*5);
            //line5
            g.fillRect(xpos + 4*12 + 2*2, ypos + 4 + 2*5, 2*4, 2*2);
            g.fillRect(xpos + 4*12 + 2*8, ypos + 4 + 2*5, 2*4, 2*2);
            //done with red

            g.setColor(Color.white);
            g.fillRect(xpos + 4*12 + 2*4, ypos + 4 + 2*4, 2, 2);
        }
    }

    //return position
    public Vertex getPosition() {
        return position;
    }

    //set position
    public void updatePosition(Vertex v) {
        position = v;
        v.setVisible(true);
    }

    //set win
    public void setWin(boolean win) {
        this.win = win;
    }

    //return win
    public boolean getWin() {
        return win;
    }

    //set armed
    public void setArmed(boolean armed) {
        this.armed = armed;
    }

    //return armed
    public boolean armed() {
        return armed;
    }

    //return the neighboring Vertex given the direction
    public Vertex shoot(Vertex.Direction dir) {
        return position.getNeighbor(dir);
    }

    //set busted to true
    public void busted() {
        busted = true;
    }

    //return busted
    public boolean isBusted() {
        return busted;
    }

    //change evenMove to the opposite state and return evenMove
    public boolean switchMove() {
        if(evenMove ==true) {
            evenMove = false;
        }
        else{
            evenMove = true;
        }
        return evenMove;
    }

    //reset Corgi to its initial state with its position being Vertex v
    public void reset(Vertex v) {
        busted = false;
        armed = false;
        dir = 0;
        evenMove = true;
        position = v;
        position.setVisible(true);
        win = false;
    }

    //return teleport state
    public boolean getTeleport() {
        return this.teleport;
    }

    //set teleport state
    public void setTeleport(boolean teleport) {
        this.teleport = teleport;
    }

    public static void main(String[] args) {
        Corgi corgi = new Corgi(new Vertex(1,1));
        System.out.println("Corgi's initial state:");
        System.out.println("busted " + corgi.isBusted());
        System.out.println("win " + corgi.getWin());
        System.out.println("teleport "+ corgi.getTeleport());
        System.out.println("shooting direction " + corgi.getDir());
        System.out.println("armed " + corgi.armed());
        System.out.println("position " + corgi.getPosition().getCol() + corgi.getPosition().getRow());

        corgi.busted();
        corgi.setWin(true);
        corgi.setTeleport(true);
        corgi.setDir(1);
        corgi.setArmed(true);
        corgi.updatePosition(new Vertex(3,2));

        System.out.println("Corgi's updated state:");
        System.out.println("busted " + corgi.isBusted());
        System.out.println("win " + corgi.getWin());
        System.out.println("teleport "+ corgi.getTeleport());
        System.out.println("shooting direction " + corgi.getDir());
        System.out.println("armed " + corgi.armed());
        System.out.println("position " + corgi.getPosition().getCol() + corgi.getPosition().getRow());

    }


}