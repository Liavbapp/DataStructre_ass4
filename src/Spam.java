public class Spam {

    private String word;
    private int threshold;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public Spam(String word, int threshold)
    {
        this.word=word;
        this.threshold=threshold;
    }

    public String toString() {
        return "Word: "+word+" Threshold: "+threshold;
    }
}
