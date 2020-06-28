package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SellerService {

    SellerServiceModel createSeller(SellerServiceModel sellerServiceModel);

    SellerServiceViewModel updateSeller(SellerServiceModel sellerServiceModel);

    SellerServiceViewModel getSellerById(long id);

    List<SellerServiceViewModel> getAllSellers();

    void deleteSellerById(long id);



}
