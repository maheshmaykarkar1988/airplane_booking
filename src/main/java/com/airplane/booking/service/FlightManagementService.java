package com.airplane.booking.service;

import com.airplane.booking.wrapper.AirportDetailsResponse;
import com.airplane.booking.wrapper.FlightDetailsResponse;
import com.airplane.booking.wrapper.FlightOfferResponse;

/**
 * Interface declaration of FlightSchedule management  related CRUD operations
 * Created by Mahesh Maykarkar on 09/02/19.
 */

public interface FlightManagementService{

    public FlightDetailsResponse getFlightDetails(String oringin, String destination, String departureDate, String classType, int seatCount);

    public FlightOfferResponse getFlightOffers(String origin, String destination);

    public AirportDetailsResponse getAirportListBySearchCriteria(String searchText);
}
