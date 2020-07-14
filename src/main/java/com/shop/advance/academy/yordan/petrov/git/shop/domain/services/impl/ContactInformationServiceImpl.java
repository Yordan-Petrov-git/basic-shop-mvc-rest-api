package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ContactInformationDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ContactInformation;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ContactInformationServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ContactInformationServiceViewModel;
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

    private final ContactInformationDao contactInformationDao;
    private final ModelMapper modelMapper;

    @Autowired
    public ContactInformationServiceImpl(ContactInformationDao contactInformationDao, ModelMapper modelMapper) {
        this.contactInformationDao = contactInformationDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContactInformationServiceViewModel createContactInformation(ContactInformationServiceModel contactInformationServiceModel) {
        ContactInformation contactInformation = mapContactInformationServiceModelToContactInformation(contactInformationServiceModel);
        findByEmail(contactInformationServiceModel);
        this.contactInformationDao.findByCountryCodeAndPhoneNumber(contactInformationServiceModel.getCountryCode(), contactInformationServiceModel.getPhoneNumber()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Contact information with phone number '%s','%s' already exists.", contactInformation.getCountryCode(), contactInformationServiceModel.getPhoneNumber()));
        });
        this.contactInformationDao.saveAndFlush(contactInformation);
        return mapContactInformationToContactInformationServiceViewModel(contactInformation);
    }


    @Override
    @Transactional
    public ContactInformationServiceViewModel updateContactInformation(ContactInformationServiceModel contactInformationServiceModel) {
        ContactInformation contactInformation = mapContactInformationServiceModelToContactInformation(contactInformationServiceModel);
        getContactInformationById(contactInformationServiceModel.getId());
        this.contactInformationDao.saveAndFlush(contactInformation);
        return mapContactInformationToContactInformationServiceViewModel(contactInformation);
    }

    private ContactInformationServiceViewModel mapContactInformationToContactInformationServiceViewModel(ContactInformation contactInformation) {
        return this.modelMapper.map(contactInformation, ContactInformationServiceViewModel.class);
    }

    @Override
    public ContactInformationServiceViewModel getContactInformationById(long id) {

        return this.modelMapper
                .map(this.contactInformationDao.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Contact Information  with ID %s not found.", id))), ContactInformationServiceViewModel.class);

    }

    @Override
    public List<ContactInformationServiceViewModel> getAllContactInformations() {

        this.contactInformationDao.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Contact information was found"));

        List<ContactInformation> contactInformation = contactInformationDao.findAll();

        return modelMapper.map(contactInformation, new TypeToken<List<ContactInformationServiceViewModel>>() {
        }.getType());

    }

    @Override
    public ContactInformationServiceViewModel deleteContactInformationById(long id) {
        ContactInformationServiceViewModel contactInformationServiceViewModel = this.getContactInformationById(id);
        this.contactInformationDao.deleteById(id);
        return contactInformationServiceViewModel;
    }


    private void findByEmail(ContactInformationServiceModel contactInformationServiceModel) {
        this.contactInformationDao.findByEmail(contactInformationServiceModel.getEmail()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Contact information with email '%s' already exists.", contactInformationServiceModel.getEmail()));
        });
    }

    private ContactInformation mapContactInformationServiceModelToContactInformation(ContactInformationServiceModel contactInformationServiceModel) {
        return this.modelMapper.map(contactInformationServiceModel, ContactInformation.class);
    }
}
