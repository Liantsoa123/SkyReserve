package controller;

import dao.CityDAO;
import dao.FlightDAO;
import mg.noobframework.annotation.*;
import mg.noobframework.modelview.Modelview;
import model.Flight;

@Controller
public class FlightController {

    @Get
    @Url("/showInsertFlight")
    public Modelview showInsertFlight() throws Exception {
        Modelview mv = new Modelview();
        mv.add("cities", CityDAO.findAll());
        mv.setUrl("insertFlight.jsp");
        return mv;
    }

    @Post
    @AuthMethod("ADMIN")
    @Url("/insertFlight")
    public Modelview insertFlight(@RequestParamObject("Flight")Flight flight) {
        Modelview mv = new Modelview();
        mv.add("flight", flight);
        mv.add("url", "/showInsertFlight");
        try {
            mv.add("cites", CityDAO.findAll());
            FlightDAO.insert(flight);
            mv.add("message", "Flight inserted successfully");
        } catch (Exception e) {
            mv.add("error", "An error occurred while inserting the flight");
        }
        mv.setUrl("insertFlight.jsp");
        return mv;
    }

    @Get
    @Url("/showAllFlights")
    public Modelview showAllFlights() throws Exception {
        Modelview mv = new Modelview();
        mv.add("flights", FlightDAO.findAll());
        mv.add("cities", CityDAO.findAll());
        mv.setUrl("allFlights.jsp");
        return mv;
    }

}
