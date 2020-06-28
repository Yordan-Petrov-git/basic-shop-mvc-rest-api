package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.AddressRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CityRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.City;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.AddressService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CityService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {


    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CityService cityService;
    private final ModelMapper modelMapper;


    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, CityRepository cityRepository, CityService cityService, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressServiceViewModel createAddress(AddressServiceModel addressServiceModel) {
//Create address only if the city is alredy in the database
        Address address = this.modelMapper.map(addressServiceModel, Address.class);

        this.addressRepository.findByStreetNumberAndStreetName(addressServiceModel.getStreetNumber(), addressServiceModel.getStreetName())
                .ifPresent(c -> {
                    throw new InvalidEntityException(
                            String.format("Address with number '%s' and street '%s' in city '%s' already exists.",
                                    addressServiceModel.getStreetNumber(),
                                    addressServiceModel.getStreetName(),
                                    addressServiceModel.getCity().getName()));
                });

        CityServiceViewModel cityServiceViewModel = this.cityService.getCityByName(addressServiceModel.getCity().getName());

        cityRepository.findCityByName(addressServiceModel.getCity().getName())
                .ifPresent(c -> {
                    address.setCity(this.modelMapper.map(cityServiceViewModel, City.class));
                });


        return this.modelMapper.map(this.addressRepository.saveAndFlush(address), AddressServiceViewModel.class);
    }

    @Override
    @Transactional
    public AddressServiceViewModel updateAddress(AddressServiceModel addressServiceModel) {

        Address address = this.modelMapper.map(addressServiceModel, Address.class);


        return this.modelMapper.map(this.addressRepository.saveAndFlush(address), AddressServiceViewModel.class);

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
                .orElseThrow(() -> new InvalidEntityException("No Addresses were found"));

        List<Address> addresses = addressRepository.findAll();

        return modelMapper.map(addresses, new TypeToken<List<AddressServiceViewModel>>() {
        }.getType());

    }

    @Override
    public void deleteAddressById(long id) {

        this.addressRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Address  with id '%d' not found .", id)));

        this.addressRepository.deleteById(id);

    }

}
