package com.airplane.booking.serviceImpl;

import com.airplane.booking.dao.AirportDAO;
import com.airplane.booking.dao.FlightDAO;
import com.airplane.booking.dao.FlightOfferDAO;
import com.airplane.booking.domain.Airport;
import com.airplane.booking.domain.FlightSchedule;
import com.airplane.booking.domain.FlightOffer;
import com.airplane.booking.exception.BookingValidationException;
import com.airplane.booking.exception.NoRecordFoundException;
import com.airplane.booking.service.FlightManagementService;
import com.airplane.booking.wrapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FlightManagementServiceImpl implements FlightManagementService {

    private static final Logger logger = LoggerFactory.getLogger(FlightManagementServiceImpl.class);

    @Autowired
    FlightDAO flightDAO;

    @Autowired
    FlightOfferDAO flightOfferDAO;

    @Autowired
    AirportDAO airportDAO;

    @Value("${origin.error.message}")
    private String originErrorMessage;

    @Value("${destination.error.message}")
    private String destinationErrorMessage;

    @Value("${departureDate.error.message}")
    private String departureDateErrorMessage;

    @Value("${departure.date.format}")
    private String departureDateFormat;

    @Value("${departure.date.parse.error.message}")
    private String dateParseErrorMessage;

    @Value("${flight.schedule.error.message}")
    private String flightScheduleErrorMessage;

    @Value("${offer.not.found.error.message}")
    private String offerNotFoundErrorMessage;


    @Transactional
    @Override
    public FlightDetailsResponse getFlightDetails(String origin, String destination, String departureDate, String classType, int seatCount) {

        performValidation(origin, destination);

        getSQLDate(departureDate);

        List<FlightSchedule> flightDetails = flightDAO.findByTravelLocation(origin, destination, getSQLDate(departureDate));

        if (CollectionUtils.isEmpty(flightDetails)) {
            throw new NoRecordFoundException(flightScheduleErrorMessage);
        }

        List<FlightDetailsWrapper> flightDetailsWrappers = flightDetails.stream().map(flight -> {
            FlightDetailsWrapper flightDetailsWrapper = new FlightDetailsWrapper(flight, classType, seatCount);
            return flightDetailsWrapper;
        }).collect(Collectors.toList());

        FlightDetailsResponse flightDetailsResponse = new FlightDetailsResponse();
        flightDetailsResponse.setFlightDetailsWrappers(flightDetailsWrappers);
        return flightDetailsResponse;
    }

    private void performValidation(String origin, String destination) {
        if (StringUtils.isEmpty(origin)) {
            throw new BookingValidationException(originErrorMessage);
        }

        if (StringUtils.isEmpty(destination)) {
            throw new BookingValidationException(destinationErrorMessage);
        }
    }

    @Transactional
    @Override
    public FlightOfferResponse getFlightOffers(String origin, String destination) {

        performValidation(origin, destination);

        List<FlightOffer> flightOffers = flightOfferDAO.getFlightOffers(origin, destination);

        if (CollectionUtils.isEmpty(flightOffers)) {
            throw new NoRecordFoundException(String.format(offerNotFoundErrorMessage, origin, destination));
        }

        List<FlightOfferWrapper> flightOfferWrappers = flightOffers.stream().map(flightOffer -> {
            FlightOfferWrapper flightOfferWrapper = new FlightOfferWrapper(flightOffer, origin, destination);
            return flightOfferWrapper;
        }).collect(Collectors.toList());

        FlightOfferResponse flightOfferResponse = new FlightOfferResponse();
        flightOfferResponse.setFlightOffers(flightOfferWrappers);
        return flightOfferResponse;
    }

    private Date getSQLDate(String departureDate) {
        if (StringUtils.isEmpty(departureDate)) {
            throw new BookingValidationException(departureDateErrorMessage);
        }

        SimpleDateFormat sdf = new SimpleDateFormat(departureDateFormat);
        java.sql.Date sqlDate = null;
        try {
            java.util.Date date = sdf.parse(departureDate);
            sqlDate = new Date(date.getTime());
        } catch (ParseException e) {
            logger.info(departureDateErrorMessage);
            throw new IllegalArgumentException(departureDateErrorMessage);
        }
        return sqlDate;
    }

    @Transactional
    @Override
    public AirportDetailsResponse getAirportListBySearchCriteria(String searchText) {
        List<Airport> airports = airportDAO.findAll(new Specification<Airport>() {
            @Override
            public Predicate toPredicate(Root<Airport> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(!Objects.isNull(searchText)) {
                    predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get("airportCode"), "%" + searchText + "%"),
                            criteriaBuilder.like(root.get("airportName"), "%" + searchText + "%"),
                            criteriaBuilder.like(root.get("airportDescription"), "%" + searchText + "%")
                    ));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        List<AirportDetailsWrapper> airportDetailsWrappers = airports.stream().map(airport -> {
            AirportDetailsWrapper airportDetailsWrapper = new AirportDetailsWrapper(airport);
            return airportDetailsWrapper;
        }).collect(Collectors.toList());

        AirportDetailsResponse airportDetailsResponse = new AirportDetailsResponse();
        airportDetailsResponse.setAirportDetails(airportDetailsWrappers);
        return  airportDetailsResponse;
    }
}
