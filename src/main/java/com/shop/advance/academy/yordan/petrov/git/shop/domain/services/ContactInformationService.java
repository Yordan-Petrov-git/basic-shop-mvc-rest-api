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

    ContactInformationServiceViewModel createContactInformation(ContactInformationServiceModel ContactInformation);

    ContactInformationServiceViewModel updateContactInformation(ContactInformationServiceModel ContactInformation);

    ContactInformationServiceViewModel getContactInformationById(long id);

    List<ContactInformationServiceViewModel> getAllContactInformations();

    ContactInformationServiceViewModel deleteContactInformationById(long id);
}
