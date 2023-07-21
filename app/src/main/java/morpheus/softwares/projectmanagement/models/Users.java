package morpheus.softwares.projectmanagement.models;

public class Users {
    protected String identifier, pin, name, role;

    public Users(String identifier, String pin, String name, String role) {
        setIdentifier(identifier);
        setPin(pin);
        setName(name);
        setRole(role);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
}