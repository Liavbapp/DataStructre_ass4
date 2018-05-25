public class Spam {

    private String word;
    private int threshold;

    public Spam(String word, int threshold)
    {
        this.word=word;
        this.threshold=threshold;
    }

    public String toString() {
        return "Word: "+word+" Threshold: "+threshold;
    }
}
