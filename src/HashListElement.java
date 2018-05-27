public class HashListElement {

    private String data;
    private HashListElement next;

    public HashListElement(String data) {
        this.data=data;
        this.next=null;
    }

    public HashListElement(String data, HashListElement next) {
        this.data=data;
        this.next=next;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public HashListElement getNext() {
        return next;
    }

    public void setNext(HashListElement next) {
        this.next = next;
    }

}
