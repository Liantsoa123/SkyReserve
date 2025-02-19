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
        mv.add("seatTypes", SeatTypeDAO.findAll());
        mv.setUrl("insertReservation.jsp");
        return mv;
    }

    @Post
    @Url("/insertReservation")
    @AuthMethod("Client")
    public Modelview insertReserve(@RequestParam("flight_id") int flightId,
            @RequestParam("seats_number") int seatsNumber, @RequestParam("seat_type_id") int seatypeId) {
        Modelview mv = new Modelview();
        try {
            Flight flight = FlightDAO.findById(flightId);
            List<SeatAvailabilityDTO> seatAvailability = SeatAvailabilityDAO.getAvailableSeats(flightId);
            mv.add("seatAvailability", seatAvailability);
            mv.add("flight", flight);
            mv.add("departure_city", CityDAO.findById(flight.getDeparture_city_id()));
            mv.add("arrival_city", CityDAO.findById(flight.getArrival_city_id()));
            mv.add("seatTypes", SeatTypeDAO.findAll());
            mv.setUrl("insertReservation.jsp");
            mv.add("url", "/reserve");

            // Check setting reservation
            SettingReservation settingReservation = SettingReservationDAO.findById(1);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            if (TimestampUtils.getHoursBetweenTimestamps(timestamp, flight.getDeparture_date()) < settingReservation
                    .getReservation()) {
                mv.add("errrorMessage", "Reservation Impossible pour ce vol");
            }

            SeatAvailabilityDTO seatAvailabilityDTO = SeatAvailabilityDAO.getAvailableSeatsBySeatId(flightId,
                    seatypeId);

            Reservation reservation = new Reservation();
            reservation.setReservation_id(1);
            reservation.setReservation_date(timestamp);
            reservation.setReservation_status_id(1);
            reservation.setSeat_type_id(seatypeId);
            reservation.setFlight_id(flightId);
            reservation.setUser_id(((User) getMysession().get("user")).getUser_id());

            NumberPromotion numberPromotion = NumberPromotionDAO.findByIdFlight(flightId);

            // Check nombre place disponible
            if (seatsNumber > seatAvailabilityDTO.getAvailableSeats()) {
                mv.add("errorMessage", "Nombre de place insuffisant pour");
            }
            reservation.setSeats_number(seatsNumber);

            // Check Promotion
            if (seatAvailabilityDTO.getDiscountPercentage() > 0
                    && seatAvailabilityDTO.getNumberPromotions() <= seatsNumber) {
                if (numberPromotion != null) {
                    if (numberPromotion.getNumber() > 0) {
                        numberPromotion.setNumber(numberPromotion.getNumber() - 1);
                        reservation.setHas_promotion(true);
                    }
                } else {
                    reservation.setHas_promotion(true);
                }
            }

            // if Reglo
            ReservationDAO.insert(reservation);
            if (numberPromotion != null) {
                NumberPromotionDAO.update(numberPromotion);
            }
            mv.add("message", "Reservation finish ");
        } catch (Exception e) {
            mv.add("errorMessage", "Error while trying to insert reservation " + e.getMessage());
        }

        return mv;
    }
}
