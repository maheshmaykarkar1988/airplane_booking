package com.airplane.booking.wrapper;

import com.airplane.booking.domain.Airport;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Wrapper holding actual airport details
 * Created by Mahesh Maykarkar on 02/09/2019.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AirportDetailsWrapper {

    private String airportCode;
    private String airportName;
    private String airportDescription;
    private String city;
    private String country;

    public AirportDetailsWrapper(Airport airport) {
        this.airportCode = airport.getAirportCode();
        this.airportName = airport.getAirportName();
        this.airportDescription = airport.getAirportDescription();
        this.city = airport.getAirportCity();
        this.country = airport.getCountry().getCountryName();
    }
}
