package model;

public class PriceInfo {
    private int seat_type_id;
    private int flight_id;
    private double unit_price;
    private double discount_percentage;
    private int number;

    public PriceInfo() {
    }

    public PriceInfo(int seat_type_id , int flight_id, double unit_price, double discount_percentage, int number) {
        this.seat_type_id = seat_type_id;
        this.flight_id = flight_id;
        this.unit_price = unit_price;
        this.discount_percentage = discount_percentage;
        this.number = number;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getDiscount_percentage() {
        return discount_percentage;
    }

    public void setDiscount_percentage(double discount_percentage) {
        this.discount_percentage = discount_percentage;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSeat_type_id() {
        return seat_type_id;
    }

    public void setSeat_type_id(int seat_type_id) {
        this.seat_type_id = seat_type_id;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }
}
