package com.airplane.booking.endpoints;

import com.airplane.booking.service.FlightManagementService;
import com.airplane.booking.wrapper.AirportDetailsResponse;
import com.airplane.booking.wrapper.FlightDetailsResponse;
import com.airplane.booking.wrapper.FlightOfferResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Endpoints related to FlightSchedule booking CRUD operations
 * Created by Mahesh Maykarkar on 09/02/19.
 */

@Api(value = "user", description = "Rest API for airplane ticket booking", tags = "User API")
@RestController
@RequestMapping(value = {"/flights"})
public class FlightManagementEndPoint {

    @Autowired
    FlightManagementService flightManagementService;

    @GetMapping(value = "/schedule", produces = "application/json")
    @ApiOperation(value = "Returns flightSchedule details for given origin, destination and departure date.Departure date must be yyyy-MM-dd format", response = AirportDetailsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = FlightDetailsResponse.class),
            @ApiResponse(code = 422, message = "Please enter origin"),
            @ApiResponse(code = 422, message = "Please enter destination")
        }
    )
    public ResponseEntity getFlightDetails(@Valid @RequestParam(required = true) String origin, @RequestParam(required = true) String destination, @RequestParam(required = true) String departureDate, @RequestParam(defaultValue = "ECONOMY") String classType, @RequestParam(defaultValue = "1") Integer seatCount) {
        FlightDetailsResponse flightDetails = flightManagementService.getFlightDetails(origin, destination, departureDate, classType, seatCount);
        return new ResponseEntity<FlightDetailsResponse>(flightDetails, HttpStatus.OK);
    }

    @GetMapping(value = "/offers", produces = "application/json")
    @ApiOperation(value = "Returns all possible offers on fare for given origin and destination", response = FlightOfferResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = FlightOfferResponse.class),
            @ApiResponse(code = 422, message = "Please enter origin"),
            @ApiResponse(code = 422, message = "Please enter destination"),
            @ApiResponse(code = 204, message = "No offeres were found"),
        }
    )
    public ResponseEntity getOffers(@Valid @RequestParam(required = true) String origin, @RequestParam(required = true) String destination) {
        FlightOfferResponse flightOffers = flightManagementService.getFlightOffers(origin, destination);
        return new ResponseEntity<FlightOfferResponse>(flightOffers, HttpStatus.OK);
    }

    @GetMapping(value = "/search", produces = "application/json")
    @ApiOperation(value = "Returns all possible results based on searchkey i.e. id , name a or description of an airport.It will return all airport details if nothing has been provided in search", response = AirportDetailsResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AirportDetailsResponse.class)
        }
    )
    public ResponseEntity getAirportDetails(@Valid @RequestParam(required = true) String searchKey) {
        AirportDetailsResponse airportDetails = flightManagementService.getAirportListBySearchCriteria(searchKey);
        return new ResponseEntity<AirportDetailsResponse>(airportDetails, HttpStatus.OK);
    }
}
