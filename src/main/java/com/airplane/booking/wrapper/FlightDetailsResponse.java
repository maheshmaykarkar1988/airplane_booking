package com.airplane.booking.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * Response wrapper holding reference to flightSchedule details
 * Created by Mahesh Maykarkar on 02/09/2019.
 */

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FlightDetailsResponse {


    List<FlightDetailsWrapper> flightDetailsWrappers;
}
