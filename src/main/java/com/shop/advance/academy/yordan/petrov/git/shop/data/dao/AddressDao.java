package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface dao for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Repository
public interface AddressDao extends JpaRepository<Address, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Address> findById(Long id);

    /**
     * @param id
     * @return
     */
    Optional<Address> deleteAddressById(Long id);

    /**
     * @param cityName
     * @return
     */
    Optional<Address> findByCityName(String cityName);

    /**
     * @param streetName
     * @return
     */
    Optional<Address> findByStreetName(String streetName);

    /**
     * @param number
     * @return
     */
    Optional<Address> findByStreetNumber(String number);

    /**
     * @param number
     * @param streetName
     * @return
     */
    Optional<Address> findByStreetNumberAndStreetName(String number, String streetName);

}
