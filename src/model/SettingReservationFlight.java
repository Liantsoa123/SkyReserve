package model;

import mg.noobframework.annotation.Numerique;
import mg.noobframework.annotation.Required;

public class SettingReservationFlight {
    private int setting_reservation_flight_id ;
    @Required
    private int flight_id ;
    @Required
    @Numerique(min = 0 , max = 24)
    private double reservation;
    @Required
    @Numerique(min = 0, max = 24)
    private double cancelation;

    public SettingReservationFlight() {
    }

    public SettingReservationFlight(int setting_reservation_flight_id, int flight_id, double reservation, double cancelation) {
        this.setting_reservation_flight_id = setting_reservation_flight_id;
        this.flight_id = flight_id;
        this.reservation = reservation;
        this.cancelation = cancelation;
    }

    public int getSetting_reservation_flight_id() {
        return setting_reservation_flight_id;
    }

    public void setSetting_reservation_flight_id(int setting_reservation_flight_id) {
        this.setting_reservation_flight_id = setting_reservation_flight_id;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public double getReservation() {
        return reservation;
    }

    public void setReservation(double reservation) {
        this.reservation = reservation;
    }

    public double getCancelation() {
        return cancelation;
    }

    public void setCancelation(double cancelation) {
        this.cancelation = cancelation;
    }
}
