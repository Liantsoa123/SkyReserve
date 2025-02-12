package model;

public class PriceInfo {
    private SeatType seat_type;
    private Flight flight;
    private double unit_price;
    private double discount_percentage;
    private int number;

    public PriceInfo() {
    }

    public PriceInfo(SeatType seat_type, Flight flight, double unit_price, double discount_percentage, int number) {
        this.seat_type = seat_type;
        this.flight = flight;
        this.unit_price = unit_price;
        this.discount_percentage = discount_percentage;
        this.number = number;
    }

    public SeatType getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(SeatType seat_type) {
        this.seat_type = seat_type;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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
}
