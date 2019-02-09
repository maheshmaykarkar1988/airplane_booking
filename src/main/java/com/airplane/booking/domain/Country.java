package com.airplane.booking.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Entity class to represent Country details
 * Created by Mahesh Maykarkar on 09/02/19.
 */

@Data
@Entity
@Table(name = "country")
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "iata_country_code", nullable = false)
    private String iataCountryCode;

    @Column(name = "country_name", nullable = false)
    private String countryName;

}
