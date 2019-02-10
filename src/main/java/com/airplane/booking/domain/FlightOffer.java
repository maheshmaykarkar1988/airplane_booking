package com.airplane.booking.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entity class to represent FlightOffer details
 * Created by Mahesh Maykarkar on 09/02/19.
 */

@Data
@Entity
@Table(name = "flight_offer")
public class FlightOffer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "offer_id", nullable = false, updatable = false)
    private int offerId;

    @Column(name = "offer_code",nullable = false)
    private String offerCode;

    @Column(name = "offer_discount_percentage", nullable = false)
    private double offerDiscountPercentage;

    @Column(name = "date_created",nullable = false)
    private Date dateCreated;

    @Column(name = "valid_until", nullable = false)
    private Date validUntil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_number")
    private FlightSchedule flightSchedule;

}
