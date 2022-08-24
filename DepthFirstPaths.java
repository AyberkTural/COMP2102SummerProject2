
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DepthFirstPaths {

    boolean[] marked;
    int[] edgeTo;
    int from;
    LinearProbingHash lph ;
    
    public boolean hasPathTo(int w) {
        return marked[w];
    }

    public Integer[] pathTo(int w) {
        int k = edgeTo[w];
        java.util.Stack<Integer> st = new java.util.Stack<Integer>();
        st.push(k);
        while (k != this.from) {
            k = edgeTo[k];
            st.push(k);
        }
     

        Integer[] path = new Integer[st.size()];
        for (int i = 0; i < path.length; i++) {
            path[i] = st.pop();
        }
        return path;

    }

    public void printPathTo(int w) {

        
        
        Integer[] path = pathTo(w);

        for (int i = 0; i < path.length; i++) {

            System.out.print("->" +  path[i]);
        }
        System.out.println("->" + w);
        
     
        

    }
    
    public void printpathTo(int w) throws FileNotFoundException {

        FileInputStream fis = new FileInputStream("CharactersWithHashs.txt");
        
        Scanner scanner = new Scanner (fis);
        
        Integer[] path = pathTo(w);

        String datas [] = new String [] {};
        
      
            
        for (int i = 0; i < path.length; i++) {
            
            while (scanner.hasNextLine()) {
       
            datas = scanner.nextLine().split(" ");
            if ((Integer.parseInt(datas[1]))==(path[i]) ){
            
            System.out.print("->" +  datas[0]);
        }
            
             if ((Integer.parseInt(datas[1]))==(w) ){
        System.out.println("*" +  datas[0]);
             }
             
               
        }
        

    }

    }
    
  

    public DepthFirstPaths(ListGraph g, int from) {
        edgeTo = new int[g.getNumV()];
        marked = new boolean[g.getNumV()];
        this.from = from;
        dfs(g, from);
    }

    public void dfs(ListGraph g, int source) {
        marked[source] = true;
        Integer[] a = (Integer[]) g.neighborsArray(source);
        for (int i = 0; i < a.length; i++) {
            int neighbor = a[i];
            if (!marked[neighbor]) {
            //    System.out.println("..."+neighbor);
                dfs(g, neighbor);
                edgeTo[neighbor] = source;
            }
        }
    }
}
