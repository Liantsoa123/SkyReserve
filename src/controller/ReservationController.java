package controller;

import java.util.ArrayList;
import java.util.List;

import dao.*;
import mg.noobframework.session.Mysession;
import model.*;

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
            List<SeatAvailabilityDTO> seatAvailability = SeatAvailabilityDAO
                    .getAvailableSeats(reservation.getFlight_id());
            mv.add("seatAvailability", seatAvailability);
            mv.add("flight", flight);
            mv.add("departure_city", CityDAO.findById(flight.getDeparture_city_id()));
            mv.add("arrival_city", CityDAO.findById(flight.getArrival_city_id()));
            mv.add("seatTypes", SeatTypeDAO.findByPlaneId(flight.getPlane_id()));
            mv.setUrl("insertReservation.jsp");

            mv.add("reservation", reservation);

            reservation.setReservation_date(TimestampUtils.getCurrentTimestamp());
            reservation.setReservation_status_id(1);

            SeatAvailabilityDTO seatAvailabilityDTO = SeatAvailabilityDAO
                    .getAvailableSeatsBySeatId(reservation.getFlight_id(), reservation.getSeat_type_id());
            // Check if there are enough seats available
            if (seatAvailabilityDTO.getAvailableSeats() < reservation.getSeats_number()) {
                mv.add("errorMessage", "Pas assez de places disponibles pour ce type");
                return mv;
            }

            // Check if the user has a promotion
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

            // check Setting Reservation Flight
            SettingReservationFlight settingReservationFlight = SettingReservationFlightDAO
                    .findByFlightId(reservation.getFlight_id());
            double hoursEcart = TimestampUtils.getHoursBetweenTimestamps(reservation.getReservation_date(),
                    flight.getDeparture_date());
            SettingReservation settingReservation = SettingReservationDAO.findById(1);

            if (settingReservationFlight.getReservation() > 0) {
                if (hoursEcart <= settingReservationFlight.getReservation()) {
                    mv.add("errorMessage", "Vous ne pouvez pas réserver un vol pour moins de "
                            + settingReservationFlight.getReservation() + " heurs avant le depart");
                    return mv;
                }
            } else {
                if (settingReservation.getReservation() > 0) {
                    if (hoursEcart <= settingReservation.getReservation()) {
                        mv.add("errorMessage",
                                "Vous ne pouvez pas réserver un vol pour moins de "
                                        + settingReservation.getReservation() + " heures avant le depart");
                        return mv;
                    }
                }
            }

            // insertion of the reservation
            ReservationDAO.insert(reservation);
            mv.add("message", "Réservation insérée avec succès, promotion=" + reservation.isHas_promotion());
            // UDPATE SEAT AVAILABILITY
            seatAvailability = SeatAvailabilityDAO.getAvailableSeats(reservation.getFlight_id());
            mv.add("seatAvailability", seatAvailability);
        } catch (Exception e) {
            mv.add("errorMessage", "Erreur lors de la tentative de réservation du vol. " + e.getMessage());
        }
        return mv;
    }

    @Get
    @Url("/showMyReservations")
    @AuthMethod("CLIENT")
    public Modelview showMyReservations() {
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
            mv.add("errorMessage",
                    "Une erreur est survenue lors de la récupération des réservations " + currentUser.getName());
        }
        return mv;
    }

    @Get
    @Url("/cancelReservation")
    @AuthMethod("CLIENT")
    public Modelview cancelReservation(@RequestParam("reservationId") int reservationId) {
        Modelview mv = new Modelview();
        mv.setUrl("myReservations.jsp");
        loadReservationsData(mv);
        try {
            // Récupérer la réservation
            Reservation reservation = ReservationDAO.findById(reservationId);

            if (reservation != null) {
                // Vérifier que l'utilisateur est bien le propriétaire de la réservation
                User currentUser = (User) mysession.get("user");
                if (reservation.getUser_id() != currentUser.getUser_id()) {
                    mv.add("errorMessage", "Vous n'êtes pas autorisé à annuler cette réservation");
                    return mv;
                }

                // Récupérer le vol
                Flight flight = FlightDAO.findById(reservation.getFlight_id());

                // Vérifier les conditions d'annulation
                SettingReservationFlight settingReservationFlight = SettingReservationFlightDAO
                        .findByFlightId(flight.getFlight_id());
                SettingReservation settingReservation = SettingReservationDAO.findById(1);

                double hoursUntilDeparture = TimestampUtils.getHoursBetweenTimestamps(
                        TimestampUtils.getCurrentTimestamp(),
                        flight.getDeparture_date());

                // Vérifier le délai d'annulation
                if (settingReservationFlight.getCancelation() > 0) {
                    if (hoursUntilDeparture <= settingReservationFlight.getCancelation()) {
                        mv.add("errorMessage",
                                String.format(
                                        "Vous ne pouvez plus annuler cette réservation. Le délai d'annulation est dépassé (%.2f heures avant le départ)",
                                        (double) settingReservationFlight.getCancelation()));
                        return mv;
                    }
                } else {
                    if (settingReservation.getCancelation() > 0) {
                        if (hoursUntilDeparture <= settingReservation.getCancelation()) {
                            mv.add("errorMessage",
                                    String.format(
                                            "Vous ne pouvez plus annuler cette réservation. Le délai d'annulation est dépassé (%.2f heures avant le départ)",
                                            (double) settingReservation.getCancelation()));
                            return mv;
                        }
                    }
                }

                // Mettre à jour le statut de la réservation
                ReservationStatus cancelledStatus = ReservationStatusDAO.findByName("Annulé");
                if (cancelledStatus == null) {
                    mv.add("errorMessage", "Erreur: le statut 'Annulé' n'existe pas dans la base de données");
                    return mv;
                }

                reservation.setReservation_status_id(cancelledStatus.getReservation_status_id());
                ReservationDAO.update(reservation);

                // Si la réservation avait une promotion, remettre le compteur à jour
                if (reservation.isHas_promotion()) {
                    NumberPromotion numberPromotion = NumberPromotionDAO.findByIdFlight(reservation.getFlight_id());
                    if (numberPromotion != null) {
                        numberPromotion.setNumber(numberPromotion.getNumber() + 1);
                        NumberPromotionDAO.update(numberPromotion);
                    }
                }

                mv.add("message", "La réservation a été annulée avec succès");
            } else {
                mv.add("errorMessage", "La réservation n'existe pas");
            }

        } catch (Exception e) {
            mv.add("errorMessage", "Une erreur est survenue lors de l'annulation de la réservation: " + e.getMessage());
        }

        // UPDATE RESERVATION DATA
        loadReservationsData(mv);
        return mv;
    }

    private void loadReservationsData(Modelview mv) {
        try {
            // Recharger la page avec les réservations mises à jour
            User currentUser = (User) mysession.get("user");
            List<Reservation> reservations = ReservationDAO.findByUserId(currentUser.getUser_id());
            List<Flight> flights = new ArrayList<>();
            List<City> departureCities = new ArrayList<>();
            List<City> arrivalCities = new ArrayList<>();
            List<SeatType> seatTypes = new ArrayList<>();
            List<ReservationStatus> statuses = new ArrayList<>();

            for (Reservation res : reservations) {
                Flight flight = FlightDAO.findById(res.getFlight_id());
                flights.add(flight);
                departureCities.add(CityDAO.findById(flight.getDeparture_city_id()));
                arrivalCities.add(CityDAO.findById(flight.getArrival_city_id()));
                seatTypes.add(SeatTypeDAO.findById(res.getSeat_type_id()));
                statuses.add(ReservationStatusDAO.findById(res.getReservation_status_id()));
            }

            mv.add("reservations", reservations);
            mv.add("flights", flights);
            mv.add("departureCities", departureCities);
            mv.add("arrivalCities", arrivalCities);
            mv.add("seatTypes", seatTypes);
            mv.add("statuses", statuses);
        } catch (Exception e) {
            mv.add("errorMessage", "Une erreur est survenue lors de la récupération des réservations");
        }

    }
}
