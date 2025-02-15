package controller;

import dao.SettingReservationDAO;
import mg.noobframework.annotation.*;
import mg.noobframework.modelview.Modelview;
import model.SettingReservation;

@Controller
@AuthClass("ADMIN")
public class SettingReservationController {

    @Get
    @Url("/showSettingReservation")
    public Modelview showSettingReservation()throws  Exception {
        Modelview mv = new Modelview();
        SettingReservation settingReservation = SettingReservationDAO.findById(1);
        if (settingReservation != null) {
            mv.add("settingReservation", settingReservation);
        }
        mv.setUrl("settingReservation.jsp");
        return mv;
    }

    @Post
    @Url("/insertSettingReservation")
    public Modelview insertSettingReservation(@RequestParamObject("SettingReservation") SettingReservation settingReservation) {
        Modelview mv = new Modelview();
        mv.add("settingReservation", settingReservation);
        mv.add("url", "/showSettingReservation");
        mv.setUrl("settingReservation.jsp");
        try {
            //Update
            if (settingReservation.getSetting_reservation_id() > 0) {
                SettingReservationDAO.update(settingReservation);
                mv.add("message", "Setting reservation updated successfully");
            }
            //Insert
            else {
                SettingReservationDAO.insert(settingReservation);
                mv.add("message", "Setting reservation inserted successfully");
            }

        } catch (Exception e) {
            mv.add("errorMessage", "An error occurred while inserting the setting reservation");
        }
        return mv;
    }
}
