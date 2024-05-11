package model;

public class Product {
    private String name;
    private boolean adultsOnly;

    private float price;

    private int weight;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    private String providerName;

    public Product(String name, boolean adultsOnly, float price, int weight) {
        this.name = name;
        this.adultsOnly = adultsOnly;
        this.price = price;
        this.weight = weight;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdultsOnly() {
        return adultsOnly;
    }

    public void setAdultsOnly(boolean adultsOnly) {
        this.adultsOnly = adultsOnly;
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
