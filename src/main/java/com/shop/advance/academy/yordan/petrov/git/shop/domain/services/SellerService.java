package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.SellerServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.SellerServiceViewModel;
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
public interface SellerService {

    SellerServiceViewModel createSeller(SellerServiceModel sellerServiceModel);

    SellerServiceViewModel updateSeller(SellerServiceModel sellerServiceModel);

    SellerServiceViewModel getSellerById(long id);

    List<SellerServiceViewModel> getAllSellers();

    SellerServiceViewModel deleteSellerById(long id);
}
