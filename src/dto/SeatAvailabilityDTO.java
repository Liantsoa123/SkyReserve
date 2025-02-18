package dto;

public class SeatAvailabilityDTO {
    private int seatTypeId;
    private int planeId;
    private int totalSeats;
    private int availableSeats;

    // Constructeur par défaut
    public SeatAvailabilityDTO() {
    }

    // Constructeur avec tous les champs
    public SeatAvailabilityDTO(int seatTypeId, int planeId, int totalSeats, int availableSeats) {
        this.seatTypeId = seatTypeId;
        this.planeId = planeId;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    // Getters et Setters
    public int getSeatTypeId() {
        return seatTypeId;
    }

    public void setSeatTypeId(int seatTypeId) {
        this.seatTypeId = seatTypeId;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
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