import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
    private final int V;
    private final LinkedList<Integer>[] adj; // Komşuluk listesi

    public Graph(int v){
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i){
            adj[i] = new LinkedList();
        }
    }
    public void addEdge(int v, int w){
        adj[v].add(w); }

    public void BFS(int s){
        boolean[] visited = new boolean[V];

        // Breadth first search için kuyruk yaratmak
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()){
                int n = i.next();
                if (!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }
}
