public class BTreeNode {
//tav

    private int _keysNumber;
    private boolean _isLeaf;
    private String[] keyArr;
    private BTreeNode[] childsArr;
    int _tval;

    public BTreeNode(int tval, boolean isLeaf) {
        _tval = tval;
        this._keysNumber = 0;
        this._isLeaf = isLeaf;
        keyArr = new String [2 * tval - 1];
        childsArr = new BTreeNode[2 * tval];
    }

    public void splitChild(int childIndex) {

        BTreeNode y = childsArr[childIndex];
        BTreeNode z = new BTreeNode(_tval, y.is_isLeaf());
        //add keys to 'z' from key 't' to key '2t-1' (t-1 is the middle key)
        for (int j = 0; j < _tval - 1; j = j + 1) z.setKeyToArr(j, y.getKeyArr()[j+_tval]);
        //add child to 'z'
        if (!z.is_isLeaf()) {
            for (int j = 0; j < _tval; j = j + 1) z.setChildToArr(j, y.getChildsArr()[j+_tval]);
        }
        //updating keys number of 'z' and 'y'
        y.set_keysNumber(_tval - 1);
        z.set_keysNumber(_tval - 1);

//      // moving children's list one step right to add new pointer to child 'z'
        for (int j = _keysNumber+1 ; j <= childIndex +1; j = j - 1)   {this.setChildToArr(j+1, this.getChildsArr()[j]);}
        childsArr[childIndex+1]=z;

        //moving keys one step right to add the key from node below , using one more cell of the array
        for (int j = _keysNumber; j <= childIndex + 1; j = j - 1) this.setKeyToArr(j, keyArr[j - 1]);

        //set the middle key from below to this node (from 'y' list)
        this.setKeyToArr(childIndex, y.getKeyArr()[_tval - 1]);
        //increase size of keys arr
        this.set_keysNumber(this._keysNumber + 1);
    }



        public void insertNonFull (String key){

            // Initialize index i as the most right element in the node
            int i = _keysNumber - 1;

            if (_isLeaf) {
                while (i >= 0 && keyArr[i].compareTo((key)) > 0) {
                    keyArr[i+1]=keyArr[i];
                    i--;
                }
                //Insert the new key at the location we've found
                keyArr[i+1]=key;
                _keysNumber++;
            } else { //the node isn't a leaf

                //finds the child which will contain the new key
                while (i >= 0 && (keyArr[i]).compareTo((key)) > 0)
                    i--;

                i = i + 1;

                //checks whether the child is full or not
                if (childsArr[i].get_keysNumber() == 2 * _tval - 1) {

                    //full indeed, split it please
                    childsArr[i].splitChild(i);
                    if ((keyArr[i]).compareTo((key)) < 0)
                        i++;
                }
                childsArr[i].insertNonFull(key);
            }
        }

        public boolean search (String key){
            int i = 0;
            while (i < _keysNumber && (keyArr[i]).compareTo((key)) < 0)
                i++;
            if ( (key).equals(( keyArr[i]))) //found
                return true;
            if (_isLeaf) //no children to search in
                return false;
            else
                return childsArr[i].search(key);
        }



        public String[] getKeyArr() { return keyArr; }

        public void setKeyArr(String[] keyArr) { this.keyArr = keyArr; }

        public BTreeNode[] getChildsArr() { return childsArr; }

        public void setChildsArr(BTreeNode[] childsArr) { this.childsArr = childsArr; }

        private void setKeyToArr(int index, String val) {  this.keyArr[index] = val; }

        private void setChildToArr(int index, BTreeNode child) {this.childsArr[index] = child; }

        public int get_keysNumber () { return _keysNumber; }

        public void set_keysNumber ( int _keysNumber){ this._keysNumber = _keysNumber; }

        public boolean is_isLeaf () { return _isLeaf;}

        public void set_isLeaf ( boolean _isLeaf){this._isLeaf = _isLeaf;}

        public int get_tval () { return _tval; }

        public void set_tval ( int _tval){ this._tval = _tval; }


    }


