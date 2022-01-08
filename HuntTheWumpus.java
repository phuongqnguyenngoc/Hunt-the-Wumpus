/** 
 * HuntTheWumpus.java
 * Phuong Nguyen Ngoc 
 * CS231
* Project: Hunt the Wumpus
* Using InteractiveLandscapeDisplay.java as a template
*/

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.Point;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;



import java.util.*;

public class HuntTheWumpus {
    private JFrame win;
    private LandscapePanel canvas;    
    private int scale;
    JLabel announcement;

    // controls whether the game is playing or exiting
    private enum PlayState { PLAY, STOP }
    private PlayState state;


    private Graph graph;
    private Hunter hunter;
    private Wumpus wumpus;
    private Landscape scape;



    public HuntTheWumpus() {
        // The game should begin in the play state.
        this.state = PlayState.PLAY; 
        
        // Create the elements of the Landscape and the game.
        this.scale = 64; // determines the size of the grid
        this.scape = new Landscape(scale*10,scale*7);

        Vertex v1 = new Vertex(0,0);
        Vertex v2 = new Vertex(0,1);
        Vertex v3 = new Vertex(0,2);
        Vertex v4 = new Vertex(1,0);
        Vertex v5 = new Vertex(1,1);
        Vertex v6 = new Vertex(1,2);
        Vertex v7 = new Vertex(2,0);
        Vertex v8 = new Vertex(2,1);
        Vertex v9 = new Vertex(2,2);

        hunter = new Hunter(v1);
        wumpus = new Wumpus(v9);
        graph = new Graph();
        
        
        graph.addEdge(v1, Vertex.Direction.EAST, v2);
        graph.addEdge(v2, Vertex.Direction.EAST, v3);
        graph.addEdge(v2, Vertex.Direction.SOUTH, v5);
        graph.addEdge(v4, Vertex.Direction.NORTH, v1);
        graph.addEdge(v5, Vertex.Direction.EAST, v6);
        graph.addEdge(v5, Vertex.Direction.SOUTH, v8);
        graph.addEdge(v6, Vertex.Direction.SOUTH, v9);
        graph.addEdge(v8, Vertex.Direction.EAST, v9);
        graph.addEdge(v8, Vertex.Direction.WEST, v7);
        graph.shortestPath(v9);

        this.scape.addBackgroundAgent( v1 );
        this.scape.addBackgroundAgent( v2 );
        this.scape.addBackgroundAgent( v3 );
        this.scape.addBackgroundAgent( v4 );   
        this.scape.addBackgroundAgent( v5 );
        this.scape.addBackgroundAgent( v6 );
        this.scape.addBackgroundAgent( v7 );
        this.scape.addBackgroundAgent( v8 );   
        this.scape.addBackgroundAgent( v9 );
        
        this.scape.addBackgroundAgent(wumpus);
        this.scape.addBackgroundAgent(hunter);
        
        this.win = new JFrame("Hunt the Wumpus");
        win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        // make the main drawing canvas (this is the usual
        // LandscapePanel we have been using). and put it in the window
        this.canvas = new LandscapePanel( this.scape.getWidth(), this.scape.getHeight() );
        this.win.add( this.canvas, BorderLayout.CENTER );
        this.win.pack();

        this.announcement = new JLabel("Hunter's state: ");
        JButton quit = new JButton("Quit");
        JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));

        panel.add(this.announcement);
        panel.add( quit );
        this.win.add( panel, BorderLayout.SOUTH);
        this.win.pack();

        Control control = new Control();
        this.win.addKeyListener(control);
        this.win.setFocusable(true);
        this.win.requestFocus();
        quit.addActionListener( control );
        
        this.win.setVisible( true );


    }

    private class LandscapePanel extends JPanel {
		
        /**
         * Creates the drawing canvas
         * @param height the height of the panel in pixels
         * @param width the width of the panel in pixels
         **/
        public LandscapePanel(int height, int width) {
            super();
            this.setPreferredSize( new Dimension( width, height ) );
            this.setBackground(Color.white);
        }

        /**
         * Method overridden from JComponent that is responsible for
         * drawing components on the screen.  The supplied Graphics
         * object is used to draw.
         * 
         * @param g		the Graphics object used for drawing
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            scape.draw( g, scale );
        }
    } // end class LandscapePanel


    private class Control extends KeyAdapter implements ActionListener {

        public void keyTyped(KeyEvent e) {
            //if hunter is eaten or wumpus is dead, return to stop the game
            if (hunter.isEaten() == true) 
                return;
            else if (wumpus.isDead() == true) {
                return;
            }
            System.out.println( "Key Pressed: " + e.getKeyChar() ); 

            if( ("" + e.getKeyChar()).equalsIgnoreCase("q") ) {
                state = PlayState.STOP;
            } 
            //if hunter is not armed, the key typed will determine the moving direction
            if (hunter.armed() == false) {
                
                if (("" + e.getKeyChar()).equalsIgnoreCase("w")) {
                    //call method to move North
                    Vertex move = hunter.getPosition().getNeighbor(Vertex.Direction.NORTH);
                    if (move == wumpus.getWumpusPosition()) {
                        hunter.die();
                        announcement.setText( "Hunter dies - game over" );
                        //hunter die -> wumpus turns visible and wins
                        wumpus.turnVisible();
                    }
                    if (move != null ) {
                        hunter.updatePosition(move);
                    }
                }
                else if (("" + e.getKeyChar()).equalsIgnoreCase("s")) {
                    //call method to move South
                    Vertex move = hunter.getPosition().getNeighbor(Vertex.Direction.SOUTH);
                    if (move == wumpus.getWumpusPosition()) {
                        hunter.die();
                        announcement.setText( "Hunter dies - game over" );
                        wumpus.turnVisible();
                    }
                    else if (move != null ) {
                        hunter.updatePosition(move);
                    } 
                    
                }
                else if (("" + e.getKeyChar()).equalsIgnoreCase("a")) {
                    //call method to move West
                    Vertex move = hunter.getPosition().getNeighbor(Vertex.Direction.WEST);
                    if (move == wumpus.getWumpusPosition()) {
                        hunter.die();
                        announcement.setText( "Hunter dies - game over" );
                        wumpus.turnVisible();
                    }
                    else if (move != null ) {
                        hunter.updatePosition(move);
                    }    
                    
                }        
                else if (("" + e.getKeyChar()).equalsIgnoreCase("d")) {
                    //call method to move East
                    Vertex move = hunter.getPosition().getNeighbor(Vertex.Direction.EAST);
                    if (move == wumpus.getWumpusPosition()) {
                        hunter.die();
                        announcement.setText( "Hunter dies - game over" );
                        wumpus.turnVisible();
                    }    
                    else if (move != null ) {
                        hunter.updatePosition(move);
                    } 
                           
                } 
                else if (("" + e.getKeyChar()).equalsIgnoreCase(" ")) {
                    //call method to arm
                    announcement.setText( "About to shoot" );
                    hunter.getArmed();
                    
                }
            }
            //if the hunter is armed, the key typed will determine the shooting direction
            else {
                
                if (("" + e.getKeyChar()).equalsIgnoreCase("w")) {
                    //call method to shoot North
                    Vertex shoot = hunter.shoot(Vertex.Direction.NORTH);
                    if (shoot == null) {
                        announcement.setText( "No door - cannot shoot in this direction" );

                    } else {
                        if (shoot == wumpus.getWumpusPosition()) {
                            wumpus.killTheWumpus();
                            wumpus.turnVisible();
                            announcement.setText( "Killed it - YOU WIN" );
    
                            //WIN
                        } else {
                            hunter.die();
                            announcement.setText( "Hunter dies - game over" );
                            wumpus.turnVisible();
                            //DIE
                        }    
                    }
                    
                }
                else if (("" + e.getKeyChar()).equalsIgnoreCase("s")) {
                    //call method to shoot South
                    Vertex shoot = hunter.shoot(Vertex.Direction.SOUTH);
                    // hunter.shootShout();
                    if (shoot == null) {
                        announcement.setText( "No door - cannot shoot in this direction" );

                    } else {
                        if (shoot == wumpus.getWumpusPosition()) {
                            wumpus.killTheWumpus();
                            wumpus.turnVisible();
                            announcement.setText( "Killed it - YOU WIN" );
    
                            //WIN
                        } else {
                            hunter.die();
                            announcement.setText( "Hunter dies - game over" );
                            wumpus.turnVisible();
                            //DIE
                        }    
                    }
                }
                else if (("" + e.getKeyChar()).equalsIgnoreCase("a")) {
                    //call method to shoot West
                    Vertex shoot = hunter.shoot(Vertex.Direction.WEST);
                    if (shoot == null) {
                        announcement.setText( "No door - cannot shoot in this direction" );

                    } else {
                        if (shoot == wumpus.getWumpusPosition()) {
                            wumpus.killTheWumpus();
                            wumpus.turnVisible();
                            announcement.setText( "Killed it - YOU WIN" );
    
                            //WIN
                        } else {
                            hunter.die();
                            announcement.setText( "Hunter dies - game over" );
                            wumpus.turnVisible();
                            //DIE
                        }    
                    }
                }        
                else if (("" + e.getKeyChar()).equalsIgnoreCase("d")) {
                    //call method to shoot EAST
                    Vertex shoot = hunter.shoot(Vertex.Direction.EAST);
                    if (shoot == null) {
                        announcement.setText( "No door - cannot shoot in this direction" );

                    } else {
                        if (shoot == wumpus.getWumpusPosition()) {
                            wumpus.killTheWumpus();
                            wumpus.turnVisible();
                            announcement.setText( "Killed it - YOU WIN" );
    
                            //WIN
                        } else {
                            hunter.die();
                            announcement.setText( "Hunter dies - game over" );
                            wumpus.turnVisible();
                            //DIE
                        }    
                    }         
                } 
            }

        }

        public void actionPerformed(ActionEvent event) {
            // If the Quit button was pressed
            if( event.getActionCommand().equalsIgnoreCase("Quit") ) {
		        System.out.println("Quit button clicked");
                state = PlayState.STOP;
            }
        }
    } // end class Control

    public void repaint() {
    	this.win.repaint();
    }

    public void dispose() {
	    this.win.dispose();
    }

    public static void main(String[] argv) throws InterruptedException {
        HuntTheWumpus w = new HuntTheWumpus();
        while (w.state == PlayState.PLAY) {
            w.repaint();
            Thread.sleep(33);
        }
        System.out.println("Disposing window");
        w.dispose();
    }
}