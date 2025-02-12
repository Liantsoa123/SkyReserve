package model;

public class City {
    private int city_id;
    private String city_name;
    private String country;

    public City() {
    }

    public City(int city_id, String city_name, String country) {
        this.city_id = city_id;
        this.city_name = city_name;
        this.country = country;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
