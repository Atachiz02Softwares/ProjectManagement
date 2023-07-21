package morpheus.softwares.projectmanagement.models;

public class Coordinator {
    private String name, phoneNumber, email;

    public Coordinator(String name, String phoneNumber, String email) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
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