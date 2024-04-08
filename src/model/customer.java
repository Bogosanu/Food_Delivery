package model;

public class customer extends user{

    private boolean adult;
    private String address;

    public customer(user usr) {
        super(usr.getFirst_name(), usr.getLast_name(), usr.getPhone_number());
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
