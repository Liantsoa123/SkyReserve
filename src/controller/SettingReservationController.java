package controller;

import mg.noobframework.annotation.AuthClass;
import mg.noobframework.annotation.Controller;
import mg.noobframework.annotation.Get;
import mg.noobframework.annotation.Url;
import mg.noobframework.modelview.Modelview;

@Controller
@AuthClass("ADMIN")
public class SettingReservationController {

    @Get
    @Url("/settingReservation")
    public Modelview showSettingReservation() {
        Modelview mv = new Modelview();
        mv.setUrl("settingReservation.jsp");
        return mv;
    }
}
