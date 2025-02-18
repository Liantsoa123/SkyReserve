package model;

public class NumberPromotion {
    private int number_promotion_id;
    private int number;
    private Flight flight;

    public NumberPromotion() {
    }

    public NumberPromotion(int number_promotion_id, int number, Flight flight) {
        this.number_promotion_id = number_promotion_id;
        this.number = number;
        this.flight = flight;
    }

    public int getNumber_promotion_id() {
        return number_promotion_id;
    }

    public void setNumber_promotion_id(int number_promotion_id) {
        this.number_promotion_id = number_promotion_id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
