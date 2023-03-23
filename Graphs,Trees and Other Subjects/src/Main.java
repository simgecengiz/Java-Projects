public class Main {
    public static void main(String[] args) {
        System.out.println("******************************");
        System.out.println("AVL Tree Insertion : ");
        AVLTree tree = new AVLTree();
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 40);
        tree.root = tree.insert(tree.root, 50);
        tree.root = tree.insert(tree.root, 25);

        System.out.println("Preorder traversal" + " of constructed tree is : ");
        tree.preOrder(tree.root);
        System.out.println();
        System.out.println("******************************");

        System.out.println("Dijkstra's Algorithm : ");
        int graphDijkstra[][] = new int[][]{{ 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        Dijkstras dj = new Dijkstras();
        dj.dijkstra(graphDijkstra,0);

        System.out.println("******************************");

        System.out.println("Prim's Algorithm : ");
        MST t = new MST();
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 } };

        t.primMST(graph);
        System.out.println("******************************");

        System.out.println("Breadth First Traversal Algorithm : ");
        Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");
        g.BFS(2);
        System.out.println();

        System.out.println("******************************");

        String trie_keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};

        Trie.TrieNode root = new Trie.TrieNode();
        Trie trie = new Trie(root);
        int i;
        for (i = 0; i < trie_keys.length ; i++)
            trie.insert(trie_keys[i]);

    }
}
