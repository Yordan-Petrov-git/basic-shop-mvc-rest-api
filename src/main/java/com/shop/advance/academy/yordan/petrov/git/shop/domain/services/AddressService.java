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

    /**
     * @param addressServiceModel
     * @return
     */
    AddressServiceViewModel createAddress(AddressServiceModel addressServiceModel);

    /**
     * @param addressServiceModel
     * @return
     */
    AddressServiceViewModel updateAddress(AddressServiceModel addressServiceModel);

    /**
     * @param id
     * @return
     */
    AddressServiceViewModel getAddressById(long id);

    /**
     * @return
     */
    List<AddressServiceViewModel> getAllAddresses();

    /**
     * @param id
     * @return
     */
    AddressServiceViewModel deleteAddressById(long id);
}
