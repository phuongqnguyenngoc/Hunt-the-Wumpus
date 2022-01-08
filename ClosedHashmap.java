import java.util.ArrayList;
import java.lang.Math;

public class ClosedHashmap<K,V> implements MapSet<K,V> {
    private Object[] hmap ;
    private boolean[] occuppied;
    private int numItems;
    private int collision;

    //a constructor initializes the hashmap 
    public ClosedHashmap(int size) {
        this.hmap = new Object[size];
        this.occuppied = new boolean[size];
        this.numItems = 0;
        this.collision = 0;            
    }


    //return number of collisions
    public int getCollision() {return collision;}

    //return number of items
    public int size() {return numItems;}

    //return the index on the array of the given key
    public int getIndex(K key) {
        int numTraversals = 0;
        
        int start = 0;

        //get the index through double hashing
        int index = (hash1(key) + start * hash2(key)) % hmap.length ;
        
        //traverse to find an unoccupied position
        while (numTraversals < hmap.length && occuppied[index])
        {
            start ++;
            
            // get the key-value pair of the indexed position
            KeyValuePair<K,V> kv = (KeyValuePair<K,V>) hmap[index];

            //if the given KeyValuePair and the KeyValuePair of the indexed position are the same, return index
            if (kv != null && key.equals(kv.getKey()))
                return index;
                
            // else, continue traversal, update the variables
            numTraversals++;
            //get the index through double hashing
            index = (hash1(key) + start * hash2(key)) % hmap.length ;
        }
        //if traversal is done, return - 1
        return -1;
    }



    //hash1 method for double hashing
    public int hash1(K key) {
        // System.out.println(hmap.length);
        return Math.abs(key.hashCode()) % hmap.length;

    }

    //hash2 method for double hashing
    public int hash2(K key) {
        //situation 1: the table size is small
        if (hmap.length < 1000) {
            return 29 - (Math.abs(key.hashCode()) % 29);
        }

        //situation 2: the table size is large enough
        else {
            return 977 - (Math.abs(key.hashCode()) % 977);
        }

    }

 
    //doubles the table's size when number of items is larger than half of the table's size
    public  void ensureCapacity() {
        if (numItems < hmap.length/2 )
    	return;
        doubleSize();
       
    }

    //double the table's size
    public void doubleSize () {
        Object[] copy = new Object[hmap.length];
        for (int i = 0; i < copy.length; i++)
        copy[i] = hmap[i];
        
        hmap = new Object[2*copy.length];
        occuppied = new boolean[2*copy.length];
        numItems = 0;
        for (int i = 0; i < copy.length; i++) { 
            KeyValuePair<K,V> currKeyVal = (KeyValuePair<K,V>) copy[i];
        
            if (currKeyVal != null)
                put(currKeyVal.getKey(), currKeyVal.getValue());
        }
    }

    //put the Key into hashmap
    public V put(K key, V value) {

        //get the index 
        int index = getIndex(key);
        //if the key has already existed in the hashmap, update the value
        if (index != -1) {
            KeyValuePair<K,V> kv = (KeyValuePair<K,V>)(hmap[index]);
            V val = kv.getValue();
            kv.setValue(value);
            return val;           
        } //else, find a suitable position using double hasing and put the key into the hashmap
        else {
            ensureCapacity();
            int start = 0;
            int position = (hash1(key) + start*hash2(key)) % hmap.length;
            
            while (hmap[position] != null) {
                if (start == 0) {
                    collision ++;
                } 
                if (start == hmap.length) {
                    doubleSize();
                }
                start ++;
                position = (hash1(key) + start*hash2(key)) % hmap.length;   
            }
            hmap[position] = new KeyValuePair<K,V>(key, value);
            occuppied[position] = true;
            numItems++;
            return null;
        }
    }

    //get the value of the key
    public V get(K key) {
        int index = getIndex(key);
        // check for special case
        if (index < 0)
        return null;
        
        KeyValuePair<K,V> kv = (KeyValuePair<K,V>) hmap[index];
        return kv.getValue();
    }

    //remove the key from the hashmap
    public V remove(K key) {
        int index = getIndex(key);
        // check for special case
        if (index < 0)
        return null;
        
        V val = get(key);

        hmap[index] = null;
        numItems--;
        return val;
    }

    //return true if the key exists in the hashmap
    public boolean containsKey( K key ) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    //return an ArrayList of all the keys
    public ArrayList<K> keySet() {
        ArrayList<K> key = new ArrayList<K>();
        for (int i = 0; i < hmap.length; i ++) {
            if (hmap[i] != null) {
                key.add( ((KeyValuePair<K,V>) (hmap[i])).getKey()); 
            }
        }
        return key;
    }

    //return an ArrayList of all the values
    public ArrayList<V> values() {
        ArrayList<V> val = new ArrayList<V>();
        for (int i = 0; i < hmap.length; i ++) {
            if (hmap[i] != null) {
                val.add( ((KeyValuePair<K,V>) (hmap[i])).getValue()); 
            }
        }
        return val;
    }

    //return an ArrayList of all the KeyValuePair
    public ArrayList<KeyValuePair<K,V>> entrySet() {
        ArrayList<KeyValuePair<K,V>> kv = new ArrayList<KeyValuePair<K,V>>();
        for (int i = 0; i < hmap.length; i ++) {
            if (hmap[i] != null) {
                kv.add( (KeyValuePair<K,V>) (hmap[i])); 
            }
        }
        return kv;
    }

    //clear the hashmap
    public void clear() {
        Object[] copy = new Object[hmap.length];
        this.hmap = copy;
        this.occuppied = new boolean[hmap.length];
        this.numItems = 0;
        this.collision = 0; 
    }

    public String toString() {
        String map = "";
        for (int i = 0; i < hmap.length; i ++) {
            KeyValuePair<K,V> kv = (KeyValuePair<K,V>)hmap[i];
            if (kv != null) {
                map += kv.getKey() + "-" + kv.getValue() + "  ";
            }
        }
        return map;

    }





    public static void main (String[] args) {
        ClosedHashmap<String, Integer> test = new ClosedHashmap<String,Integer>(37);
        System.out.println(test.put("Ant", 1));
        // System.out.println(test.get("Ant"));
        test.put("Bear", 2);
        test.put("Cat", 1);
        test.put("Dog", 2);
        test.put("Eagle", 1);
        test.put("Fly", 2);

        // System.out.println(test.containsKey("Dog"));
        // System.out.println(test.containsKey("Turtle"));

        System.out.println(test.get("Dog"));

        
        System.out.println(test.keySet());
       

        System.out.println(test.getCollision());

        test.put("Duck", 1);
        test.put("Chicken", 2);
        test.put("Dolphin", 1);
        test.put("Hedgehog", 2);
        test.put("Whale", 1);
        // System.out.println(test.hashFunction("Bird"));
        test.put("Bird", 2);

        // System.out.println(test.entrySet());
        // System.out.println(test);
        System.out.println(test.keySet());
        System.out.println(test.values());
        System.out.println(test.entrySet());
        System.out.println(test);
    }


}