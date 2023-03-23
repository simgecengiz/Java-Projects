public class MST {
    private static final int V = 5;

    public int minKey(int key[], Boolean mstSet[]){

        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }
    public void printMST(int parent[], int graph[][]){
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t"
                    + graph[i][parent[i]]);
        }
    }

    // Komşuluk matrisi kullanılarak minimum spanning tree oluşturma ve printleme
    void primMST(int graph[][]){
        // minimum spanning tree için dizi
        int parent[] = new int[V];

        int key[] = new int[V];

        // Minimum spanning tree'ye dahil olan köşeleri gösteren dizi
        Boolean mstSet[] = new Boolean[V];

        for (int i = 0; i < V; i++){
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;

        parent[0] = -1; // Minimum spanning tree'nin ilk düğümü root

        for (int count = 0; count < V - 1; count++) {

            int u = minKey(key, mstSet);

            mstSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }
}
