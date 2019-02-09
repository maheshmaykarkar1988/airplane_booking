package com.airplane.booking.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import java.math.BigDecimal;

/**
 * Entity class to represent Jet details
 * Created by Mahesh Maykarkar on 09/02/19.
 */

@Data
@Entity
@Table(name = "jet")
public class Jet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    private int id;

    @Column(name = "jet_name", nullable = false)
    private String jetName;

    @Column(name = "jet_type")
    private String jetType;

}
