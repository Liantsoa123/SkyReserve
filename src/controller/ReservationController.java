package controller;

import java.sql.Timestamp;
import java.util.ArrayList;
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

            reservation.setReservation_date(TimestampUtils.getCurrentTimestamp());
            reservation.setReservation_status_id(1);

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

            //check Setting Reservation Flight
            SettingReservationFlight settingReservationFlight = SettingReservationFlightDAO.findByFlightId(reservation.getFlight_id());
            long hoursEcart = TimestampUtils.getHoursBetweenTimestamps(reservation.getReservation_date(), flight.getDeparture_date());
            SettingReservation settingReservation = SettingReservationDAO.findById(1);
            if (settingReservationFlight != null && settingReservation != null) {

                if (hoursEcart <= settingReservationFlight.getReservation() && settingReservationFlight.getReservation() != 0) {
                    mv.add("errorMessage", "You can't reserve a flight less than " + settingReservationFlight.getReservation() + " hours before departure");
                    return mv;
                } else if (hoursEcart <= settingReservation.getReservation() && settingReservation.getReservation() != 0) {
                    {
                        mv.add("errorMessage", "You can't reserve a flight less than " + settingReservation.getReservation() + " hours before departure");
                        return mv;
                    }
                }
            }

            //insertion of the reservation
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

    @Get
    @Url("/showMyReservations")
    @AuthMethod("CLIENT")
    public Modelview showMyReservations( ) {
        Modelview mv = new Modelview();
        mv.setUrl("myReservations.jsp");
        User currentUser = (User) mysession.get("user");
        try {

            List<Reservation> reservations = ReservationDAO.findByUserId(currentUser.getUser_id());

            // Récupérer les détails pour chaque réservation
            List<Flight> flights = new ArrayList<>();
            List<City> departureCities = new ArrayList<>();
            List<City> arrivalCities = new ArrayList<>();
            List<SeatType> seatTypes = new ArrayList<>();
            List<ReservationStatus> statuses = new ArrayList<>();

            for (Reservation reservation : reservations) {
                Flight flight = FlightDAO.findById(reservation.getFlight_id());
                flights.add(flight);
                departureCities.add(CityDAO.findById(flight.getDeparture_city_id()));
                arrivalCities.add(CityDAO.findById(flight.getArrival_city_id()));
                seatTypes.add(SeatTypeDAO.findById(reservation.getSeat_type_id()));
                statuses.add(ReservationStatusDAO.findById(reservation.getReservation_status_id()));
            }

            mv.add("reservations", reservations);
            mv.add("flights", flights);
            mv.add("departureCities", departureCities);
            mv.add("arrivalCities", arrivalCities);
            mv.add("seatTypes", seatTypes);
            mv.add("statuses", statuses);


        } catch (Exception e) {
            mv.add("errorMessage", "Une erreur est survenue lors de la récupération des réservations " + currentUser.getName());
        }
        return mv;
    }

}
