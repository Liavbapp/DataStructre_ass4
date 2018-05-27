
import java.util.NoSuchElementException;

public class SpamWordsIterator implements Iterator<Spam> {

    private Spam[] spamsArray;
    private int i;

    public SpamWordsIterator(Spams spams) {
    this.spamsArray=spams.getSpamArray();
    this.i=0;
    }

    @Override
    public boolean hasNext() {
        return i<spamsArray.length;
    }

    @Override
    public Spam next() {
        while (hasNext()) {
            Spam spamToReturn = this.spamsArray[i];
            i++;
            return spamToReturn;
        }
        throw new NoSuchElementException();
    }
}
