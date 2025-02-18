package model;

public class PlaneSeats {
    private SeatType seat_type;
    private Plane plane;
    private int number;

    public PlaneSeats() {
    }

    public PlaneSeats(SeatType seat_type, Plane plane, int number) {
        this.seat_type = seat_type;
        this.plane = plane;
        this.number = number;
    }

    public SeatType getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(SeatType seat_type) {
        this.seat_type = seat_type;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
