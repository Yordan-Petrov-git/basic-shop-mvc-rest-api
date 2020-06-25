package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ContactInformationRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ContactInformationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactInformationServiceImpl implements ContactInformationService {

    private final ContactInformationRepository contactInformationRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactInformationServiceImpl(ContactInformationRepository contactInformationRepository, ModelMapper modelMapper) {
        this.contactInformationRepository = contactInformationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContactInformationServiceModel createContactInformation(ContactInformationServiceModel ContactInformation) {
        return null;
    }

    @Override
    public void updateContactInformation(ContactInformationServiceModel ContactInformation) {

    }

    @Override
    public ContactInformationServiceViewModel getContactInformationById(long id) {
        return null;
    }

    @Override
    public List<ContactInformationServiceViewModel> getAllContactInformations() {
        return null;
    }

    @Override
    public void deleteContactInformationById(long id) {

    }
}
