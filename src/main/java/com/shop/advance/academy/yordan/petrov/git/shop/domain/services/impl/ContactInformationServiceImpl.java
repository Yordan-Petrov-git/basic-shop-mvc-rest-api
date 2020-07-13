package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.repository.ContactInformationRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ContactInformation;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ContactInformationServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ContactInformationService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
    public ContactInformationServiceViewModel createContactInformation(ContactInformationServiceModel contactInformationServiceModel) {
        ContactInformation contactInformation = mapContactInformationServiceModelToContactInformation(contactInformationServiceModel);
        findByEmail(contactInformationServiceModel);
        this.contactInformationRepository.findByCountryCodeAndPhoneNumber(contactInformationServiceModel.getCountryCode(), contactInformationServiceModel.getPhoneNumber()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Contact information with phone number '%s','%s' already exists.", contactInformation.getCountryCode(), contactInformationServiceModel.getPhoneNumber()));
        });
        this.contactInformationRepository.saveAndFlush(contactInformation);
        return mapContactInformationToContactInformationServiceViewModel(contactInformation);
    }


    @Override
    @Transactional
    public ContactInformationServiceViewModel updateContactInformation(ContactInformationServiceModel contactInformationServiceModel) {
        ContactInformation contactInformation = mapContactInformationServiceModelToContactInformation(contactInformationServiceModel);
        getContactInformationById(contactInformationServiceModel.getId());
        this.contactInformationRepository.saveAndFlush(contactInformation);
        return mapContactInformationToContactInformationServiceViewModel(contactInformation);
    }

    private ContactInformationServiceViewModel mapContactInformationToContactInformationServiceViewModel(ContactInformation contactInformation) {
        return this.modelMapper.map(contactInformation, ContactInformationServiceViewModel.class);
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

        List<ContactInformation> contactInformation = contactInformationRepository.findAll();

        return modelMapper.map(contactInformation, new TypeToken<List<ContactInformationServiceViewModel>>() {
        }.getType());

    }

    @Override
    public ContactInformationServiceViewModel deleteContactInformationById(long id) {
        ContactInformationServiceViewModel contactInformationServiceViewModel = this.getContactInformationById(id);
        this.contactInformationRepository.deleteById(id);
        return contactInformationServiceViewModel;
    }


    private void findByEmail(ContactInformationServiceModel contactInformationServiceModel) {
        this.contactInformationRepository.findByEmail(contactInformationServiceModel.getEmail()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Contact information with email '%s' already exists.", contactInformationServiceModel.getEmail()));
        });
    }

    private ContactInformation mapContactInformationServiceModelToContactInformation(ContactInformationServiceModel contactInformationServiceModel) {
        return this.modelMapper.map(contactInformationServiceModel, ContactInformation.class);
    }
}
