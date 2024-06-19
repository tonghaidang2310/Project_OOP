package demo.Entity;

public class Inbox {
    private int id;
    private int senderID;
    private int receiverID;
    private String tiltle;
    private String body;
    private String classSectionID;

    public Inbox(int id, int senderID, int receiverID, String tiltle, String body, String classSectionID) {
        this.id = id;
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.tiltle = tiltle;
        this.body = body;
        this.classSectionID = classSectionID;
    }

    public int getId() {
        return id;
    }

    public int getSenderID() {
        return senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public String getTiltle() {
        return tiltle;
    }

    public String getBody() {
        return body;
    }

    public String getClassSectionID() {
        return classSectionID;
    }
}
