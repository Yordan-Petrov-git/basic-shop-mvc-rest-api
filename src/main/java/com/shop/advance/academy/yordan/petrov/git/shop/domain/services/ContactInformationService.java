package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactInformationService {

    ContactInformationServiceViewModel createContactInformation(ContactInformationServiceModel ContactInformation);

    ContactInformationServiceViewModel updateContactInformation(ContactInformationServiceModel ContactInformation);

    ContactInformationServiceViewModel getContactInformationById(long id);

    List<ContactInformationServiceViewModel> getAllContactInformations();

    ContactInformationServiceViewModel deleteContactInformationById(long id);


}
