package com.airplane.booking.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Entity class to represent FlightSchedule details
 * Created by Mahesh Maykarkar on 09/02/19.
 */

@Data
@Entity
@Table(name = "flight_schedule")
public class FlightSchedule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "flight_number", nullable = false)
    private int flightNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "flight_date", nullable = false)
    private Date flightDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "arrival_time", nullable = false)
    private Date arrivalDateTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "departure_time", nullable = false)
    private Date departureDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_airport_code")
    private Airport originAirportCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_airport_code")
    private Airport destinationAirportCode;

    @OneToMany(mappedBy = "flightSchedule")
    private List<JetSeatClass> jetSeatClass;

    @OneToMany(mappedBy = "flightSchedule")
    private List<FlightOffer> flightOffers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_jet_id")
    private Jet jet;

}
