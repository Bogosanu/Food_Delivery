package model;

public class Driver extends User {

    private String licensePlate;
    private String carModel;

    public Driver(User usr) {
        super(usr.getFirstName(), usr.getLastName(), usr.getPhoneNumber());
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
