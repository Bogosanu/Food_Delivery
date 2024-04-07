package model;

public class product {
    private String name;
    private boolean adultsonly;

    private float price;

    private int weight;

    public product(String name, boolean adultsonly, float price, int weight) {
        this.name = name;
        this.adultsonly = adultsonly;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdultsonly() {
        return adultsonly;
    }

    public void setAdultsonly(boolean adultsonly) {
        this.adultsonly = adultsonly;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
