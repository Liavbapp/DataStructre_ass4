

    public class MessageArrIterator implements Iterator<Message> {

     Messages messageArr;
     int i;
     int size;
    public MessageArrIterator(Messages message) {
    this.messageArr =message;
    this.i=0;
    this.size= messageArr.size();
    }

    @Override
    public boolean hasNext() {
        return i<size;
    }

    @Override
    public Message next() {
        i=i+1;
        return messageArr.getAtIndex(i-1);

    }
}
