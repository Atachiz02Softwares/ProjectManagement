package morpheus.softwares.projectmanagement.models;

public class User {
    protected int id;
    protected String email, pin, name, role, status, onlineOffline;

    public User(int id, String email, String pin, String name, String role, String status, String onlineOffline) {
        setId(id);
        setEmail(email);
        setPin(pin);
        setName(name);
        setRole(role);
        setStatus(status);
        setOnlineOffline(onlineOffline);
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOnlineOffline() {
        return onlineOffline;
    }

    public void setOnlineOffline(String onlineOffline) {
        this.onlineOffline = onlineOffline;
    }
}