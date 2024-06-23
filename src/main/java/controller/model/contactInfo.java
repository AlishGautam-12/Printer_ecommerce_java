package controller.model;

public class contactInfo {
    private String phoneNumber;
    private String email;
    private String message;

    public contactInfo() {
    }

    public contactInfo(String phoneNumber, String email, String message) {
        super();
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
