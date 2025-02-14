package controller;

import dao.FlightDAO;
import dao.PriceInfoDAO;
import dao.SeatTypeDAO;
import mg.noobframework.annotation.*;
import mg.noobframework.modelview.Modelview;
import model.PriceInfo;

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

    @Post
    @Url("/insertPriceInfo")
    @AuthMethod("ADMIN")
    public Modelview insertPriceInfo(@RequestParamObject("PriceInfo") PriceInfo priceInfo) {
        Modelview mv = new Modelview();
        mv.add("priceInfo", priceInfo);
        mv.add("url", "/showInsertPriceInfo");
        mv.setUrl("insertPriceInfo.jsp");
        try {
            mv.add("seatTypes", SeatTypeDAO.findAll());
            mv.add("flights", FlightDAO.findAll());

            if (priceInfo.getUnit_price() < 0 || priceInfo.getDiscount_percentage() < 0 || priceInfo.getNumber() < 0) {
                mv.add("errorMessage", "Price, discount and number must be positive");
                mv.setUrl("insertPriceInfo.jsp");
                return mv;
            }
            PriceInfoDAO.insert(priceInfo);
            mv.add("message", "Price info inserted successfully");

        } catch (Exception e) {
            mv.add("errorMessage", "An error occurred while inserting the price info");
        }
        return mv;
    }

}
