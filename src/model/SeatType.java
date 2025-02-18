package model;

public class SeatType {
    private int seat_type_id;
    private String type_name;

    public SeatType() {
    }

    public SeatType(int seat_type_id, String type_name) {
        this.seat_type_id = seat_type_id;
        this.type_name = type_name;

    }

    public int getSeat_type_id() {
        return seat_type_id;
    }

    public void setSeat_type_id(int seat_type_id) {
        this.seat_type_id = seat_type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
