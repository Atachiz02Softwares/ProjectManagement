package morpheus.softwares.projectmanagement.models;

public class Users {
    protected String identifier, pin, name;

    public Users(String identifier, String pin, String name) {
        setIdentifier(identifier);
        setPin(pin);
        setName(name);
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
}