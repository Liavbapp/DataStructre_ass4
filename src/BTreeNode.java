public class BTreeNode <T> {

    private int _keysNumber;
    private boolean _isLeaf;
    private LinkedList<T> _keysList;
    private LinkedList<BTreeNode<T>> _childsList;
    int _tval;

        public BTreeNode(int tval,boolean isLeaf) {
            this._keysNumber=0;
            this._isLeaf=isLeaf;
            this._keysList =new LinkedList<>();
            this._childsList =new LinkedList<>();
            this._tval=tval;
        }


    public void splitChild(int index) {
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
        //add new child to child list
        this.addChilds(index+1,z);
        this.addKey(index,y.get_keysList().get(_tval));
        this.set_keysNumber(this._keysNumber+1);

        //add new index to child list and keys list ( lists size increase by 1)
//        this._childsList.add(null);
//        this._keysList.add (null);
//        // moving children's list one step right to add new pointer to child 'z'
//        for(int j=this._keysNumber+1;j<=index+1;j=j-1) {
//            this.setChild(j,this._childsList.get(j-1));
//        }
//        this.setChild(index+1,z);
//        //moving keys one step right
//        for(int j=this._keysNumber;j<=index;j=j-1) {
//            this.setKey(j+1,_keysList.get(j));
//        }
//        //set the new key to this node (from 'y' list)
//        this.setKey(index,y.get_keysList().get(_tval));
//        this.set_keysNumber(this._keysNumber+1);


    }


    public void insertNonFull(T key) {

        // Initialize index i as the most right element in the node
        int i=_keysNumber-1;

        if (_isLeaf) {
            while(i>=0 && ((String)_keysArr.get(i)).compareTo(((String)key))<0) //finds the right place to insert the key
                i--;

            //Insert the new key at the location we've found
            _keysArr.add(i+1,key);
            _keysNumber++;
        }
        else { //the node isn't a leaf

            //finds the child which will contain the new key
            while(i>=0 && ((String)_keysArr.get(i)).compareTo(((String)key))<0)
                i--;

            //checks whether the child is full or not
            if(_childsList.get(i+1).get_keysNumber()==2*_tval-1) {

                //full indeed, split it please
                _keysArr.get(i + 1).splitChild(i + 1);
                if(((String)_keysArr.get(i+1)).compareTo(((String)key))>0)
                    i++;
            }
            _childsList.get(i+1).insertNonFull(key);

        }

    }

    public boolean search(T key) {
        int i=0;
        while(i<_keysNumber && ((String)_keysArr.get(i)).compareTo(((String)key))<0)
            i++;
        if(i<_keysNumber && ((String)key).equals(((String)_keysArr.get(i)))) //found
            return true;
        if(_isLeaf) //no children to search in
            return false;
        else
            return _childsList.get(i).search(key);
    }




    public void addKey(int index,T key) {
            this._keysList.add(index,key);
    }
    private void setKey(int index,T key) {
            this._keysList.set(index,key);
    }
    public void addChilds(int index,BTreeNode<T> child) {
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

    public LinkedList<BTreeNode<T>> get_childsList() {
        return _childsList;
    }

    public void set_childsList(LinkedList<BTreeNode<T>> _childsList) {
        this._childsList = _childsList;
    }

    public int get_tval() {
        return _tval;
    }

    public void set_tval(int _tval) {
        this._tval = _tval;
    }


}
