package model;

import mg.noobframework.annotation.Required;

import java.sql.Timestamp;

public class Reservation {
    @Required
    private int reservation_id;
    private Timestamp reservation_date;
    @Required
    private int seats_number;
    private boolean has_promotion;
    private int reservation_status_id;
    @Required
    private int seat_type_id;
    @Required
    private int flight_id;
    @Required
    private int user_id;

    public Reservation() {
    }

    public Reservation(int reservation_id, Timestamp reservation_date, int seats_number, boolean has_promotion,
                       int reservation_status_id, int seat_type_id, int flight_id, int user_id) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.seats_number = seats_number;
        this.has_promotion = has_promotion;
        this.reservation_status_id = reservation_status_id;
        this.seat_type_id = seat_type_id;
        this.flight_id = flight_id;
        this.user_id = user_id;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Timestamp getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(Timestamp reservation_date) {
        this.reservation_date = reservation_date;
    }

    public int getSeats_number() {
        return seats_number;
    }

    public void setSeats_number(int seats_number) {
        this.seats_number = seats_number;
    }

    public boolean isHas_promotion() {
        return has_promotion;
    }

    public void setHas_promotion(boolean has_promotion) {
        this.has_promotion = has_promotion;
    }

    public int getReservation_status_id() {
        return reservation_status_id;
    }

    public void setReservation_status_id(int reservation_status_id) {
        this.reservation_status_id = reservation_status_id;
    }

    public int getSeat_type_id() {
        return seat_type_id;
    }

    public void setSeat_type_id(int seat_type_id) {
        this.seat_type_id = seat_type_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }


}
