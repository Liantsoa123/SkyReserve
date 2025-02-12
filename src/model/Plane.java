package model;

import java.sql.Date;

public class Plane {
    private int plane_id;
    private String model;
    private Date manufacture_date;

    public Plane() {
    }

    public Plane(int plane_id, String model, Date manufacture_date) {
        this.plane_id = plane_id;
        this.model = model;
        this.manufacture_date = manufacture_date;
    }

    public int getPlane_id() {
        return plane_id;
    }

    public void setPlane_id(int plane_id) {
        this.plane_id = plane_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Date getManufacture_date() {
        return manufacture_date;
    }

    public void setManufacture_date(Date manufacture_date) {
        this.manufacture_date = manufacture_date;
    }
}
