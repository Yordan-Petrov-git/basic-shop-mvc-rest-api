package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.AddressDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.CityDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.UserDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.City;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.AddressServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.AddressServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CityServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.AddressService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CityService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressDao addressDao;
    private final CityDao cityDao;
    private final CityService cityService;
    private final ModelMapper modelMapper;
    private final UserDao userDao;

    /**
     * Constructor
     */
    @Autowired
    public AddressServiceImpl(AddressDao addressDao, CityDao cityDao,
                              CityService cityService, ModelMapper modelMapper, UserDao userDao) {
        this.addressDao = addressDao;
        this.cityDao = cityDao;
        this.cityService = cityService;
        this.modelMapper = modelMapper;
        this.userDao = userDao;
    }

    /**
     * @param addressServiceModel
     * @return
     */
    @Override
    public AddressServiceViewModel createAddress(AddressServiceModel addressServiceModel) {
        Address address = mapAddressServiceModelToAddress(addressServiceModel);
        this.addressDao.findByStreetNumberAndStreetName(addressServiceModel.getStreetNumber(), addressServiceModel.getStreetName())
                .ifPresent(c -> {
                    throw new InvalidEntityException(
                            String.format("Address with number '%s' and street '%s' in city '%s' already exists.",
                                    addressServiceModel.getStreetNumber(),
                                    addressServiceModel.getStreetName(),
                                    addressServiceModel.getCity().getName()));
                });
        addressSetCity(addressServiceModel, address);
        address.setUsers(addUsersToAddress(addressServiceModel));
        this.addressDao.saveAndFlush(address);
        return mapAddressToAddressServiceViewModel(address);
    }

    /**
     * @param addressServiceModel
     * @return
     */
    @Transactional
    public List<User> addUsersToAddress(AddressServiceModel addressServiceModel) {
        List<User> userList = new ArrayList<>();
        addressServiceModel.getUser().forEach(u -> {
            User user = userDao.findById(u.getId())
                    .orElseThrow(InvalidEntityException::new);
            userList.add(user);
        });
        return userList;
    }

    /**
     * @param addressServiceModel
     * @return
     */
    @Override
    @Transactional
    public AddressServiceViewModel updateAddress(AddressServiceModel addressServiceModel) {
        Address address = mapAddressServiceModelToAddress(addressServiceModel);
        getAddressById(addressServiceModel.getId());
        address.setUsers(addUsersToAddress(addressServiceModel));
        this.addressDao.saveAndFlush(address);
        return mapAddressToAddressServiceViewModel(address);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AddressServiceViewModel getAddressById(long id) {
        return mapAddressToAddressServiceViewModel(findAddressByIdFromRepository(id));
    }

    /**
     * @return
     */
    @Override
    public List<AddressServiceViewModel> getAllAddresses() {
        validateIfFoundAnyAddresses();
        return mapAddressListToAddressServiceViewModelList(findAllAddressesFromRepository());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AddressServiceViewModel deleteAddressById(long id) {
        AddressServiceViewModel addressServiceViewModel = this.getAddressById(id);
        this.addressDao.deleteAddressById(id);
        return addressServiceViewModel;
    }

    /**
     * @param address
     * @return
     */
    public AddressServiceViewModel mapAddressToAddressServiceViewModel(Address address) {
        return this.modelMapper.map(address, AddressServiceViewModel.class);
    }

    /**
     * @param addressServiceModel
     * @return
     */
    public Address mapAddressServiceModelToAddress(AddressServiceModel addressServiceModel) {
        return this.modelMapper.map(addressServiceModel, Address.class);
    }

    /**
     * @return
     */
    public List<Address> findAllAddressesFromRepository() {
        return findAllAddresses();
    }

    /**
     *
     */
    public void validateIfFoundAnyAddresses() {
        findAllAddresses()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Addresses were found"));
    }

    /**
     * @return
     */
    private List<Address> findAllAddresses() {
        return this.addressDao.findAll();
    }

    /**
     * @param addresses
     * @return
     */
    public List<AddressServiceViewModel> mapAddressListToAddressServiceViewModelList(List<Address> addresses) {
        return modelMapper.map(addresses, new TypeToken<List<AddressServiceViewModel>>() {
        }.getType());
    }

    /**
     * @param id
     * @return
     */
    public Address findAddressByIdFromRepository(long id) {
        return this.addressDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Address with ID %s not found.", id)));
    }

    /**
     * @param addressServiceModel
     * @param address
     */
    public void addressSetCity(AddressServiceModel addressServiceModel, Address address) {
        cityDao.findCityByName(addressServiceModel.getCity().getName())
                .ifPresent(c -> {
                    address.setCity(this.modelMapper.map(findCityByName(addressServiceModel), City.class));
                });
    }

    /**
     * @param addressServiceModel
     * @return
     */
    public CityServiceViewModel findCityByName(AddressServiceModel addressServiceModel) {
        return this.cityService.getCityByName(addressServiceModel.getCity().getName());
    }

}
