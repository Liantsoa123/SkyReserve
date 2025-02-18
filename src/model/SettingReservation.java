package model;

import mg.noobframework.annotation.Required;

public class SettingReservation {
    private int setting_reservation_id;
    @Required
    private double reservation;
    @Required
    private double cancelation;

    public SettingReservation() {
    }

    public SettingReservation(int setting_reservation_id, double reservation, double cancelation) {
        this.setting_reservation_id = setting_reservation_id;
        this.reservation = reservation;
        this.cancelation = cancelation;
    }

    public int getSetting_reservation_id() {
        return setting_reservation_id;
    }

    public void setSetting_reservation_id(int setting_reservation_id) {
        this.setting_reservation_id = setting_reservation_id;
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