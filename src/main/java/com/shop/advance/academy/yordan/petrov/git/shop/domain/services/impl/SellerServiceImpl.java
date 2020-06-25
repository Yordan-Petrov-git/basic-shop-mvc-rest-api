package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.SellerRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.SellerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public SellerServiceModel createSeller(SellerServiceModel sellerServiceModel) {
        return null;
    }

    @Override
    public void updateSeller(SellerServiceModel sellerServiceModel) {

    }

    @Override
    public SellerServiceViewModel getSellerById(long id) {
        return null;
    }

    @Override
    public List<SellerServiceViewModel> getAllSellers() {
        return null;
    }

    @Override
    public void deleteSellerById(long id) {

    }
}
