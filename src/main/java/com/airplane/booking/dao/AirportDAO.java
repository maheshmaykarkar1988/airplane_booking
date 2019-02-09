package com.airplane.booking.dao;

import com.airplane.booking.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interface representing database related operations for Airport entity
 * Created by Mahesh Maykarkar on 09/02/19.
 */

public interface AirportDAO extends JpaRepository<Airport,String>, JpaSpecificationExecutor<Airport> {

}
