package com.airplane.booking.wrapper;

import com.airplane.booking.domain.FlightSchedule;
import com.airplane.booking.domain.Jet;
import com.airplane.booking.domain.JetSeatClass;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * Wrapper holding actual flightSchedule details
 * Created by Mahesh Maykarkar on 02/09/2019.
 */

@Data
@JsonInclude (value = JsonInclude.Include.NON_NULL)
public class FlightDetailsWrapper {
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int flightNumber;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int flightJetId;
    private String flightName;
    private Date flightDate;
    private Date arrivalDateTime;
    private Date departureDateTime;
    private String originAirportCode;
    private String destinationAirportCode;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int ticketAmount;
    private String seatClassType;

    public FlightDetailsWrapper(FlightSchedule flight, String classType, int seatCount) {

        JetSeatClass jetSeatClass = flight.getJetSeatClass().stream().filter(x -> x.getClassType().equals(classType)).findAny().orElse(null);

        if(jetSeatClass != null) {
            this.ticketAmount = jetSeatClass.getSeatFare() * seatCount;
            this.seatClassType = jetSeatClass.getClassType();
            this.flightDate = flight.getFlightDate();
            this.arrivalDateTime = flight.getArrivalDateTime();
            this.departureDateTime = flight.getDepartureDateTime();
            this.originAirportCode = flight.getOriginAirportCode().getAirportCode();
            this.destinationAirportCode = flight.getDestinationAirportCode().getAirportCode();

            Jet jet = flight.getJet();
            this.flightJetId = jet.getId();
            this.flightName = jet.getJetName();
        }
    }
}
