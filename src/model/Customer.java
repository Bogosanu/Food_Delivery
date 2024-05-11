package model;

public class Customer extends User {

    private boolean adult;
    private String address;

    public Customer(User usr) {
        super(usr.getFirstName(), usr.getLastName(), usr.getPhoneNumber());
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
