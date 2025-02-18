package controller;

import java.util.List;

import dao.FlightDAO;
import dao.SeatAvailabilityDAO;
import dao.SeatTypeDAO;
import dto.SeatAvailabilityDTO;
import mg.noobframework.annotation.AuthMethod;
import mg.noobframework.annotation.Controller;
import mg.noobframework.annotation.Get;
import mg.noobframework.annotation.RequestParam;
import mg.noobframework.annotation.Url;
import mg.noobframework.modelview.Modelview;
import model.Flight;

@Controller
public class ReservationController {

    @Get
    @Url("/reserve")
    @AuthMethod("CLIENT")
    public Modelview showReservation(@RequestParam("flightId") int flightId) throws Exception {
        Modelview mv = new Modelview();
        Flight flight = FlightDAO.findById(flightId);
        List<SeatAvailabilityDTO> seatAvailability = SeatAvailabilityDAO.getAvailableSeats(flightId);
        mv.add("seatAvailability", seatAvailability);
        mv.add("flight", flight);
        mv.add("seatTypes", SeatTypeDAO.findAll());
        mv.setUrl("insertReservation.jsp");
        return mv;
    }
}
