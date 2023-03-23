public class AVLTree {
    public Node root;

    public int findHeight(Node node){
        if (node == null)
            return 0;

        return node.getHeight();
    }

    public int max(int a, int b) {
        return Math.max(a, b);
    }

    int getBalance(Node N) {
        if (N == null)
            return 0;

        return findHeight(N.getLeftChild()) - findHeight(N.getRightChild());
    }

    public Node rightRotate(Node y) {
        Node x = y.getLeftChild();
        Node T2 = x.getRightChild();

        // Rotasyon işlemi
        x.setRightChild(y);
        y.setLeftChild(T2);

        //Yükseklikleri güncelle
        y.setHeight(max(findHeight(y.getLeftChild()), findHeight(y.getRightChild())) + 1);
        x.setHeight(max(findHeight(x.getLeftChild()), findHeight(x.getRightChild())) + 1);


        return x;
    }

    public Node leftRotate(Node x) {
        Node y = x.getRightChild();
        Node T2 = y.getLeftChild();

        // Rotasyon işlemi
        y.setLeftChild(x);
        x.setRightChild(T2);

        //  Yükseklikleri güncelle
        x.setHeight(max(findHeight(x.getLeftChild()), findHeight(x.getRightChild())) + 1);
        y.setHeight(max(findHeight(y.getLeftChild()), findHeight(y.getRightChild())) + 1);

        return y;
    }

    public Node insert(Node node, int key) {
        if (node == null)
            return (new Node(key));

        if (key < node.getKey())
            node.setLeftChild(insert(node.getLeftChild(), key));
        else if (key > node.getKey())
            node.setRightChild(insert(node.getRightChild(), key));
        else
            return node;

        // Düğümün boyunu güncelleme
        node.setHeight( 1 + max(findHeight(node.getLeftChild()),findHeight(node.getRightChild())));

        // Düğümün dengesiz olup olmadığını kontrol etmek için ana düğümün denge faktörünü alma
        int balance = getBalance(node);

        // Node eğer dengesizleşmişse
        // sol-sol durumu
        if (balance > 1 && key < node.getLeftChild().getKey())
            return rightRotate(node);

        // sağ-sağ durumu
        if (balance < -1 && key > node.getRightChild().getKey())
            return leftRotate(node);

        // sol -sağ durumu
        if (balance > 1 && key > node.getLeftChild().getKey()) {
            node.setLeftChild(leftRotate(node.getLeftChild()));
            return rightRotate(node);
        }

        // sağ sol durumu
        if (balance < -1 && key < node.getRightChild().getKey()) {
            node.setRightChild(rightRotate(node.getRightChild()));
            return leftRotate(node);
        }

        return node;
    }
    public void preOrder(Node node) {
        // preorder dizilim
        if (node != null) {
            System.out.print(node.getKey() + " ");
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }
}
