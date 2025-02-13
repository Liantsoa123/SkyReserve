package model;

import mg.noobframework.annotation.Required;

import java.sql.Timestamp;

public class Flight {

    private int flight_id;
    @Required
    private Timestamp departure_date;
    @Required
    private Timestamp arrival_date;
    @Required
    private City departure_city;
    @Required
    private City arrival_city;

    public Flight() {
    }

    public Flight(int flight_id, Timestamp departure_date, Timestamp arrival_date, City departure_city, City arrival_city) {
        this.flight_id = flight_id;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.departure_city = departure_city;
        this.arrival_city = arrival_city;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public Timestamp getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Timestamp departure_date) {
        this.departure_date = departure_date;
    }

    public Timestamp getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(Timestamp arrival_date) {
        this.arrival_date = arrival_date;
    }

    public City getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(City departure_city) {
        this.departure_city = departure_city;
    }

    public City getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(City arrival_city) {
        this.arrival_city = arrival_city;
    }
}
