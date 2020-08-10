package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.ContactInformation;
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
public interface ContactInformationDao extends JpaRepository<ContactInformation, Long> {

    /**
     * @param id
     * @return
     */
    Optional<ContactInformation> findById(Long id);

    /**
     * @param phoneNumber
     * @return
     */
    Optional<ContactInformation> findByPhoneNumber(String phoneNumber);

    /**
     * @param countryCode
     * @param phoneNumber
     * @return
     */
    Optional<ContactInformation> findByCountryCodeAndPhoneNumber(String countryCode, String phoneNumber);

    /**
     * @param email
     * @return
     */
    Optional<ContactInformation> findByEmail(String email);


}

