package model;

public class customer extends user{

    private boolean adult;
    private String address;

    public customer(String first_name, String last_name, String phone_number) {
        super(first_name, last_name, phone_number);
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
