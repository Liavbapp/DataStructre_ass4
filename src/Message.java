public class Message {

    private String senderName;
    private String reciverName;
    private String message;
    private HashTable table;

    public HashTable getTable() {
        return table;
    }

    public Message() {
     this.senderName="";
     this.reciverName="";
     this.message="";
     this.table=null; //be be initiated in Messages class
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    public String[] splitMessageIntoWords() {
        return message.split(" ");
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHashTable(HashTable tableToSet) {
        this.table=tableToSet;
    }
}
