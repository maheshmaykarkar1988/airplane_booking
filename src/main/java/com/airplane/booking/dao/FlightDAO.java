package com.airplane.booking.dao;

import com.airplane.booking.domain.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

/**
 * Interface representing database related operations for FlightSchedule entity
 * Created by Mahesh Maykarkar on 09/02/19.
 */

public interface FlightDAO extends JpaRepository<FlightSchedule,Integer> {

    @Query("select f from FlightSchedule f where f.originAirportCode.airportCode = :origin and f.destinationAirportCode.airportCode = :destination and f.flightDate= :flightDate")
    public List<FlightSchedule> findByTravelLocation(@Param("origin") String origin, @Param("destination") String destination, @Param("flightDate") Date flightDate);
}

