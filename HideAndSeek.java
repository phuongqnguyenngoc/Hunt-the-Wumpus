/** 
 * HideAndSeek.java
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
import java.util.Random;

public class HideAndSeek {
    private JFrame win;
    private LandscapePanel canvas;    
    private int scale;
    JLabel announcement;

    // controls whether the game is playing or exiting
    private enum PlayState { PLAY, STOP }
    private PlayState state;


    private Graph graph;
    private Corgi corgi;
    private Husky husky;
    private Landscape scape;



    public HideAndSeek() {
        // The game should begin in the play state.
        this.state = PlayState.PLAY; 
        
        // Create the elements of the Landscape and the game.
        this.scale = 128; // determines the size of the grid
        this.scape = new Landscape(scale*10,scale*5);

        Vertex v1 = new Vertex(0,0);
        Vertex v2 = new Vertex(0,1);
        Vertex v3 = new Vertex(0,2);
        Vertex v4 = new Vertex(0,3);
        Vertex v5 = new Vertex(1,0);
        Vertex v6 = new Vertex(1,1);
        Vertex v7 = new Vertex(1,2);
        Vertex v8 = new Vertex(1,3);
        Vertex v9 = new Vertex(2,0);
        Vertex v10 = new Vertex(2,1);
        Vertex v11 = new Vertex(2,2);
        Vertex v12 = new Vertex(2,3);
        Vertex v13 = new Vertex(3,0);
        Vertex v14 = new Vertex(3,1);
        Vertex v15 = new Vertex(3,2);
        Vertex v16 = new Vertex(3,3);

        corgi = new Corgi(v1);
        husky = new Husky(v11);
        graph = new Graph();
        
        
        graph.addEdge(v1, Vertex.Direction.EAST, v2);
        graph.addEdge(v2, Vertex.Direction.EAST, v3);
        graph.addEdge(v3, Vertex.Direction.EAST, v4);
        graph.addEdge(v2, Vertex.Direction.SOUTH, v6);
        graph.addEdge(v4, Vertex.Direction.SOUTH, v8);
        graph.addEdge(v7, Vertex.Direction.EAST, v8);
        graph.addEdge(v7, Vertex.Direction.SOUTH, v11);
        graph.addEdge(v8, Vertex.Direction.SOUTH, v12);
        graph.addEdge(v12, Vertex.Direction.SOUTH, v16);
        graph.addEdge(v16, Vertex.Direction.WEST, v15);
        graph.addEdge(v6, Vertex.Direction.EAST, v7);
        graph.addEdge(v15, Vertex.Direction.NORTH, v11);
        graph.addEdge(v11, Vertex.Direction.WEST, v10);
        graph.addEdge(v10, Vertex.Direction.WEST, v9);
        graph.addEdge(v10, Vertex.Direction.SOUTH, v14);
        graph.addEdge(v10, Vertex.Direction.NORTH, v6);
        graph.addEdge(v14, Vertex.Direction.WEST, v13);
        graph.addEdge(v15, Vertex.Direction.WEST, v14);
        graph.addEdge(v9, Vertex.Direction.NORTH, v5);

        graph.shortestPath(v11);

        this.scape.addBackgroundAgent( v1 );
        this.scape.addBackgroundAgent( v2 );
        this.scape.addBackgroundAgent( v3 );
        this.scape.addBackgroundAgent( v4 );   
        this.scape.addBackgroundAgent( v5 );
        this.scape.addBackgroundAgent( v6 );
        this.scape.addBackgroundAgent( v7 );
        this.scape.addBackgroundAgent( v8 );   
        this.scape.addBackgroundAgent( v9 );
        this.scape.addBackgroundAgent( v10 );
        this.scape.addBackgroundAgent( v11 );
        this.scape.addBackgroundAgent( v12 );
        this.scape.addBackgroundAgent( v13 );
        this.scape.addBackgroundAgent( v14 );   
        this.scape.addBackgroundAgent( v15 );
        this.scape.addBackgroundAgent( v16 );

        
        this.scape.addBackgroundAgent(husky);
        this.scape.addBackgroundAgent(corgi);
        
        this.win = new JFrame("Hide and seek");
        win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);

        // make the main drawing canvas (this is the usual
        // LandscapePanel we have been using). and put it in the window
        this.canvas = new LandscapePanel( this.scape.getWidth(), this.scape.getHeight() );
        this.win.add( this.canvas, BorderLayout.CENTER );
        this.win.pack();

        this.announcement = new JLabel("Corgi's state: ");
        JButton quit = new JButton("Quit");
        JButton reset = new JButton("New game");
        JPanel panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));

        panel.add(this.announcement);
        panel.add( quit );
        panel.add( reset);
        this.win.add( panel, BorderLayout.SOUTH);
        this.win.pack();

        Control control = new Control();
        this.win.addKeyListener(control);
        this.win.setFocusable(true);
        this.win.requestFocus();
        quit.addActionListener( control );
        reset.addActionListener(control);
        
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
            System.out.println( "Key Pressed: " + e.getKeyChar() ); 

            if( ("" + e.getKeyChar()).equalsIgnoreCase("q") ) {
                state = PlayState.STOP;
            } 
            //if the corgi is not armed and not busted, check whether it can move to the neighboring room of the direction given by key typed
            //then check whether it goes to Husky's position of not
            //if it does, player loses
            //otherwise, update the position and keep playing           
            if (corgi.armed() == false && corgi.isBusted() == false) {
                if (("" + e.getKeyChar()).equalsIgnoreCase("w")) {
                    //corgi moves North
                    Vertex move = corgi.getPosition().getNeighbor(Vertex.Direction.NORTH);
                    if (move == husky.getPosition()) {
                        corgi.busted();
                        announcement.setText( "Husky gotcha - Gameover" );
                        husky.turnVisible();
                    }
                    else if (move != null ) {
                        corgi.updatePosition(move);
                    }
                }
                else if (("" + e.getKeyChar()).equalsIgnoreCase("s")) {
                    //corgi moves South
                    Vertex move = corgi.getPosition().getNeighbor(Vertex.Direction.SOUTH);
                    if (move == husky.getPosition()) {
                        corgi.busted();
                        announcement.setText( "Husky gotcha - Gameover" );
                        husky.turnVisible();
                    }
                    else if (move != null ) {
                        corgi.updatePosition(move);
                    } 
                    
                }
                else if (("" + e.getKeyChar()).equalsIgnoreCase("a")) {
                    //corgi moves West
                    Vertex move = corgi.getPosition().getNeighbor(Vertex.Direction.WEST);
                    if (move == husky.getPosition()) {
                        corgi.busted();
                        announcement.setText( "Husky gotcha - Gameover" );
                        husky.turnVisible();
                    }
                    else if (move != null ) {
                        corgi.updatePosition(move);
                    }    
                    
                }        
                else if (("" + e.getKeyChar()).equalsIgnoreCase("d")) {
                    //corgi moves East
                    Vertex move = corgi.getPosition().getNeighbor(Vertex.Direction.EAST);
                    if (move == husky.getPosition()) {
                        corgi.busted();
                        announcement.setText( "Husky gotcha - Gameover" );
                        husky.turnVisible();
                    }    
                    else if (move != null ) {
                        corgi.updatePosition(move);
                    } 
                           
                } 
                else if (("" + e.getKeyChar()).equalsIgnoreCase(" ")) {
                    //corgi gets armed
                    announcement.setText( "About to shoot" );
                    corgi.setArmed(true);
                    
                }
                System.out.println("Corgi is amred? " + corgi.armed());
            }
            //if the corgi is armed and the game is not won or lost
            else if (corgi.armed() == true && corgi.isBusted() == false && corgi.getWin() == false) {
                //for each key pressed, check whether corgi can shoot in that direction
                //if it can, check whether it hits the target or not
                if (("" + e.getKeyChar()).equalsIgnoreCase("w")) {
                    //call method to shoot North
                    Vertex shoot = corgi.shoot(Vertex.Direction.NORTH);
                    if (shoot == null) {
                        announcement.setText( "No door - Cannot shoot in this direction" );
                        corgi.setArmed(false);
                    } else {
                        corgi.setDir(1);
                        if (shoot == husky.getPosition()) {
                            husky.busted();
                            husky.turnVisible();
                            announcement.setText( "Found Husky - YOU WIN" );
                            corgi.setWin(true);
                            //WIN
                        } else {
                            corgi.busted();
                            announcement.setText( "Husky gotcha - Gameover" );
                            husky.turnVisible();
                        }   
                    }    
                }
                else if (("" + e.getKeyChar()).equalsIgnoreCase("s")) {
                    //call method to shoot South
                    Vertex shoot = corgi.shoot(Vertex.Direction.SOUTH);

                    if (shoot == null) {
                        announcement.setText( "No door - Cannot shoot in this direction" );
                        corgi.setArmed(false);
                    } else {
                        corgi.setDir(2);
                        if (shoot == husky.getPosition()) {
                            husky.busted();
                            husky.turnVisible();
                            announcement.setText( "Found Husky - YOU WIN" );
                            corgi.setWin(true);
                            //WIN
                        } else {
                            corgi.busted();
                            announcement.setText( "Husky gotcha - Gameover" );
                            husky.turnVisible();
                        }   
                    }  
                }
                else if (("" + e.getKeyChar()).equalsIgnoreCase("a")) {
                    //call method to shoot West
                    Vertex shoot = corgi.shoot(Vertex.Direction.WEST);                    
                    if (shoot == null) {
                        announcement.setText( "No door - Cannot shoot in this direction" );
                        corgi.setArmed(false);
                    } else {
                        corgi.setDir(3);
                        if (shoot == husky.getPosition()) {
                            husky.busted();
                            husky.turnVisible();
                            announcement.setText( "Found Husky - YOU WIN" );
                            corgi.setWin(true);
                            //WIN
                        } else {
                            corgi.busted();
                            announcement.setText( "Husky gotcha - Gameover" );
                            husky.turnVisible();
                        }   
                    }  
                }        
                else if (("" + e.getKeyChar()).equalsIgnoreCase("d")) {
                    //call method to shoot EAST
                    Vertex shoot = corgi.shoot(Vertex.Direction.EAST);
                    corgi.setDir(4);
                    if (shoot == null) {
                        announcement.setText( "No door - Cannot shoot in this direction" );
                        corgi.setArmed(false);
                    } else {
                        if (shoot == husky.getPosition()) {
                            husky.busted();
                            husky.turnVisible();
                            announcement.setText( "Found Husky - YOU WIN" );
                            corgi.setWin(true);
                            //WIN
                        } else {
                            corgi.busted();
                            announcement.setText( "Husky gotcha - Gameover" );
                            husky.turnVisible();
                        }   
                    }
                             
                } 
            }

        }

        public void actionPerformed(ActionEvent event) {
            // If the Quit button was pressed -> change the game state into stop
            if( event.getActionCommand().equalsIgnoreCase("Quit") ) {
		        System.out.println("Quit button clicked");
                state = PlayState.STOP;
            } 
            //if the New game button was pressed, reset a new random map 
            if (event.getActionCommand().equalsIgnoreCase("New game")) {
                System.out.println("New game button clicked");
                graph.randomlyResetGame(corgi, husky);
                win.requestFocus();
                announcement.setText("");


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
        HideAndSeek w = new HideAndSeek();
        while (w.state == PlayState.PLAY) {
            w.repaint();
            Thread.sleep(33);
        }
        System.out.println("Disposing window");
        w.dispose();
    }
}