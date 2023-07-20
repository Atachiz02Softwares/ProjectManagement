package morpheus.softwares.projectmanagement.models;

public class Supervisors {
    private String name, phoneNumber, email, area;

    public Supervisors(String name, String phoneNumber, String email, String area) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setEmail(email);
        setArea(area);
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}