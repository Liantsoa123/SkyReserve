-- Insert price info for all seat types associated with the plane of the new flight.
CREATE OR REPLACE FUNCTION insert_flight_price_info()
RETURNS TRIGGER AS $$
BEGIN

INSERT INTO price_info (seat_type_id, flight_id, unit_price, discount_percentage, number)
SELECT
    ps.seat_type_id,
    NEW.flight_id,
    0.00 AS unit_price,
    0.00 AS discount_percentage,
    0 AS number
FROM plane_seats ps
WHERE ps.plane_id = NEW.plane_id;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER flight_price_info_trigger
    AFTER INSERT ON flight
    FOR EACH ROW
    EXECUTE FUNCTION insert_flight_price_info();


-- Insert setting reservation flight for the new flight.
CREATE OR REPLACE FUNCTION insert_flight_setting_reservation()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO setting_reservation_flight (flight_id, reservation, cancelation)
    VALUES (NEW.flight_id, 0, 0);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER flight_setting_reservation_trigger
    AFTER INSERT ON flight
    FOR EACH ROW
    EXECUTE FUNCTION insert_flight_setting_reservation();