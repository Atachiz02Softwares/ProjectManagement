package morpheus.softwares.projectmanagement.models;

public class Attachment {
    private int id;
    private String email, attachmentName;
    private byte[] attachmentData;

    public Attachment(int id, String email, String attachmentName, byte[] attachmentData) {
        setId(id);
        setEmail(email);
        setAttachmentName(attachmentName);
        setAttachmentData(attachmentData);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public byte[] getAttachmentData() {
        return attachmentData;
    }

    public void setAttachmentData(byte[] attachmentData) {
        this.attachmentData = attachmentData;
    }
}