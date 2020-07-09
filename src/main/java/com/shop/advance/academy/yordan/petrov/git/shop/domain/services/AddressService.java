package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.AddressServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.AddressServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    AddressServiceViewModel createAddress(AddressServiceModel addressServiceModel);

    AddressServiceViewModel updateAddress(AddressServiceModel addressServiceModel);

    AddressServiceViewModel getAddressById(long id);

    List<AddressServiceViewModel> getAllAddresses();

    AddressServiceViewModel deleteAddressById(long id);
}
