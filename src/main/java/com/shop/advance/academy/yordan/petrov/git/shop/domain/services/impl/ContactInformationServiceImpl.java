package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.ContactInformationRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ContactInformation;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ContactInformationService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import lombok.AllArgsConstructor;
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
        return this.modelMapper.map( this.contactInformationRepository.saveAndFlush(contactInformation), ContactInformationServiceModel.class);
    }

    @Override
    public void updateContactInformation(ContactInformationServiceModel ContactInformation) {

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
                .orElseThrow((InvalidEntityException::new));

        List<ContactInformation> contactInformations = contactInformationRepository.findAll();

        return modelMapper.map(contactInformations, new TypeToken<List<ContactInformationServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteContactInformationById(long id) {

        this.contactInformationRepository.findById(id)
                .orElseThrow((InvalidEntityException::new));

        this.contactInformationRepository.deleteById(id);
    }
}
