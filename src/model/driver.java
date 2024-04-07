package model;

public class driver extends user{

    private String license_plate;
    private String car_model;

    public driver(String first_name, String last_name, String phone_number) {
        super(first_name, last_name, phone_number);
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }
}
