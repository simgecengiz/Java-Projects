public class Dijkstras {
    static  int vertex = 9;
    int minDistance(int dist[], Boolean sptSet[])
    {
        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < vertex; v++)
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }
    void printSolution(int dist[])
    {
        System.out.println(
                "Vertex \t\t Distance from Source");
        for (int i = 0; i < vertex; i++)
            System.out.println(i + " \t\t\t " + dist[i]);
    }

    void dijkstra(int graph[][], int src)
    {
        int dist[] = new int[vertex];

        Boolean sptSet[] = new Boolean[vertex];

        // Bütün uzaklıklara sonsuzluk atamak
        // ve boollean dizideki bütün elemanları öncelikle false atamak
        for (int i = 0; i < vertex; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Kaynak noktanın kendine uzaklığı her zaman 0'dır.
        dist[src] = 0;

        // En kısa yolu bulma
        for (int count = 0; count < vertex - 1; count++) {

            // Köşe listesinden henüz gidilmemiş noktalardan en kısa uzaklıktaki köşeyi seçme

            int u = minDistance(dist, sptSet);

            // Gezilen noktayı işaretleme
            sptSet[u] = true;

            // Seçilen köşenin komşuluk matrisinde uzaklık değerini güncelle
            for (int v = 0; v < vertex; v++)

                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }

        printSolution(dist);
    }
}
