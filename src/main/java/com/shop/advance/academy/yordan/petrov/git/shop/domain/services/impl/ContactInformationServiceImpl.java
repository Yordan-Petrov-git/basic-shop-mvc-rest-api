package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ContactInformationRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ContactInformation;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ContactInformationService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public ContactInformationServiceModel createContactInformation(ContactInformationServiceModel contactInformationServiceModel) {

        ContactInformation contactInformation = this.modelMapper.map(contactInformationServiceModel, ContactInformation.class);

        this.contactInformationRepository.findByEmail(contactInformationServiceModel.getEmail()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Contact information with email '%s' already exists.", contactInformationServiceModel.getEmail()));
        });


        this.contactInformationRepository.findByCountryCodeAndPhoneNumber(contactInformationServiceModel.getCountryCode(), contactInformationServiceModel.getPhoneNumber()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Contact information with phone number '%s','%s' already exists.", contactInformation.getCountryCode(), contactInformationServiceModel.getPhoneNumber()));
        });


        return this.modelMapper.map(this.contactInformationRepository.saveAndFlush(contactInformation), ContactInformationServiceModel.class);

    }

    @Override
    public void updateContactInformation(ContactInformationServiceModel ContactInformation) {

        ContactInformation contactInformation = this.modelMapper.map(ContactInformation, ContactInformation.class);

        this.contactInformationRepository.findById(ContactInformation.getId())
                .orElseThrow(() -> new InvalidEntityException(String.format("Contact information with id '%d' not found .", ContactInformation.getId())));

        this.modelMapper.map(this.contactInformationRepository.saveAndFlush(contactInformation), ContactInformationServiceModel.class);

    }

    @Override
    public ContactInformationServiceViewModel getContactInformationById(long id) {

        return this.modelMapper
                .map(this.contactInformationRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Contact Information  with ID %s not found.", id))), ContactInformationServiceViewModel.class);

    }

    @Override
    public List<ContactInformationServiceViewModel> getAllContactInformations() {

        this.contactInformationRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Contact information was found"));

        List<ContactInformation> contactInformations = contactInformationRepository.findAll();

        return modelMapper.map(contactInformations, new TypeToken<List<ContactInformationServiceViewModel>>() {
        }.getType());

    }

    @Override
    public void deleteContactInformationById(long id) {

        this.contactInformationRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Contact information  with id '%d' not found .", id)));

        this.contactInformationRepository.deleteById(id);

    }
}
