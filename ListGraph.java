
import java.util.LinkedList;


public class ListGraph {

    LinkedList<Integer>[] edges;
   int numV;
   int numE;

    
    public ListGraph(int V) {
        this.numV = V;
        edges = new LinkedList[numV];
        for (int i = 0; i < numV; i++) {
            edges[i] = new LinkedList<>();
        }
    }

    public int getNumE() {
        return numE;
    }

    public int getNumV() {
        return numV;
    }

    public void addEdge(int from, int to) {
        if (from >= 0 && from < numV && to >= 0 && to < numV) {
            edges[from].add(to);
            edges[to].add(from);
            numE += 2;//?
        } else {
            System.out.println("Vertex out of bounds!");

        }
    }

    public void removeEdge(int from, int to) {
        if (edges[from].contains(to)) {
            edges[from].remove(to);
            if (edges[to].contains(from)) {
                edges[to].remove(from);
                numE -= 2;
            } else {
                System.err.println("Undirected edge has just one link!");
            }
        } else {
            System.err.println("Edge not found!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        sb.append("G(" + numV + "," + numE + ") \n");
        for (int i = 0; i < numV; i++) {
            sb.append(i + " " + edges[i].toString() + "\n");
        }
        return sb.toString();
    }

    public boolean isAdjacent(int from, int to) {
        return edges[from].contains(to);
    }

    public LinkedList<Integer> neighborsList(int from) {
        return (LinkedList<Integer>) edges[from].clone();
    }

    public Integer[] neighborsArray(int from) {
        Integer[] ar = new Integer[edges[from].size()];
        edges[from].toArray(ar);
        return ar;
    }
   

    public int degree(int from) {
        return edges[from].size();
    }

    public static int findMaxDegree(ListGraph G) {//kontrol et
        int degree = 0;
        int maxDegVer = 0;
        for (int i = 0; i < G.numV; i++) {
            if (G.edges[i].size()>degree) {
                degree = G.edges[i].size();
                maxDegVer = i;
            }
        }   
        return maxDegVer;
    }

  
    }

