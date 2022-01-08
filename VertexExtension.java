import java.awt.Graphics;
import java.awt.Color;



public class VertexExtension extends Agent {
    private ClosedHashmap<Direction, Vertex> adjacent;
    private boolean visible;
    private int cost;
    private boolean marked;
   
    public enum Direction {NORTH, SOUTH, EAST, WEST}

    public VertexExtension(int row, int col) {
        super(row, col);
        this.adjacent = new ClosedHashmap<Direction, Vertex>(4);
        this.visible = false;
        this.cost = 0;
        this.marked = false;
        

        
    }

    // public int getRow() {
    //     return this.row;
    // }

    // public int getCol() {
    //     return this.column;
    // }

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

    
    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean getMarked() {
        return marked;
    }

    public void setMarked( boolean marked) {
        this.marked = marked;
    }

    public void connect(Vertex other, Direction dir) {
        // KeyValuePair<Direction,Vertex> added = new KeyValuePair<Direction,Vertex>(dir, other);
        if (adjacent.get(dir) == null) {
            adjacent.put(dir, other);
        }
    }

    public Vertex getNeighbor(Direction dir) {
        return adjacent.get(dir);
    }

    public ClosedHashmap<Direction,Vertex> getNeighbors() {
        return this.adjacent;
    }

    public String toString() {
        String vertex =  "\n" + "Cost " + getCost() + "\n";
        vertex += "Neighbors: " + adjacent.size() + "\n";
        vertex += "Mark: " + getMarked();
        return vertex;
    }

    public void draw(Graphics g, int scale) {
        if (!this.visible) {
            return;
        }
        int xpos = this.getCol()*scale;
        int ypos = (int)this.getRow()*scale;
        int border = 2;
        int half = scale / 2;
        int eighth = scale / 8;
        int sixteenth = scale / 16;
        
        // draw rectangle for the walls of the room
        if (this.cost < 2)
            // wumpus is nearby
            g.setColor(Color.red);
        else
            // wumpus is not nearby
            g.setColor(Color.black);
        g.drawRect(xpos + border, ypos + border, scale - 2*border, scale - 2 * border);
        // draw doorways as boxes
        g.setColor(Color.black);
        if (this.adjacent.containsKey(Direction.NORTH))
            g.fillRect(xpos + half - sixteenth, ypos, eighth, eighth + sixteenth);
        if (this.adjacent.containsKey(Direction.SOUTH))
            g.fillRect(xpos + half - sixteenth, ypos + scale - (eighth + sixteenth), 
                       eighth, eighth + sixteenth);
        if (this.adjacent.containsKey(Direction.WEST))
            g.fillRect(xpos, ypos + half - sixteenth, eighth + sixteenth, eighth);
        if (this.adjacent.containsKey(Direction.EAST))
            g.fillRect(xpos + scale - (eighth + sixteenth), ypos + half - sixteenth, 
                       eighth + sixteenth, eighth);
    }



    // public static void main(String[] args) {
    //     Vertex test1 = new Vertex();
    //     Vertex test2 = new Vertex();

    //     test1.setVisible(true);
    //     test1.setMarked(true);
    //     System.out.println(test1.opposite(Direction.South));
    //     System.out.println(test1.opposite(Direction.North));
    //     System.out.println(test1.opposite(Direction.West));
    //     System.out.println(test1.opposite(Direction.East));

    //     test1.connect(new Vertex(), Direction.South);
    //     test1.connect(new Vertex(), Direction.West);
    //     test1.setCost(3);
    //     test2.setCost(6);
        

        

    //     test1.connect(test2, Direction.East);
    //     System.out.println(test1);
    //     System.out.println(test1.getNeighbors());

    // }




}