package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.AddressRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Country;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.AddressService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressServiceModel createAddress(AddressServiceModel addressServiceModel) {
        Address address = this.modelMapper.map(addressServiceModel, Address.class);
        return this.modelMapper.map( this.addressRepository.saveAndFlush(address), AddressServiceModel.class);
    }

    @Override
    public void updateAddress(AddressServiceModel addressServiceModel) {

    }

    @Override
    public AddressServiceViewModel getAddressById(long id) {
        return this.modelMapper
                .map(this.addressRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Address  with ID %s not found.", id))), AddressServiceViewModel.class);
    }

    @Override
    public List<AddressServiceViewModel> getAllAddresses() {

        this.addressRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow((InvalidEntityException::new));

        List<Address> addresses = addressRepository.findAll();

        return modelMapper.map(addresses, new TypeToken<List<AddressServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteAddressById(long id) {

        this.addressRepository.findById(id)
                .orElseThrow((InvalidEntityException::new));

        this.addressRepository.deleteById(id);
    }
}
