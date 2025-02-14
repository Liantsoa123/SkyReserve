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
    private int  departure_city_id;
    @Required
    private int  arrival_city_id;

    public Flight() {
    }

    public Flight(int flight_id, Timestamp departure_date, Timestamp arrival_date, int departure_city_id, int arrival_city_id) {
        this.flight_id = flight_id;
        this.departure_date = departure_date;
        this.arrival_date = arrival_date;
        this.departure_city_id = departure_city_id;
        this.arrival_city_id = arrival_city_id;
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

    public int getDeparture_city_id() {
        return departure_city_id;
    }

    public void setDeparture_city_id(int departure_city_id) {
        this.departure_city_id = departure_city_id;
    }

    public int getArrival_city_id() {
        return arrival_city_id;
    }

    public void setArrival_city_id(int arrival_city_id) {
        this.arrival_city_id = arrival_city_id;
    }
}
