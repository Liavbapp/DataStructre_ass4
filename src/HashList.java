public class HashList {

    private HashListElement first;

    public HashList() {
        this.first=null;
    }

    public void add(String word) {
        first=new HashListElement(word,first);
    }

    public int occurrences(String word) {
        int counter=0;
        HashListElement curr=first;
        while (curr != null) {
            if(curr.getData().equals(word)) counter++;
        }
        return counter;
    }
}
