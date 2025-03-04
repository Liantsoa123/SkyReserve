package controller;

import java.sql.Timestamp;
import java.util.List;

import dao.*;
import mg.noobframework.session.Mysession;
import model.*;
import org.postgresql.translation.messages_cs;

import dto.SeatAvailabilityDTO;
import mg.noobframework.annotation.*;
import mg.noobframework.modelview.Modelview;
import utils.TimestampUtils;

@Controller
public class ReservationController {
    private Mysession mysession;

    public Mysession getMysession() {
        return mysession;
    }

    public void setMysession(Mysession mysession) {
        this.mysession = mysession;
    }

    @Get
    @Url("/reserve")
    @AuthMethod("CLIENT")
    public Modelview showReservation(@RequestParam("flightId") int flightId) throws Exception {
        Modelview mv = new Modelview();
        Flight flight = FlightDAO.findById(flightId);
        List<SeatAvailabilityDTO> seatAvailability = SeatAvailabilityDAO.getAvailableSeats(flightId);
        mv.add("seatAvailability", seatAvailability);
        mv.add("flight", flight);
        mv.add("departure_city", CityDAO.findById(flight.getDeparture_city_id()));
        mv.add("arrival_city", CityDAO.findById(flight.getArrival_city_id()));
        mv.add("seatTypes", SeatTypeDAO.findByPlaneId(flight.getPlane_id()));
        mv.setUrl("insertReservation.jsp");
        return mv;
    }

    @Post
    @AuthMethod("Client")
    @Url("/insertReservation")
    public Modelview insertReservation(@RequestParamObject("Reservation") Reservation reservation) {
        Modelview mv = new Modelview();

        try {
            Flight flight = FlightDAO.findById(reservation.getFlight_id());
            List<SeatAvailabilityDTO> seatAvailability = SeatAvailabilityDAO.getAvailableSeats(reservation.getFlight_id());
            mv.add("seatAvailability", seatAvailability);
            mv.add("flight", flight);
            mv.add("departure_city", CityDAO.findById(flight.getDeparture_city_id()));
            mv.add("arrival_city", CityDAO.findById(flight.getArrival_city_id()));
            mv.add("seatTypes", SeatTypeDAO.findByPlaneId(flight.getPlane_id()));
            mv.setUrl("insertReservation.jsp");

            mv.add("reservation", reservation);

            SeatAvailabilityDTO seatAvailabilityDTO = SeatAvailabilityDAO.getAvailableSeatsBySeatId(reservation.getFlight_id(), reservation.getSeat_type_id());
            // Check if there are enough seats available
            if (seatAvailabilityDTO.getAvailableSeats() < reservation.getSeats_number()) {
                mv.add("errorMessage", "Not enough seats available for this type");
                return mv;
            }

            //Check if the user has a promotion
            if (seatAvailabilityDTO.getNumberPromotions() > 0) {
                reservation.setHas_promotion(false);
                NumberPromotion numberPromotion = NumberPromotionDAO.findByIdFlight(reservation.getFlight_id());
                if (numberPromotion != null && numberPromotion.getNumber() > 0) {
                    reservation.setHas_promotion(true);
                    numberPromotion.setNumber(numberPromotion.getNumber() - 1);
                    NumberPromotionDAO.update(numberPromotion);
                } else {
                    reservation.setHas_promotion(false);
                }
            }

            //insertion of the reservation
            reservation.setReservation_date(TimestampUtils.getCurrentTimestamp());
            reservation.setReservation_status_id(1);
            ReservationDAO.insert(reservation);
            mv.add("message", "Reservation inserted successfully avec promotion=" + reservation.isHas_promotion());
            //UDPATE SEAT AVAILABILITY
            seatAvailability = SeatAvailabilityDAO.getAvailableSeats(reservation.getFlight_id());
            mv.add("seatAvailability", seatAvailability);
        } catch (Exception e) {
            mv.add("errorMessage", "Error while trying to reserve flight. " + e.getMessage());
        }
        return mv;
    }
}
