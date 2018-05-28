
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Messages  implements Iterable<Message> {

    private Message[]messageArr;
    private Message messageObject;


    public void generateMessages(String MessagesPath) {
        String line = "";
        messageArr=new Message[countMessages((MessagesPath))];
        BufferedReader br = null;
        int c=0;

        try { br = new BufferedReader(new FileReader(MessagesPath));
            //first line is empty so we read it and move on
            br.readLine();
            while ((line = br.readLine()) != null) {
                messageObject = new Message();
                while (line != null && !line.equals("#")) {
                    if (getlineType(line) == 1) {
                        messageObject.setSenderName(line.substring(5));
                        line = br.readLine();
                    } else if (getlineType(line) == 2) {
                        messageObject.setReciverName(line.substring(3));
                        line = br.readLine();
                    } else {
                        String message = line;
                        while ((line = br.readLine()) != null && !line.equals("#")) message = message + line;
                        messageObject.setMessage(message);
                        messageArr[c]=messageObject;
                        c=c+1;
                    }
                }
            }
        } catch (FileNotFoundException e) {
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

    // 1.sender 2.reciver 3.message
    private int getlineType(String line) {
        int linelength = line.length();

        if (linelength == 0) {
            // if line is empty we throw an exception
            throw new IllegalArgumentException("empty line in a message");
        } else if (linelength == 1 | linelength == 2) {
            return 3;
        }
        if (line.substring(0, 3).equals("To:")) {
            return 2;
        } else if (line.substring(0, 5).equals("From:")) {
            return 1;
        } else return 3;
    }

    //function retrive the number of messages that will be in the txt file
    private int countMessages(String path) {
        int counter = 1;
        String line = "";
        BufferedReader br = null;
        boolean isFileEmpty = true;
        try {
            br = new BufferedReader(new FileReader(path));
            //first line is empty so we read it and move on
            br.readLine();
            while ((line = br.readLine()) != null) {
                //check validity of the line, if there it is an empty line error will thrown
                getlineType(line);
                if (isFileEmpty) isFileEmpty = false;
                if (line.equals("#")) counter = counter + 1;
            }
        } catch (FileNotFoundException e) {
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
    return counter;
    }

    public int size(){ return messageArr.length;}
    public Message getAtIndex(int index) {return messageArr[index];}
    @Override
        public Iterator<Message> iterator() {
      return  new MessageArrIterator(this);
    }

    public void createHashTables(String arg) {
        int m=Integer.valueOf(arg);
        Iterator<Message> iter=iterator();
        while (iter.hasNext()) {
            Message curr=iter.next();
            HashTable table=new HashTable(m);
            String[] messageWords=curr.splitMessageIntoWords();
            for (String word: messageWords)
                table.add(word);
        curr.setHashTable(table);

        }


    }

    public String findSpams(String spamPath, BTree btree) {
        String output="";
        int messageIndex=0;
        boolean isFriends;
        Spams spams=new Spams(spamPath);
        Iterator<Message> iter=iterator();

        while (iter.hasNext()) {
            Message curr=iter.next();
            isFriends=btree.search(curr.getReciverName()+" & "+curr.getSenderName()) ||
                    btree.search(curr.getSenderName()+" & "+curr.getReciverName());
            if (!isFriends) {
                if (isSpam(curr, spams))
                    output += messageIndex+",";
            }
            messageIndex++;
        }
        if(output.length()>0)
            output=output.substring(0,output.length()-1); //removing the last "," if output isn't empty
        return output;
    }

    private boolean isSpam(Message curr, Spams spams) {
        Iterator<Spam> spamIter=new SpamWordsIterator(spams);
        boolean hasSpam=false;
        int length=curr.length();
        while (spamIter.hasNext()&&!hasSpam) {
            Spam currSpamWord=spamIter.next();
            if((100*((double)(curr.getTable().occurncesOfWord(currSpamWord.getWord()))/length))>currSpamWord.getThreshold()) {
                hasSpam=true;
            }
        }

        return hasSpam;
    }


}
