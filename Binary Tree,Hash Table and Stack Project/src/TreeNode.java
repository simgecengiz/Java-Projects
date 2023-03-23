public class TreeNode {
    public TreeNode rightChild;

    public TreeNode leftChild;

    public MilliPark milliPark;

    public Kelime kelime;

    public TreeNode(){
        this.rightChild = null;
        this.leftChild = null;
    }
    public void showData(){
        System.out.println(" " + this.milliPark + " ");
    }

    public void showKelimeData(){
        System.out.println(" " + this.kelime.getData() +"---> Sayac : " + kelime.sayac);
    }


}
