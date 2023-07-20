package morpheus.softwares.projectmanagement.models;

public class Users {
    protected String idNumber, pin;

    public Users(String idNumber, String pin) {
        setIdNumber(idNumber);
        setPin(pin);
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}