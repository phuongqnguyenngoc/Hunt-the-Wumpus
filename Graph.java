/** 
 * Graph.java
 * Phuong Nguyen Ngoc 
 * CS231
* Project: Hunt the Wumpus
*/

import java.util.ArrayList;
import java.util.Random;

public class Graph {
    private ArrayList<Vertex> vertices;
    private Vertex[][] arrayOfVertices;

    //constructor initializes the arrayList and the 2D array that stores the Vertex
    public Graph() {
        this.vertices = new ArrayList<Vertex>();
        arrayOfVertices = new Vertex[4][4];
    }

    //return the number of Vertex in the graph
    public int vertexCount() {
        return this.vertices.size();
    }

    //connect 2 vertex objecects
    public void addEdge(Vertex v1, Vertex.Direction dir, Vertex v2) {
        if (!vertices.contains(v1)) {
            vertices.add(v1);
        } if (!vertices.contains(v2)) {
            vertices.add(v2); 
        } 

        //store Vertex in 2d array to randomize graph later
        if (arrayOfVertices[v1.getRow()][v1.getCol()] == null) {
            arrayOfVertices[v1.getRow()][v1.getCol()] = v1;
        }
        if (arrayOfVertices[v2.getRow()][v2.getCol()] == null) {
            arrayOfVertices[v2.getRow()][v2.getCol()] = v2;
        }
        
        v1.connect(v2, dir);
        v2.connect(v1, v1.opposite(dir));
    }

    //use Dijkstra's algorithm to find the shortest path
    public void shortestPath(Vertex v0) {
        PQHeap<Vertex> q = new PQHeap<Vertex>(new CompareCost());
        for (Vertex v: this.vertices) {
            v.setCost(1000);
            v.setMarked(false);
        }
        v0.setCost(0);
        q.add(v0);
        while (q.size() > 0) {
            Vertex v = q.remove();
            v.setMarked(true);
            ArrayList<Vertex> neighbors = v.getNeighbors().values();
            for (Vertex w: neighbors) {
                if (w.getMarked() == false && v.getCost() + 1 < w.getCost()) {
                    w.setCost(v.getCost() + 1);
                    //remove the Vertex then add it to the heap to make sure it is in the right position
                    q.remove(w);
                    q.add(w);
                }
            }
        }
        
    }

    //add teleport between 2 vertex
    public void addTeleport( Vertex v1, Vertex v2) {
        v1.setTeleport(v2);
        v2.setTeleport(v1);   
    }

    //check whether corgi is standing in the room with the teleport or not
    //if it is, teleport it to the Vertex of the other end 
    public void checkTeleport(Corgi corgi) {
        if (corgi.getPosition().getTeleport() != null) {
            if (corgi.getTeleport() == false) {
                corgi.setTeleport(true);
                corgi.updatePosition(corgi.getPosition().getTeleport()); 
                }
            }    
    }

    //reset the graph by making every vertex invisible
    public void reset() {
        for (Vertex v: vertices) {
            v.setVisible(false);
        }
    }

