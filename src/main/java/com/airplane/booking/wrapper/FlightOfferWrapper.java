package com.airplane.booking.wrapper;

import com.airplane.booking.domain.FlightOffer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Wrapper holding actual flightSchedule offer details
 * Created by Mahesh Maykarkar on 02/09/2019.
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightOfferWrapper {

    private String offerCode;
    private double offerDiscountPercentage;
    private String dateCreated;
    private String validUntil;
    private String originAirportCode;
    private String destinationAirportCode;
    private int flightNumber;

    public FlightOfferWrapper(FlightOffer flightOffer, String origin, String destination) {
        this.offerCode = flightOffer.getOfferCode();
        this.offerDiscountPercentage = flightOffer.getOfferDiscountPercentage();
        this.dateCreated = flightOffer.getDateCreated().toString();
        this.validUntil = flightOffer.getValidUntil().toString();
        this.originAirportCode = origin;
        this.destinationAirportCode = destination;
        this.flightNumber = flightOffer.getFlightSchedule().getFlightNumber();
    }

}
