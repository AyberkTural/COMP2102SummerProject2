
public class ConnectedComponents {

    private boolean marked[];
    int count;
    int[] id;

    public ConnectedComponents(ListGraph g) {
        marked = new boolean[g.getNumV()];
        count = 0;
        id = new int[g.getNumV()];
        for (int v = 0; v < g.getNumV(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    public void dfs(ListGraph g, int v) {
        marked[v] = true;
        id[v] = count;
        Integer[] a = (Integer[]) g.neighborsArray(v);
        for (int i = 0; i < a.length; i++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }

    }
}
