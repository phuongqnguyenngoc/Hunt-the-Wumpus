/** 
 * PQHeap.java
 * Phuong Nguyen Ngoc 
 * CS231
* Project: Hunt the Wumpus
*/

import java.util.Comparator;
import java.lang.Math;

public class PQHeap<T> {
    private Object[] heap;
    private int numItems;
    private Comparator<T> comp;

    //constructor
    public PQHeap(Comparator<T> comp) {
        this.heap = new Object[32];
        this.numItems = 0;
        this.comp = comp;
    }

    //return number of items in the heap
    public int size() {
        return numItems;
    }

    //add items to the heap
    public void add(T obj) {
        ensureCapacity();

        //add the KeyValuePair to the next free space in the array
        heap[numItems] = obj;
        numItems++;
        //percolate up to find the suitable position to ensure Heap structure
        percolateUp();
    }

    //compare item to its parent and restructure the array
    private void percolateUp() {
        //the index for the last element in the array
        int outOfPlaceInd = numItems - 1;
    
        // swap with parents until we reach the root or the parent is larger than the current value.
        while (outOfPlaceInd > 0) {
            int parentInd = (outOfPlaceInd - 1)/2;
            int compare = this.comp.compare((T)(heap[outOfPlaceInd]),((T) (heap[parentInd])));
          //if the current value is larger than its parent, swap
            if (compare < 0) {
            swap(outOfPlaceInd, parentInd);
            // update the curr index to the parent's
            outOfPlaceInd = parentInd;
            }
          //else, the current element is in the right position, return
        else {
            return;
        }
      }
    }

    //remove the first element from the heap
    public T remove() {
      if (numItems == 0) {
          // throw new IllegalStateException("Trying to remove from an empty PQ!");
          return null;
      }
        
      T p = (T)(heap[0]);
        // replace the root with the last element on the array
        numItems--;
        heap[0] = heap[numItems];
        
        //percolate down to ensure the Heap structure
        percolateDown();
        
        return p;
  }

  //compare item to its children and restructure the array
  private void percolateDown() {    
      int outOfPlaceInd = 0; // parent
      int leftInd = 2*outOfPlaceInd + 1; // left child
      int rightInd = 2*outOfPlaceInd + 2; // right child

      //swap until the outOfPlaceInd value is larger than its larger child's value
      while (leftInd <= numItems - 1) {
        int smallerChildInd = leftInd;
        // if ((rightInd < numItems) && (((KeyValuePair<K,V>)(heap[rightInd])).compareTo((KeyValuePair<K,V>)(heap[leftInd])) > 0)) {
        if ((rightInd < numItems) && (this.comp.compare((T)(heap[rightInd]), (T)(heap[leftInd])) < 0)) {
          smallerChildInd = rightInd;
        }
        int compare = this.comp.compare((T)(heap[outOfPlaceInd]), (T)(heap[smallerChildInd]));
        //if the current value is smaller than its larger child's value, swap and update the indices
        if (compare > 0)
        {
          swap(outOfPlaceInd, smallerChildInd);

          outOfPlaceInd = smallerChildInd;
          leftInd = 2*outOfPlaceInd + 1;
          rightInd = 2*outOfPlaceInd + 2;
        }
        //else, the current element is in the right place
        else
        {
          return;
        }
      }
    }

    //percolate up from the object at the indexed position
    public int percolateUp(int index) {
      int set = index; 
      while (set > 0) {
        // System.out.println("index " + index);
        int parentInd = (set - 1)/2;
        // System.out.println("set: " + heap[set]);
        // System.out.println("parent: " + heap[parentInd]);
        int compare = this.comp.compare((T)(heap[set]),((T) (heap[parentInd])));
        if (compare < 0) {
          swap(set, parentInd);
          set = parentInd;
        }
        else {
          break;
          
        }
      }
      // int set = index;
      // return set;
     
      return set;
    }

    //swap 2 elements's position with each other
    public void swap(int i, int j) {
        T copy = (T)(heap[i]);
        heap[i] = heap[j];
        heap[j] = copy;
    }

