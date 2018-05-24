public class BTree <T> {

    private BTreeNode<T> _root;
    Integer tValue;

    public BTree(String val) {
        this.tValue = Integer.valueOf(val);
        this._root = new BTreeNode<>(tValue, true);
    }

    public void insert(T key) {
        BTreeNode<T> tempRoot = _root;
        if (isFull()) {
            BTreeNode<T> s = new BTreeNode<>(tValue, false);
            _root = s;
            s.set_keysNumber(0);
            s.addChilds(0, tempRoot);
            s.splitChild(0);
            tempRoot = s;
        }
        tempRoot.insertNonFull(key);
    }

    public boolean search(T key) {
        if (_root == null) //empty tree
            return false; //not found
        else
            return _root.search(key); //let the root search for the key
    }
    private boolean isFull() {
        return 2*tValue-1==_root.get_keysNumber();
    }

}
