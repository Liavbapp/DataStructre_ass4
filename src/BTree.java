public class BTree <T> {

    private BTreeNode<T> _root;
    Integer tValue;
    public BTree(String val) {
        this.tValue=Integer.valueOf(val);
        this._root=new BTreeNode<>(0,true,null,null,tValue);
    }
    public void insert(BTreeNode<T>node,int key) {

    }





}
