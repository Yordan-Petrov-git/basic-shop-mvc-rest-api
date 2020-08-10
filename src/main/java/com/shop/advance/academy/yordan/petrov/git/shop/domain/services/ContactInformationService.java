package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ContactInformationServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ContactInformationServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface ContactInformationService {

    /**
     * @param ContactInformation
     * @return
     */
    ContactInformationServiceViewModel createContactInformation(ContactInformationServiceModel ContactInformation);

    /**
     * @param ContactInformation
     * @return
     */
    ContactInformationServiceViewModel updateContactInformation(ContactInformationServiceModel ContactInformation);

    /**
     * @param id
     * @return
     */
    ContactInformationServiceViewModel getContactInformationById(long id);

    /**
     * @return
     */
    List<ContactInformationServiceViewModel> getAllContactInformations();

    /**
     * @param id
     * @return
     */
    ContactInformationServiceViewModel deleteContactInformationById(long id);
}
