package morpheus.softwares.projectmanagement.models;

public class Projects {
    private int id;
    private String idNumber, approvedTopic;

    public Projects(int id, String idNumber, String approvedTopic) {
        setId(id);
        setIdNumber(idNumber);
        setApprovedTopic(approvedTopic);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getApprovedTopic() {
        return approvedTopic;
    }

    public void setApprovedTopic(String approvedTopic) {
        this.approvedTopic = approvedTopic;
    }
}