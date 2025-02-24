package dto;

import model.Plane;
import model.SeatType;

public class SeatAvailabilityDTO {
    private SeatType seatType;
    private Plane plane;
    private int totalSeats;
    private int availableSeats;
    private double unitPrice;
    private double discountPercentage;
    private int numberPromotions;

    // Constructeur par défaut
    public SeatAvailabilityDTO() {
    }

    // Constructeur avec tous les champs
    public SeatAvailabilityDTO(SeatType seatType, Plane plane, int totalSeats, int availableSeats, double unitPrice,
            double discountPercentage, int numberPromotions) {
        this.seatType = seatType;
        this.plane = plane;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.unitPrice = unitPrice;
        this.discountPercentage = discountPercentage;
        this.numberPromotions = numberPromotions;
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

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getNumberPromotions() {
        return numberPromotions;
    }

    public void setNumberPromotions(int numberPromotions) {
        this.numberPromotions = numberPromotions;
    }
}