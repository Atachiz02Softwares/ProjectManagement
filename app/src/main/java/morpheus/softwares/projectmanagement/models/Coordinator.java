package morpheus.softwares.projectmanagement.models;

public class Coordinator {
    private int id;
    private String name, phoneNumber, email;

    public Coordinator(int id, String name, String phoneNumber, String email) {
        setId(id);
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}