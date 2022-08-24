
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GraphMatrix {

    int edges[][]; 
   
    int numV;
    
    int numE;

    LinearProbingHash  lph = new LinearProbingHash<String>(30,2.5);
    
    ListGraph listgraph;
    
    
    public GraphMatrix(int V) {
        this.numV = V;
        edges = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                edges[i][j] = 0;
            }
        }
    }
    
    

    public void addEdge(int from, int to, int weight) {
        edges[from][to] = weight;
        edges[to][from] = weight;
    }

    public boolean isAdjacent(int v1, int v2) {
        return (edges[v1][v2] != 0);
    }

    public int degree(int v) {
        int degree = 0;
        for (int i = 0; i < numV; i++) {
            degree += edges[v][i];
        }
        return degree;
    }
    
    public static int findMaxDegree(GraphMatrix g){
        int degree = 0;
        int maxDegreeVertex = 0;
        for (int i = 0; i < g.numV; i++) {
            System.out.println("Degree: "+g.degree(i));
            if(g.degree(i)>degree){
                degree = g.degree(i);
                maxDegreeVertex = i;
            }
        }
        return maxDegreeVertex;
    }
    
    boolean isThereAPath (String name1,String name2) throws IOException {
    
        System.out.println(lph.getHash(name1)); 
        
         int i = this.lph.getHash(name1);
        
         System.out.println(lph.getHash(name2)); 
        
         int j = this.lph.getHash(name2);
      
        int k = this.edges[i][j];
        
        System.out.println(k);
        
        /*FileInputStream fis = new FileInputStream("NumericConnections.txt");
        
        Scanner scanner = new Scanner (fis);
        
        String [] data = new String []{};
       
        
        while (scanner.hasNextLine()) {
            
        data = scanner.nextLine().split(" ");
        
        listgraph.addEdge(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        
        }*/
         
        //System.out.println(listgraph.toString());
       
        return k!=0;
    }
    
     int ShortestPathLengthFromTo(String name1, String name2) throws FileNotFoundException {
         DepthFirstPaths dfs ;
         BreadthFirstSearch bfs;
       
       int n1 = lph.getHash(name1);
       
       int n2 = lph.getHash(name2);
         
       dfs = new DepthFirstPaths (listgraph,n1);
       
       bfs = new BreadthFirstSearch (listgraph,n1);       
       
       if (listgraph.isAdjacent(n1, n2)){
         
           if (dfs.pathTo(n2).length<bfs.pathTo(n2).length) {
            
               int temp = dfs.pathTo(n2).length;
               
            System.out.println("\n");   
            
               DFSfromTo(name1,name2);
               
                System.out.println("\n");   
               
               return temp;
               
         }
      else if (dfs.pathTo(n2).length>bfs.pathTo(n2).length){
             int temp1 = bfs.pathTo(n2).length;
          
             
             BFSfromTo(name1,name2);
             
              System.out.println("\n");   
             
           return temp1;
              }
         
      else {
     int temp2 = dfs.pathTo(n2).length;
           
     DFSfromTo(name1,name2);
          
      System.out.println("\n");   
     
     return temp2;
      }
         
       }
      
      
         
           System.out.println("Infinity ...");
           
           return 0;
       
     }
     
       int noOfAllPaths (String from , String to) {
       
        int sum = 1;   
           
       DepthFirstPaths dfs = new DepthFirstPaths (listgraph,lph.getHash(from));
       
       Integer [] path = dfs.pathTo(lph.getHash(to));
       
       DepthFirstPaths t = null;
       
       Integer [] t1 = null;
       
       for (int i = 0 ; i < path.length-1 ; i++) {
           
        t = new DepthFirstPaths(listgraph,path[i]);
        
        t1 = t.pathTo(lph.getHash(to));
        
        sum = sum + t1.length;
           
       }
       
       
       return sum; 
    }
       
     void AllPathsShorterThanEqualTo (int pathLen, int VertexNo, String name1) throws FileNotFoundException, IOException {
         
        int n1 = lph.getHash(name1);
        
        DepthFirstPaths dfs = new DepthFirstPaths (listgraph,n1);
        
        int n2=0;Integer tem [] = new Integer [] {};
        for(int i=0;i<lph.M;i++){
            
            
            if(lph.table[i]!=null){
                n2=i;
                
                if ((dfs.hasPathTo(n2))) {

                  if (((dfs.pathTo(n2).length)<= pathLen)) {

                        if ((dfs.pathTo(n2).length-1) >= (VertexNo-1) ) {

                           tem  =  dfs.pathTo(n2);
                           
                             
                           for (int index = 0 ; index < tem.length;index++){
                               
                               otherpathways(lph.conversionToString(tem[index]));
                               everything(lph.conversionToString(tem[index]));

                        }
                           System.out.println("---------------------------------------------------");
                  }


                  }

            }
                
            }
            
        }
        
       

        }
         
        public  void readfromFile(String f) throws FileNotFoundException, IOException {

        Scanner sc = new Scanner(new File(f));
       
        
       
            while (sc.hasNextLine()) {
        
                lph.insert(sc.nextLine());
        
       } 
            
            lph.edgeBuilder(this);
            
            listgraph=lph.lg;
    }
    
      void BFSfromTo(String name1, String name2) {
          int n1 = lph.getHash(name1);
       
          int n2 = lph.getHash(name2);
          
          BreadthFirstSearch bfs = new BreadthFirstSearch(listgraph,n1);
          
        Integer temp [] = bfs.pathTo(n2);

        for (int i = 0 ; i < temp.length ; i++) {
            
            System.out.print(lph.table[temp[i]]+"->");
              
        }
        
  System.out.println(lph.table[n2]);
       
      } 
	void DFSfromTo(String name1, String name2) throws FileNotFoundException {
            int n1 = lph.getHash(name1);
            int n2 = lph.getHash(name2);
            DepthFirstPaths dfs = new DepthFirstPaths(listgraph,n1);
            
          
        Integer temp [] = dfs.pathTo(n2);

        
        
        for (int i = 0 ; i < temp.length ; i++) {
            
            System.out.print(lph.table[temp[i]]+"->");
            
        }
            System.out.println(lph.table[n2]);
           
        }

  void everything (String ll ) throws FileNotFoundException {
      
       FileInputStream fis = new FileInputStream("Connections.txt");
        
        Scanner scanner = new Scanner (fis);
        
        String sentence [] = new String[]{}; 
        
        String temp = ll;

          
          String union = temp;
        
        while (scanner.hasNextLine()){
                   
             sentence = scanner.nextLine().split(" ");
           
            if (temp.equals(sentence[0])) {

                union = union + "->" + sentence[1];
                
                temp = sentence[1];
            
        }
      
  }
       if (union.length()!=0) { 
        
       System.out.println("Union is : "+union); 
       
       }
        
      System.out.println("");
      
      System.out.println("");
      
      
  }
  
  void showneighbourPath(String s) throws FileNotFoundException, IOException{
      
        int size = listgraph.neighborsList(lph.getHash(s)).size();
        
        Integer temp []=   listgraph.neighborsArray(lph.getHash(s));
        for (int i = 0 ; i < size;i++) {
            
          everything(lph.conversionToString(temp[i]));
            
        }
        
  }
       
    void otherpathways(String base) throws FileNotFoundException, IOException  {
         
        FileInputStream fis = new FileInputStream("Connections.txt");
        
        Scanner scanner = new Scanner (fis);
       
        String temp = base;
        
        String temp2 = "";

       int index = 0;
        
        String datas [] = new String []{};
        
        String tempsentence= "";
        
        String all [] = new String [20];

        while (scanner.hasNextLine()) {
            
            tempsentence = scanner.nextLine();
            
            datas = tempsentence.split(" ");
            
            if (temp.equals(datas[0])) {

                temp2 = datas[1];
                
                System.out.print(" "+temp2+" ");
                
                all[index]= temp2;
                
                 index++;
                 
              //   everything(temp2);
                 
            }
            
          
          }
     
        System.out.println("");
        System.out.println("");
        
         
       /* for (int i = 0 ; i < index; i++) {
            
            everything(all[i]);
            
            showneighbourPath(all[i]);
            
            System.out.println("-----------------------------------------------------");
        }*/
        
}
    
    void NoOfVerticesInComponent(String name1) {
        
        ConnectedComponents cc = new ConnectedComponents (listgraph);
        
        
        cc.dfs(listgraph,lph.getHash(name1));
        
        System.out.println("Connected component is : "+cc.count);
        
        
    }
    
    
}