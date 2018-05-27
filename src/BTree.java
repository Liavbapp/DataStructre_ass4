

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BTree {

    private BTreeNode _root;
    Integer tValue;

    public BTree(String val) {
        this.tValue = Integer.valueOf(val);
        this._root = new BTreeNode(tValue, true);
    }


    public void insert(String key) {
        BTreeNode tempRoot = _root;
        if (isFull()) {
            BTreeNode s = new BTreeNode(tValue, false);
            _root = s;
            s.set_keysNumber(0);
            s.getChildsArr()[0]=tempRoot;
            s.splitChild(0);
            tempRoot = s;
        }
        tempRoot.insertNonFull(key);
    }


    public boolean search(String key) {
        if (_root == null) //empty tree
            return false; //not found
        else
            return _root.search(key); //let the root search for the key
    }
    private boolean isFull() {
        return 2*tValue-1==_root.get_keysNumber();
    }

    public void createFullTree(String friendsPath) {
        String line="";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(friendsPath));
            while ((line = br.readLine()) != null) {
               this.insert(line);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public String toString(){
        if(_root.get_keysNumber()==0) //empty tree, no friends
            return "";
        else
            return _root.toString();

    }
}
