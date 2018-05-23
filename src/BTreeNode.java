public class BTreeNode <T> {

    private int _keysNumber;
    private boolean _isLeaf;
    private LinkedList<T> _keysList;
    private LinkedList<BTreeNode<T>> _childsList;
    int _tval;

        public BTreeNode(int keysNum,boolean isLeaf,LinkedList<T> keys,LinkedList<BTreeNode<T> childs,int tval) {
            this._keysNumber=keysNum;
            this._isLeaf=isLeaf;
            this._keysList =new LinkedList<>();
            this._childsList =new LinkedList<>();
            this._tval=tval;
        }

    private void splitChild(int index) {
        BTreeNode<T> y= _childsList.get(index);
        BTreeNode<T> z=new BTreeNode<>(_tval-1,y.is_isLeaf(),null,null,_tval);
        //add keys to 'z' from key 't' to key '2t-1'
        for(int j=0;j<_tval-1;j=j+1) {
            z.addKey(j,z.get_keysList().get(j+_tval));
        }
        //add child to 'z'
        if(!z.is_isLeaf()) {
            for (int j=0;j<_tval-1;j=j+1) {
                z.addChilds(j,y.get_childsList().get(j+_tval));
            }
        }
        y.set_keysNumber(_tval-1);
        //add new index to child list and keys list ( lists size increase by 1)
        this._childsList.add(null);
        this._keysList.add (null);
        // moving children's list one step right to add new pointer to child 'z'
        for(int j=this._keysNumber+1;j<=index+1;j=j-1) {
            this.setChild(j,this._childsList.get(j-1));
        }
        this.setChild(index+1,z);
        //moving keys one step right
        for(int j=this._keysNumber;j<=index;j=j-1) {
            this.setKey(j+1,_keysList.get(j));
        }
        //set the new key to this node (from 'y' list)
        this.setKey(index,y.get_keysList().get(_tval));
        this.set_keysNumber(this._keysNumber+1);


    }

    private void addKey(int index,T key) {
            this._keysList.add(index,key);
    }
    private void setKey(int index,T key) {
            this._keysList.set(index,key);
    }
    private void addChilds(int index,BTreeNode<T> child) {
            this._childsList.add(index,child);
    }
    private void setChild(int index,BTreeNode<T>child) {
            this._childsList.set(index,child);
    }

    public int get_keysNumber() {
        return _keysNumber;
    }

    public void set_keysNumber(int _keysNumber) {
        this._keysNumber = _keysNumber;
    }

    public boolean is_isLeaf() {
        return _isLeaf;
    }

    public void set_isLeaf(boolean _isLeaf) {
        this._isLeaf = _isLeaf;
    }

    public LinkedList<T> get_keysList() {
        return _keysList;
    }

    public void set_keysList(LinkedList<T> _keysList) {
        this._keysList = _keysList;
    }

    public LinkedList<BTreeNode> get_childsList() {
        return _childsList;
    }

    public void set_childsList(LinkedList<BTreeNode> _childsList) {
        this._childsList = _childsList;
    }

    public int get_tval() {
        return _tval;
    }

    public void set_tval(int _tval) {
        this._tval = _tval;
    }


}
