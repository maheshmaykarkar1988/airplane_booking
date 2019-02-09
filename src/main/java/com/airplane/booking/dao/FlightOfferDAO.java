package com.airplane.booking.dao;

import com.airplane.booking.domain.FlightOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interface representing database related operations for FlightOffer entity
 * Created by Mahesh Maykarkar on 09/02/19.
 */


public interface FlightOfferDAO extends JpaRepository<FlightOffer, Integer> {

    @Query(value = "select * from flight_offer fo left join flight_schedule f on fo.flight_number = f.flight_number where f.origin_airport_code = :origin and f.destination_airport_code = :destination", nativeQuery = true)
    public List<FlightOffer> getFlightOffers(@Param("origin") String origin, @Param("destination") String destination);
}
