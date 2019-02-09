package com.airplane.booking.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class to represent Airport details
 * Created by Mahesh Maykarkar on 09/02/19.
 */

@Data
@Entity
@Table(name = "airport")
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "airport_code", nullable = false)
    private String airportCode;

    @Column(name = "airport_name", nullable = false)
    private String airportName;

    @Column(name = "airport_description", nullable = false)
    private String airportDescription;

    @Column(name = "airport_city", nullable = false)
    private String airportCity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iata_country_code")
    private Country country;
}
