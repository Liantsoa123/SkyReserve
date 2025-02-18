package model;

import java.sql.Timestamp;

public class Reservation {
    private int reservation_id;
    private Timestamp reservation_date;
    private int seats_number;
    private boolean has_promotion;
    private ReservationStatus reservation_status;
    private SeatType seat_type;
    private Flight flight;
    private User user;

    public Reservation() {
    }

    public Reservation(int reservation_id, Timestamp reservation_date, int seats_number, boolean has_promotion,
            ReservationStatus reservation_status, SeatType seat_type, Flight flight, User user) {
        this.reservation_id = reservation_id;
        this.reservation_date = reservation_date;
        this.seats_number = seats_number;
        this.has_promotion = has_promotion;
        this.reservation_status = reservation_status;
        this.seat_type = seat_type;
        this.flight = flight;
        this.user = user;
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

    public ReservationStatus getReservation_status() {
        return reservation_status;
    }

    public void setReservation_status(ReservationStatus reservation_status) {
        this.reservation_status = reservation_status;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