    //generate a randomly connected graph
    public void randomGraph() {
        reset();
        Random rand = new Random();
        for (Vertex v: vertices) {
            v.disconnect();
        }
        for (Vertex v: vertices) {
            int numOfConnection = rand.nextInt(3) + 1;
            ArrayList<Integer> connections = new ArrayList<Integer>();
            for (int i = 0; i < 4; i ++) {
                connections.add(i);
            }
            for (int i = 0; i < numOfConnection; i ++) {               
                if (connections.size() == 0) {
                    break;
                }
                int getDirection = rand.nextInt(connections.size()); 
                int direction = connections.get(getDirection) ;
                System.out.println("direction is " + direction);
                if (direction == 0) {
                    //north
                    if (v.getRow() > 0 ) { //check boundary
                        Vertex toConnect = arrayOfVertices[v.getRow() - 1][v.getCol()];
                        System.out.println("v2 position " + toConnect.getRow() + " " + toConnect.getCol());
                        addEdge(v, Vertex.Direction.NORTH, toConnect);                       
                    }    
                    else {
                        i --;
                    }                    
                } else if (direction == 1) {
                    //south
                    if (v.getRow() < 3 ) { //check boundary
                        Vertex toConnect = arrayOfVertices[v.getRow() + 1][v.getCol()];
                        System.out.println("v2 position " + toConnect.getRow() + " " + toConnect.getCol());
                        addEdge(v, Vertex.Direction.SOUTH, toConnect);                        
                    }    
                    else {
                        i --;
                    }                    
                } else if (direction == 2) {
                    //east
                    if (v.getCol() < 3 ) { //check boundary
                        Vertex toConnect = arrayOfVertices[v.getRow()][v.getCol() + 1];
                        System.out.println("v2 position " + toConnect.getRow() + " " + toConnect.getCol());
                        addEdge(v, Vertex.Direction.EAST, toConnect);                        
                    }    
                    else {
                        i --;
                    }
                } else if (direction == 3) {
                    //west
                    if (v.getCol() > 0 ) { //check boundary
                        Vertex toConnect = arrayOfVertices[v.getRow()][v.getCol() - 1];
                        System.out.println("v2 position " + toConnect.getRow() + " " + toConnect.getCol());
                        addEdge(v,Vertex.Direction.WEST, toConnect);
                    }    
                    else {
                        i --;
                    }
                }
                //remove the direction so that it is not considered twice
                connections.remove(getDirection);                
            }
        }
    }


    //randomly reset game so that it has a random graph and random positions for Corgi and Husky
    public void randomlyResetGame(Corgi corgi, Husky husky) {
        randomGraph();
        Random rand = new Random();
        int corgiRow = rand.nextInt(4);
        int corgiCol = rand.nextInt(4);
        corgi.reset(arrayOfVertices[corgiRow][corgiCol]);
        int huskyRow = rand.nextInt(4);
        int huskyCol = rand.nextInt(4);
        husky.reset(arrayOfVertices[huskyRow][huskyCol]);
        shortestPath(husky.getPosition());
        while (corgi.getPosition().getCost() < 3) {
            corgiRow = rand.nextInt(4);
            corgiCol = rand.nextInt(4);
            corgi.reset(arrayOfVertices[corgiRow][corgiCol]);
        }
        //if corgi and husky is not connected, call the method again
        while (corgi.getPosition().getCost() == 1000) {
            randomlyResetGame(corgi, husky);
        }
        //set every vertex to invisible because it might be visible from previous call of randomlyResetGame
        reset();
        //set the initial position of Corgi in the final graph to visible
        corgi.getPosition().setVisible(true);
    }

    public static void main (String[] args) {
        Graph test = new Graph();
        Vertex v0 = new Vertex(0,0);
        Vertex v1 = new Vertex(0,1);
        Vertex v2 = new Vertex(0,2);
        Vertex v3 = new Vertex(1,0);
        Vertex v4 = new Vertex(1,1);
        Vertex v5 = new Vertex(1,2);
        Vertex v6 = new Vertex(2,0);
        Vertex v7 = new Vertex(2,1);
        Vertex v8 = new Vertex(2,2);

        test.addEdge(v1, Vertex.Direction.EAST, v2);
        test.addEdge(v2, Vertex.Direction.EAST, v3);
        test.addEdge(v3, Vertex.Direction.SOUTH, v4);
        test.addEdge(v4, Vertex.Direction.WEST, v0);
        test.addEdge(v0, Vertex.Direction.SOUTH, v7);
        test.addEdge(v7, Vertex.Direction.EAST, v8);
        test.addEdge(v7, Vertex.Direction.WEST, v6);
        test.addEdge(v6, Vertex.Direction.NORTH, v5);

        test.shortestPath(v0);
        System.out.println("v1 " + v1.getCost());
        System.out.println("v2 " + v2.getCost());
        System.out.println("v3 " + v3.getCost());
        System.out.println("v4 " + v4.getCost());
        System.out.println("v5 " + v5.getCost());
        System.out.println("v6 " + v6.getCost());
        System.out.println("v7 " + v7.getCost());
        System.out.println("v8 " + v8.getCost());


    }


}