package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactInformationDao extends JpaRepository<ContactInformation, Long> {

    Optional<ContactInformation> findById(Long id);

    Optional<ContactInformation> findByPhoneNumber(String phoneNumber);

    Optional<ContactInformation> findByCountryCodeAndPhoneNumber(String countryCode, String phoneNumber);

    Optional<ContactInformation> findByEmail(String email);


}

