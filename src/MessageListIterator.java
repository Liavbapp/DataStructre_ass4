

    public class MessageListIterator implements Iterator<Message> {

     Messages messageList;
     int i;
     int size;
    public MessageListIterator(Messages message) {
    messageList=message;
    i=0;
    size=messageList.size();
    }

    @Override
    public boolean hasNext() {
        return i<size;
    }

    @Override
    public Message next() {
        return messageList.getAtIndex(i);
    }
}
