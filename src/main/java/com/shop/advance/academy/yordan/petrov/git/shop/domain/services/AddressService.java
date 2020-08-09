package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.AddressServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.AddressServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface AddressService {

    AddressServiceViewModel createAddress(AddressServiceModel addressServiceModel);

    AddressServiceViewModel updateAddress(AddressServiceModel addressServiceModel);

    AddressServiceViewModel getAddressById(long id);

    List<AddressServiceViewModel> getAllAddresses();

    AddressServiceViewModel deleteAddressById(long id);
}
