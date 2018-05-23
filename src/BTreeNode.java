public class BTreeNode <T> {

  private int _keysNumber;
  private boolean _isLeaf;
  private LinkedList<T> _keysArr;
  private LinkedList<BTreeNode> _childsArray;
  int _tval;

  public BTreeNode(int keysNum,boolean isLeaf,T [] keys,BTreeNode<T>[] childs,int tval) {
      this._keysNumber=keysNum;
      this._isLeaf=isLeaf;
      this._keysArr =new LinkedList<>();
      this._childsArray =new LinkedList<>();
      this._tval=tval;
  }

  private void splitChild(int index) {
      BTreeNode<T> y= _childsArray[index];
      BTreeNode<T> z=new BTreeNode<>(_tval-1,y.is_isLeaf(),new T[_tval-1],null,_tval);
      //set keys to z , t-2 is the middle key
      for(int j=0;j<_tval-1;j=j+1) {
          z.setKey(j,z.get_keysArr()[j+_tval]);
      }
      if(!y.is_isLeaf()) {
          for (int j=0;j<_tval-1;j=j+1) {
              z.setChild(y.get_childsArray()[j+_tval],j);
          }
      }
      y.set_keysNumber(_tval-1);


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

    public LinkedList<T> get_keysArr() {
        return _keysArr;
    }

    public void set_keysArr(LinkedList<T> _keysArr) {
        this._keysArr = _keysArr;
    }

    public LinkedList<BTreeNode> get_childsArray() {
        return _childsArray;
    }

    public void set_childsArray(LinkedList<BTreeNode> _childsArray) {
        this._childsArray = _childsArray;
    }

    public int get_tval() {
        return _tval;
    }

    public void set_tval(int _tval) {
        this._tval = _tval;
    }
}
