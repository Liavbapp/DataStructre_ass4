
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Spams implements Iterable<Spam> {
    private Spam[] spamWords;

    public Spams(String spamPath) {
        int arrayLength=howManySpamWordsIn(spamPath);
        this.spamWords=new Spam[arrayLength];
        arrayInit(spamWords,spamPath);

    }

    private int howManySpamWordsIn(String spamPath) {
        int counter = 0;
        String line = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(spamPath));
            while ((line = br.readLine()) != null) {
            counter++;
            }
            return counter;
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
        return -1;
    }


    private void arrayInit(Spam[] spamWords, String spamPath) {
        String line = "";
        int i=0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(spamPath));
            while ((line = br.readLine()) != null) {
                String[] wordPlusThres=line.split(" ");
                Spam newWord=new Spam(wordPlusThres[0],Integer.parseInt(wordPlusThres[1]));
                spamWords[i]=newWord;
                i++;
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

    public Spam[] getSpamArray() {
        return this.spamWords;
    }

    @Override
    public Iterator<Spam> iterator() {
        return new SpamWordsIterator(this);
    }

}
