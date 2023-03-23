public class BinarySearchTree_millipark {
    public TreeNode root;

    public BinarySearchTree_millipark() {
        this.root = null;
    }

    public TreeNode getRoot() {
        return this.root;
    }

    // Oluştuduğum ağaca millipark nesnelerini eklerken kullandığım method
    public void insert(MilliPark mP) {
        TreeNode newNode = new TreeNode();
        newNode.milliPark = mP;
        if (root == null) {
            root = newNode;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (mP.getMilliparkAdi().compareTo(current.milliPark.getMilliparkAdi()) < 0) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    // (mP.getMilliparkAdi().compareTo(root.milliPark.getMilliparkAdi())>0)
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    int maxDepth(TreeNode root)
    {
        // Root eğer null ise o zaman bu ağacın olmadığı anlaına gelir
        if (root == null)
            return -1;

        // Eğer ağacın kökü null değilse o zaman root'un sağ ve sol çocuklarına gidebilir.
        // recursive kullanarak bütün ağacı dolaşabilirim.
        int leftDepth = maxDepth(root.leftChild);
        int rightDepth = maxDepth(root.rightChild);

        // Büyük olan uzunluğu seçip root'u da ekliyorum.
        if (leftDepth > rightDepth)
            return (leftDepth + 1);
        else
            return (rightDepth + 1);
    }

    public void inOrder(TreeNode localRoot){
        if (localRoot != null){
            inOrder(localRoot.leftChild);
            localRoot.showData();
            inOrder(localRoot.rightChild); }
    }

    // Eklediğim kelimeleri dönerken kullandığım showData fonksiyonunda biraz farklılıklar olduğu için
    // yeni bir inorder fonksiyonu tanımladım
    // Ağacı dönerken diğer inorder metodunun yaptığının aynısını yapıyor.
    // Sadece showData farklı
    public void kelime_inOrder(TreeNode localRoot){
        if (localRoot != null){
            kelime_inOrder(localRoot.leftChild);
            localRoot.showKelimeData();
            kelime_inOrder(localRoot.rightChild); }
    }

    public int countNode(TreeNode root){
        if( root == null)
            return 0;
        //Recursive fonksiyon kullanarak root'un sağ ve sol çocuğuna giderek bütün ağacı dolaşıyorum.
        //Root'u da en son ekliyorum ve daha sonra bu değeri döndürüyorum.
        return 1 + countNode(root.leftChild) + countNode(root.rightChild);
    }

    boolean isBalanced(TreeNode node)
    {
        int lh; // Soldaki ağacın(subtree) uzunluğu

        int rh; // Sağdaki ağacın(subtree) uzunluğu

        if (node == null)
            return true;

        // Soldaki ve sağdaki ağacın uzunluklarını buluyorum
        lh = maxDepth(node.leftChild);
        rh = maxDepth(node.rightChild);

        // Ağacın dengeli olup olmadığını döndürüyorum.
        return Math.abs(lh - rh) <= 1 && isBalanced(node.leftChild)
                && isBalanced(node.rightChild);
    }

    // Bilgi cümlelerinde kelimeleri oluşturduğum yeni ağaca eklerkenki kullandığım insert metodu
    public void insert(Kelime kelime){
        TreeNode node = new TreeNode();
        node.kelime = kelime;
         if (root == null) {
            root = node;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                if (kelime.getData().compareTo(current.kelime.getData()) < 0) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = node;
                        return;
                    }
                }
                else if(kelime.getData().compareTo(current.kelime.getData()) > 0) {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = node;
                        return;
                    }
                }
                else{
                    // kelime.getData().compareTo(current.kelime.getData()) = 0
                    // eklemeye çalıştığımız keime daha önce ağaca eklenmiş yani
                    // bu yüzden aynı kelimeyi bir daha ekleyemeyiz
                    // ve kelimenin sayacını arttırmalıyız.
                    current.kelime.sayac++;
                    return;
                }

            }
        }
    }

}

