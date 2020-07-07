package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.AddressRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CityRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.City;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.AddressServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.AddressServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CityServiceViewModel;
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
    public AddressServiceImpl(AddressRepository addressRepository, CityRepository cityRepository,
                              CityService cityService, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.cityRepository = cityRepository;
        this.cityService = cityService;
        this.modelMapper = modelMapper;
    }

    @Override
    public AddressServiceViewModel createAddress(AddressServiceModel addressServiceModel) {
        Address address = mapAddressServiceModelToAddress(addressServiceModel);
        validateIfAddressExists(addressServiceModel);
        addressSetCity(addressServiceModel, address);
        return mapAddressToAddressServiceViewModel(address);
    }

    @Override
    @Transactional
    public AddressServiceViewModel updateAddress(AddressServiceModel addressServiceModel) {
        return mapAddressToAddressServiceViewModel(mapAddressServiceModelToAddress(addressServiceModel));
    }

    @Override
    public AddressServiceViewModel getAddressById(long id) {
        return mapAddressToAddressServiceViewModel(findAddressByIdFromRepository(id));
    }


    @Override
    public List<AddressServiceViewModel> getAllAddresses() {
        validateIfFoundAnyAddresses();
        return mapAddressListToAddressServiceViewModelList(findAllAddressesFromRepository());
    }

    @Override
    public AddressServiceViewModel deleteAddressById(long id) {
        AddressServiceViewModel addressServiceViewModel = this.getAddressById(id);
        this.addressRepository.deleteAddressById(id);
        return addressServiceViewModel;
    }

    public AddressServiceViewModel mapAddressToAddressServiceViewModel(Address address) {
        return this.modelMapper.map(this.addressRepository.saveAndFlush(address), AddressServiceViewModel.class);
    }

    public Address mapAddressServiceModelToAddress(AddressServiceModel addressServiceModel) {
        return this.modelMapper.map(addressServiceModel, Address.class);
    }

    public List<Address> findAllAddressesFromRepository() {
        return addressRepository.findAll();
    }

    public void validateIfFoundAnyAddresses() {
        this.addressRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Addresses were found"));
    }

    public List<AddressServiceViewModel> mapAddressListToAddressServiceViewModelList(List<Address> addresses) {
        return modelMapper.map(addresses, new TypeToken<List<AddressServiceViewModel>>() {
        }.getType());
    }

    public Address findAddressByIdFromRepository(long id) {
        return this.addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Address with ID %s not found.", id)));
    }

    public void addressSetCity(AddressServiceModel addressServiceModel, Address address) {
        cityRepository.findCityByName(addressServiceModel.getCity().getName())
                .ifPresent(c -> {
                    address.setCity(this.modelMapper.map(findCityByName(addressServiceModel), City.class));
                });
    }

    public CityServiceViewModel findCityByName(AddressServiceModel addressServiceModel) {
        return this.cityService.getCityByName(addressServiceModel.getCity().getName());
    }

    public void validateIfAddressExists(AddressServiceModel addressServiceModel) {
        this.addressRepository.findByStreetNumberAndStreetName(addressServiceModel.getStreetNumber(), addressServiceModel.getStreetName())
                .ifPresent(c -> {
                    throw new InvalidEntityException(
                            String.format("Address with number '%s' and street '%s' in city '%s' already exists.",
                                    addressServiceModel.getStreetNumber(),
                                    addressServiceModel.getStreetName(),
                                    addressServiceModel.getCity().getName()));
                });
    }

}