    //return the index of the specific element in the heap
    public int findIndex(T thing) {
      for (int i = 0; i < numItems; i ++) {
        if (((T)(heap[i])) == thing) {
          return i;
        }
      }
      return -1;
    }
  
    //remove specific element from the heap
    public T remove(T thing) {
        if (numItems == 0) {
            // throw new IllegalStateException("Trying to remove from an empty PQ!");
            return null;
        }
        int index = findIndex(thing);
        if (index == -1) {
          return null;
        }
          
        T p = (T)(heap[index]);
          // replace the root with the last element on the array
          numItems--;
          heap[index] = heap[numItems];
          // System.out.println("Supposed to be Jone " + heap[numItems]);
          
          int doneUp = percolateUp(index);
          if (doneUp == index) {
            percolateDown(doneUp);
          }
          return p;
    }

    //percolate down from the object at the indexed position
    public void percolateDown(int index) {
      int leftInd = 2*index + 1; // left child
      int rightInd = 2*index + 2; // right child

      //swap until the outOfPlaceInd value is larger than its larger child's value
      while (leftInd <= numItems - 1) {
        int smallerChildInd = leftInd;
        // if ((rightInd < numItems) && (((KeyValuePair<K,V>)(heap[rightInd])).compareTo((KeyValuePair<K,V>)(heap[leftInd])) > 0)) {
        if ((rightInd < numItems) && (this.comp.compare((T)(heap[rightInd]), (T)(heap[leftInd])) < 0)) {
          smallerChildInd = rightInd;
        }
        int compare = this.comp.compare((T)(heap[index]), (T)(heap[smallerChildInd]));
        //if the current value is smaller than its larger child's value, swap and update the indices
        if (compare > 0)
        {
          swap(index, smallerChildInd);

          index = smallerChildInd;
          leftInd = 2*index + 1;
          rightInd = 2*index + 2;
        }
        //else, the current element is in the right place
        else
        {
          return;
        }
      }
    }

    

    //double array's size when necessary
    public void ensureCapacity() {
        if (numItems < heap.length/2) {
          return;
        }
        Object[] newHeap = new Object[2*heap.length];
        for (int i = 0; i < heap.length; i++) {
          newHeap[i] = heap[i];
        }   
        heap = newHeap;
    
      }

      //toString method prints out heap layer by layer
      public String toString() {
        String returnStr = "";
    
        int level = 0;
        int leftn = numItems;
        
        while (leftn > 0) {
          int count = 1;
          double pow = Math.pow(2.0, level);
          while (count <= pow )  {
            if (leftn == 0) 
              break;
            returnStr += heap[numItems-leftn] + " ";
            count++;
            leftn--;
          }
    
          if (leftn != 0) 
          returnStr += "\n";
          level++;
        }
        returnStr += "\n----------------\n";
    
        return returnStr;
      }

      public static void main(String[] args) {
        PQHeap<KeyValuePair<String,Integer>> pq = new PQHeap<KeyValuePair<String,Integer>>(new CompareValue());
        KeyValuePair<String,Integer> a = new KeyValuePair<String,Integer>("Scott", 6);
        pq.add(a);
        KeyValuePair<String,Integer> b = new KeyValuePair<String,Integer>("Bob", 1);
        pq.add(b);
        KeyValuePair<String,Integer> c = new KeyValuePair<String,Integer>("Jone", 3);
        pq.add(c);
        KeyValuePair<String,Integer> d = new KeyValuePair<String,Integer>("Susan", 4);
        pq.add(d);
        KeyValuePair<String,Integer> e = new KeyValuePair<String,Integer>("Nate", 7);
        pq.add(e);
        KeyValuePair<String,Integer> f = new KeyValuePair<String,Integer>("Eddy", 2);
        pq.add(f);
       
        System.out.println(pq);

        System.out.println("Removing Susan: " + pq.remove(d));
        System.out.println(pq);
        System.out.println("Removing Scot: " + pq.remove(a));
        System.out.println(pq);
        System.out.println("Removing Bob: " + pq.remove());
        System.out.println(pq);
      }
}