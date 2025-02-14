package controller;

import dao.CityDAO;
import dao.FlightDAO;
import mg.noobframework.annotation.*;
import mg.noobframework.modelview.Modelview;
import model.Flight;

import java.sql.Date;

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
    public Modelview insertFlight(@RequestParamObject("Flight") Flight flight) {
        Modelview mv = new Modelview();
        mv.add("flight", flight);
        mv.add("url", "/showInsertFlight");
        try {
            mv.add("cities", CityDAO.findAll());
            //Check value
            if (flight.getDeparture_city_id() == flight.getArrival_city_id()) {
                mv.add("errorMessage", "Departure city and arrival city must be different");
                mv.setUrl("insertFlight.jsp");
                return mv;
            }
            if (flight.getDeparture_date().after(flight.getArrival_date()) || flight.getDeparture_date().equals(flight.getArrival_date())) {
                mv.add("errorMessage", "Departure date must be before arrival date");
                mv.setUrl("insertFlight.jsp");
                return mv;
            }

            //Update
            if (flight.getFlight_id() > 0) {
                FlightDAO.update(flight);
                mv.add("message", "Flight updated successfully");
            }
            //Insert
            else {
                FlightDAO.insert(flight);
                mv.add("message", "Flight inserted successfully");
            }

        } catch (Exception e) {
            mv.add("errorMessage", "An error occurred while inserting the flight");
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
        mv.setUrl("listsFlight.jsp");
        return mv;
    }

    @Post
    @Url("/searchFlights")
    public Modelview searchFlight(@RequestParam("departureCityId") int departureCityId,
                                  @RequestParam("arrivalCityId") int arrivalCityId, @RequestParam("departureDate") Date departureDate) throws Exception {
        Modelview mv = new Modelview();
        mv.add("flights", FlightDAO.searchFlights(departureCityId, arrivalCityId, departureDate));
        mv.add("cities", CityDAO.findAll());
        mv.setUrl("listsFlight.jsp");
        mv.add("departureCityId", departureCityId);
        mv.add("arrivalCityId", arrivalCityId);
        mv.add("departureDate", departureDate);
        return mv;
    }

    @Get
    @Url("/deleteFlight")
    @AuthMethod("ADMIN")
    public Modelview deleteFlight(@RequestParam("flightId") int flightId) {
        Modelview mv = new Modelview();
        try {
            FlightDAO.delete(flightId);
            mv.add("message", "Flight deleted successfully");
        } catch (Exception e) {
            mv.add("error", "An error occurred while deleting the flight");
        }
        mv.setUrl("showAllFlights");
        return mv;
    }

    @Get
    @Url("/showUpdateFlight")
    @AuthMethod("ADMIN")
    public Modelview showUpdateFlight(@RequestParam("flightId") int flightId) throws Exception {
        Modelview mv = new Modelview();
        mv.add("flight", FlightDAO.findById(flightId));
        mv.add("cities", CityDAO.findAll());
        mv.setUrl("insertFlight.jsp");
        return mv;
    }

}
