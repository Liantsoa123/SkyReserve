package controller;

import dao.SettingReservationDAO;
import dao.SettingReservationFlightDAO;
import mg.noobframework.annotation.*;
import mg.noobframework.modelview.Modelview;
import model.SettingReservation;
import model.SettingReservationFlight;

@Controller
@AuthClass("ADMIN")
public class SettingReservationController {

    @Get
    @Url("/showSettingReservation")
    public Modelview showSettingReservation() throws Exception {
        Modelview mv = new Modelview();
        SettingReservation settingReservation = SettingReservationDAO.findById(1);
        if (settingReservation != null) {
            mv.add("settingReservation", settingReservation);
        }
        mv.setUrl("settingReservation.jsp");
        return mv;
    }

    @Get
    @Url("/showSettingReservationFlight")
    public Modelview showSettingReservationFlight(@RequestParam("flightId") int flightId) {
        Modelview mv = new Modelview();
        try {
            SettingReservationFlight settingReservationFlight = SettingReservationFlightDAO.findByFlightId(flightId);
            mv.add("settingReservationFlight", settingReservationFlight);
            mv.setUrl("settingReservation.jsp");
            mv.add("flightId", flightId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @Post
    @Url("/insertSettingReservation")
    public Modelview insertSettingReservation(
            @RequestParamObject("SettingReservation") SettingReservation settingReservation) {
        Modelview mv = new Modelview();
        mv.add("settingReservation", settingReservation);
        mv.setUrl("settingReservation.jsp");
        try {
            // Update
            if (settingReservation.getSetting_reservation_id() > 0) {
                SettingReservationDAO.update(settingReservation);
                mv.add("message", "Setting reservation updated successfully");
            }
            // Insert
            else {
                SettingReservationDAO.insert(settingReservation);
                mv.add("message", "Setting reservation inserted successfully");
            }

        } catch (Exception e) {
            mv.add("errorMessage", "An error occurred while inserting the setting reservation");
        }
        return mv;
    }

    @Post
    @Url("/insertSettingReservationFlight")
    public Modelview insertSettingReservationFlight(
            @RequestParamObject("SettingReservationFlight") SettingReservationFlight settingReservationFlight) {
        Modelview mv = new Modelview();
        mv.add("settingReservationFlight", settingReservationFlight);
        mv.setUrl("settingReservation.jsp");
        try {
            // Update
            if (settingReservationFlight.getSetting_reservation_flight_id() > 0) {
                SettingReservationFlightDAO.update(settingReservationFlight);
                mv.add("message", "Setting reservation flight updated successfully ");
            }
            // Insert
            else {
                SettingReservationFlightDAO.insert(settingReservationFlight);
                mv.add("message", "Setting reservation flight inserted successfully");
            }

        } catch (Exception e) {
            mv.add("errorMessage", "An error occurred while inserting the setting reservation flight");
        }
        return mv;
    }

}
