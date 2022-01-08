/** 
 * CompareCost.java
 * Phuong Nguyen Ngoc 
 * CS231
* Project: Hunt the Wumpus
*/

import java.util.Comparator;

public class CompareCost implements Comparator<Vertex> {
    //compare the Vertex a's cost to Vertex b's cost
    public int compare(Vertex a, Vertex b) {
        int aCost = a.getCost();
        int bCost = b.getCost();
        int c = aCost - bCost;
        if (c > 0) {
            return 1;
        } else if (c < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Vertex v = new Vertex(1,1);
        v.setCost(10);
        Vertex u = new Vertex(1,2);
        u.setCost(20);
        CompareCost cc = new CompareCost();
        System.out.println(cc.compare(v,u));
    }
}