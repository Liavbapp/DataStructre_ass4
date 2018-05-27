public class Message {
    private String senderName;
    private String reciverName;
    private String message;
    public Message() {
     senderName="";
     reciverName="";
     message="";
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

    public void setReceiverName(String reciverName) {
        this.reciverName = reciverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
