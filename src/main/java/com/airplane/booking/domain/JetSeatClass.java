package com.airplane.booking.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class to represent JetSeatClass details
 * Created by Mahesh Maykarkar on 09/02/19.
 */

@Data
@Entity
@Table(name = "jet_seat_class")
public class JetSeatClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @Column(name = "class_type", nullable = false)
    private String classType;

    @Column(name = "seat_fare")
    private int seatFare;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="flight_number")
    private FlightSchedule flightSchedule;
}
