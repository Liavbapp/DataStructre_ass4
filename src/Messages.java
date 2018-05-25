
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Messages  implements Iterable<Message> {
    LinkedList<Message>messageList;
    public Messages() {
        messageList=new LinkedList<>();
    }
    public void generateMessages(String MessagesPath) {
        String line="";
//        int counter=0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(MessagesPath));
            //first line is empty so we read it and move on
            br.readLine();
            Message messageObject;
            while ((line = br.readLine()) != null) {
               messageObject =new Message();
                while (line!=null&&!line.equals("#")) {
                    if(getlineType(line)==1) {
                        messageObject.setSenderName(line.substring(5));
                        line=br.readLine();
                    }
                    else if(getlineType(line)==2) {
                        messageObject.setReciverName(line.substring(3));
                        line=br.readLine();
                    }
                    else {
                        String message=line;
                        while ((line = br.readLine())!=null&&!line.equals("#")) {
                            message=message+line;
                        }
                        messageObject.setMessage(message);
                        messageList.add(messageObject);

                        }
                }
            }
//            if(counter==0) {
//                throw new IllegalArgumentException("the txt file is empty");
//            }
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

    // 1.sender 2.reciver 3.message
    private int getlineType(String line) {
        int linelength=line.length();

        if(linelength==0) {
            throw new IllegalArgumentException("empty message");
        }
        else if (linelength==1|linelength==2) {
            return 3;
        }
        if(line.substring(0,3).equals("To:")) {
            return 2;
        }
        else if (line.substring(0,5).equals("From:")) {
            return 1;
        }
        else return 3;

    }
    public boolean add(Message msg) {
        return messageList.add(msg);
    }
    public int size() {
        return messageList.size();
    }
    public Message getAtIndex(int index)
    {
        return messageList.get(index);
    }

    @Override
        public Iterator<Message> iterator() {
      return  new MessageListIterator(this);
    }
}
