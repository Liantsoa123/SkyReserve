package dto;

import model.Plane;
import model.SeatType;

public class SeatAvailabilityDTO {
    private SeatType seatType;
    private Plane plane;
    private int totalSeats;
    private int availableSeats;

    // Constructeur par défaut
    public SeatAvailabilityDTO() {
    }

    // Constructeur avec tous les champs
    public SeatAvailabilityDTO(SeatType seatType, Plane plane, int totalSeats, int availableSeats) {
        this.totalSeats = totalSeats;
        this.plane = plane;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    // Getters et Setters
    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}