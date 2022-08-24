
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;



public class LinearProbingHash<Key> {

    double loadLimit;
    int[] collision = new int[3000];
    Key[] table;
    int M;
    int N; // number of full elements
    boolean[] full;
    GraphMatrix g;
    ListGraph lg;

    public LinearProbingHash(int M, double loadLimit) {
        table = (Key[]) new Object[M];
        full = new boolean[M];
        this.N = 0;
        this.M = M;
        this.loadLimit = loadLimit;
    }

    public int hash(Key t) {
        return ((t.hashCode() & 0x7fffffff) % M);

    }

    public boolean insert(Key key) {
        
        if (key == null) {
           
            throw new IllegalArgumentException("first argument to put() is null");
            
        }
       
        if (N >= M / 2) {
            
            resize(2 * M);
            
        }
        
        if (contains(key)) {
            
            collision[hash(key)]++;
            
        }
        
        int i;
        
        int h = hash(key);
        
      //  System.out.println("hash(" + key + ")= " + h);
        
        for (i = h; table[i] != null; i = (i + 1) % M) {
            
            
        }
        table[i] = key;
        N++; // increase number of stored items
        return true;
    }
    
  
    // resizes the hash table to the given capacity by re-hashing all of the keys
    private void resize(int capacity) {
       //System.out.println("resize");
        LinearProbingHash<Key> temp = new LinearProbingHash<Key>(capacity, loadLimit);
        for (int i = 0; i < M; i++) {
            if (table[i] != null) {
                temp.insert(table[i]);
            }
        }
        table = temp.table;
        full = temp.full;
        M = temp.M;
       // System.out.println("New size is : "+M);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (!contains(key)) {
            return;
        }

        // find position i of key
        int i = hash(key);
        while (!key.equals(table[i])) {
            i = (i + 1) % M;
        }

        // delete key and associated value
        table[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % M;
        while (table[i] != null) { //maybe there was a collision so we need to rehash
            // delete keys[i] an vals[i] and reinsert
            Key keyToRehash = table[i];
            table[i] = null;
            N--;
            insert(keyToRehash);
            i = (i + 1) % M;
        }
        N--;
        // halves size of array if it's 12.5% full or less
        if (N > 0 && N <= M / 8) {
            resize(M / 2);
        }
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    int hashFunction (LinkedList l) {
        
        Node temp = l.first;
        
        int sum = 0;
        
        while (temp!=null){
            
        sum = sum + temp.data.hashCode();
            
        temp = temp.next;
        
        
        }
        
        return sum;
        
    }
    
 void edgeBuilder(GraphMatrix g) throws FileNotFoundException, IOException{
     
     FileOutputStream fos = new FileOutputStream("Connections.txt");
     
      FileOutputStream fos2 = new FileOutputStream("NumericConnections.txt");
      
      FileOutputStream fos3 = new FileOutputStream("CharactersWithHashs.txt");
     
     int count = 0;
     
     
     lg= new ListGraph(240);
     
     int indexes [] = new int [100];
     
     String nameindexes [] = new String [100];
     
     int count2 = 0;
     
  for (int i = 0; i < M; i++) {
         count++;
               if (table[i]!=null) {
                   
                  indexes[count2]= (count-1);
                  
                  nameindexes[count2]= (String)table[i];
                  
                  byte [] fosbyte = (nameindexes[count2]+" "+indexes[count2]+"\n").getBytes();
                  
                   fos3.write(fosbyte);

                  count2++;
                   
               }
            
        }
  
  int counter = 240;
  
  boolean isEmpty = true;
  
  int index = 0;
  
  while (isEmpty) {
    
      int from = (int) (Math.random()*(100));
  
  int to = (int) (Math.random()*(100));
  
  int weight = (int) (Math.random()*10);
  
  while ((from == to)|| (weight == 0)) {
      
       from = (int) (Math.random()*(100));
     
       to = (int) (Math.random()*(100));
  
   weight = (int) (Math.random()*10); 
      
  }
  
  g.addEdge(indexes[from], indexes[to], weight);
  
  lg.addEdge(indexes[from], indexes[to]);
  
      //System.out.println("Index From : "+indexes[from]+" Index To : "+indexes[to]);
  
  g.edges[from][to] = weight;
  
  byte [] fosbyte2 = (nameindexes[from]+" "+nameindexes[to]+" "+weight+"\n").getBytes();
  
  byte [] fosbyte3 = (indexes[from]+" "+indexes[to]+" "+weight+"\n").getBytes();
 
  
  fos.write(fosbyte2);
  
  fos2.write(fosbyte3);
  
  //System.out.println();

      counter--;
      
      
      if (counter==0){
          isEmpty=false;
      }
      
  }
  
 }
    
public int getHash (Key name){
    
        return hash(name);
    
    
    
}

public String conversionToString (int index) {
    
    int count = 0;
    
    
        for (int i = 0; i < M; i++) {
       
            count++;
            
            if (table[i]!=null){
                
                if (count==index){
                
                    return (String)table[i];
                
                
                }
            }
            
           
            
        }
    
    return "";
}

    
    public Key get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (int i = hash(key); table[i] != null; i = (i + 1) % M) {
            if (table[i].equals(key)) {
                return table[i];
            }
        }
        return null;
    }

   

    public double loadFactor() {
        int count = 0;
        for (int i = 0; i < M; i++) {
            if (table[i] != null) {
                count++;
            }
        }
        return count / M * 1.0;
    }

    Object conversionToString(java.util.LinkedList<Integer> temp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
