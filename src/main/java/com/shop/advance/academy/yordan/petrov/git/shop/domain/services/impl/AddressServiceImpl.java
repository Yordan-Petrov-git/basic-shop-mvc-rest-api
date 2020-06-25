package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.AddressRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.AddressServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.AddressServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public void updateAddress(AddressServiceModel addressServiceModel) {

    }

    @Override
    public AddressServiceViewModel getAddressById(long id) {
        return null;
    }

    @Override
    public List<AddressServiceViewModel> getAllAddresses() {
        return null;
    }

    @Override
    public void deleteAddressById(long id) {

    }
}
