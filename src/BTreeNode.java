public class BTreeNode <T> {
    public T[] getKeyArr() {
        return keyArr;
    }

    public void setKeyArr(T[] keyArr) {
        this.keyArr = keyArr;
    }

    public BTreeNode[] getChildsArr() {
        return childsArr;
    }

    public void setChildsArr(BTreeNode[] childsArr) {
        this.childsArr = childsArr;
    }

    private int _keysNumber;
    private boolean _isLeaf;
    private T[] keyArr;
    private BTreeNode[] childsArr;
    private LinkedList<T> _keysList;
    private LinkedList<BTreeNode<T>> _childsList;
    int _tval;

    public BTreeNode(int tval, boolean isLeaf) {
        _tval = tval;
        this._keysNumber = 0;
        this._isLeaf = isLeaf;
        this._keysList = new LinkedList<>();
        this._childsList = new LinkedList<>();
        keyArr = new T[2 * tval - 1];
        childsArr = new BTreeNode[2 * tval];
    }

    private void setKeyToArr(int index, T val) {
        this.keyArr[index] = val;
    }

    private void setChildToArr(int index, BTreeNode<T> child) {
        this.childsArr[index] = child;
    }

    public void splitChild(int childIndex) {
        BTreeNode<T> y = _childsList.get(childIndex);
        BTreeNode<T> z = new BTreeNode<T>(_tval - 1, y.is_isLeaf());
        //add keys to 'z' from key 't' to key '2t-1' (t-1 is the middle key)
        for (int j = 0; j < _tval - 1; j = j + 1) {
//            z.addKey(j,z.get_keysList().get(j+_tval));
            z.setKeyToArr(j, this.keyArr[j + _tval]);
        }
        //add child to 'z'
        if (!z.is_isLeaf()) {
            for (int j = 0; j < _tval; j = j + 1) {
//                z.addChilds(j,y.get_childsList().get(j+_tval));
                z.setChildToArr(j, this.childsArr[j + _tval]);
            }
        }
        //updating keys number of 'z' and 'y'
        y.set_keysNumber(_tval - 1);
        z.set_keysNumber(_tval - 1);

        this.addKey(childIndex, y.get_keysList().get(_tval - 1));
        //add new child to child list
        this.addChilds(childIndex + 1, z);
        ;
        this.set_keysNumber(this._keysNumber + 1);

//       // moving children's list one step right to add new pointer to child 'z'
        for(int j=_keysNumber+1;j<=childIndex+1;j=j-1) {
            this.setChildToArr(j,this.getChildsArr()[j-1]);
        }
//        this.setChild(index+1,z);
        //moving keys one step right to add the key from node below , using one more cell of the array
        for (int j = this._keysNumber; j <= childIndex + 1; j = j - 1) {
            this.setKeyToArr(j, _keysList.get(j - 1));
//        }
//          //set the middle key from below to this node (from 'y' list)
            this.setKeyToArr(childIndex, y.getKeyArr()[_tval - 1]);
            //increase size of keys arr
            this.set_keysNumber(this._keysNumber+1);


        }
    }


        public void insertNonFull (T key){

            // Initialize index i as the most right element in the node
            int i = _keysNumber - 1;

            if (_isLeaf) {
                while (i >= 0 && ((String) _keysList.get(i)).compareTo(((String) key)) < 0) //finds the right place to insert the key
                    i--;

                //Insert the new key at the location we've found
                _keysList.add(i + 1, key);
                _keysNumber++;
            } else { //the node isn't a leaf

                //finds the child which will contain the new key
                while (i >= 0 && ((String) _keysList.get(i)).compareTo(((String) key)) < 0)
                    i--;

                i = i + 1;

                //checks whether the child is full or not
                if (_childsList.get(i).get_keysNumber() == 2 * _tval - 1) {

                    //full indeed, split it please
                    _childsList.get(i).splitChild(i);
                    if (((String) _keysList.get(i)).compareTo(((String) key)) > 0)
                        i++;
                }
                _childsList.get(i).insertNonFull(key);
            }

        }

        public boolean search (T key){
            int i = 0;
            while (i < _keysNumber && ((String) _keysArr.get(i)).compareTo(((String) key)) < 0)
                i++;
            if (i < _keysNumber && ((String) key).equals(((String) _keysArr.get(i)))) //found
                return true;
            if (_isLeaf) //no children to search in
                return false;
            else
                return _childsList.get(i).search(key);
        }


        public void addKey ( int index, T key){
            this._keysList.add(index, key);
        }
        private void setKey ( int index, T key){
            this._keysList.set(index, key);
        }
        public void addChilds ( int index, BTreeNode<T > child){
            this._childsList.add(index, child);
        }
        private void setChild ( int index, BTreeNode<T > child){
            this._childsList.set(index, child);
        }

        public int get_keysNumber () {
            return _keysNumber;
        }

        public void set_keysNumber ( int _keysNumber){
            this._keysNumber = _keysNumber;
        }

        public boolean is_isLeaf () {
            return _isLeaf;
        }

        public void set_isLeaf ( boolean _isLeaf){
            this._isLeaf = _isLeaf;
        }

        public LinkedList<T> get_keysList () {
            return _keysList;
        }

        public void set_keysList (LinkedList < T > _keysList) {
            this._keysList = _keysList;
        }

        public LinkedList<BTreeNode<T>> get_childsList () {
            return _childsList;
        }

        public void set_childsList (LinkedList < BTreeNode < T >> _childsList) {
            this._childsList = _childsList;
        }

        public int get_tval () {
            return _tval;
        }

        public void set_tval ( int _tval){
            this._tval = _tval;
        }


    }


