
public class HashTable {

    private HashList[]array;
    private int m;

    public HashTable(int m) {
        this.array=new HashList[m];
        this.m=m;
    }

    public void add(String wordToAdd) {
        int index=HashFunction(wordToAdd);
        array[index].add(wordToAdd);
    }

    public int occurncesOfWord(String word) {
        int index=HashFunction(word);
        return array[index].occurrences(word);
    }

    public int HashFunction(String st) {
        int asciiSum=0;
        for(int i=0;i<st.length();i++)
            asciiSum+=((int)st.charAt(i));
        return asciiSum%m;
    }

}
