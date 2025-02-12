package model;

public class ReservationStatus {
    private int reservation_status_id;
    private String reservation_name;

    public ReservationStatus() {
    }

    public ReservationStatus(int reservation_status_id, String reservation_name) {
        this.reservation_status_id = reservation_status_id;
        this.reservation_name = reservation_name;
    }

    public int getReservation_status_id() {
        return reservation_status_id;
    }

    public void setReservation_status_id(int reservation_status_id) {
        this.reservation_status_id = reservation_status_id;
    }

    public String getReservation_name() {
        return reservation_name;
    }

    public void setReservation_name(String reservation_name) {
        this.reservation_name = reservation_name;
    }
}
