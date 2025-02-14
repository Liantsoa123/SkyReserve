package controller;

import dao.FlightDAO;
import dao.SeatTypeDAO;
import mg.noobframework.annotation.AuthMethod;
import mg.noobframework.annotation.Controller;
import mg.noobframework.annotation.Get;
import mg.noobframework.annotation.Url;
import mg.noobframework.modelview.Modelview;

@Controller
public class PriceInfoController {

    @Get
    @Url("/showInsertPriceInfo")
    @AuthMethod("ADMIN")
    public Modelview showInsertPriceInfo() throws Exception {
        Modelview mv = new Modelview();
        mv.add("seatTypes", SeatTypeDAO.findAll());
        mv.add("flights", FlightDAO.findAll());
        mv.setUrl("insertPriceInfo.jsp");
        return mv;
    }

}
