package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.SellerRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Seller;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.AddressServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ShoppingCartServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.SellerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
        Seller seller = this.modelMapper.map(sellerServiceModel, Seller.class);
        return this.modelMapper.map( this.sellerRepository.saveAndFlush(seller), SellerServiceModel.class);
    }

    @Override
    public void updateSeller(SellerServiceModel sellerServiceModel) {

    }

    @Override
    public SellerServiceViewModel getSellerById(long id) {
        return this.modelMapper
                .map(this.sellerRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Seller  with ID %s not found.", id))), SellerServiceViewModel.class);

    }

    @Override
    public List<SellerServiceViewModel> getAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();

        return modelMapper.map(sellers, new TypeToken<List<SellerServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteSellerById(long id) {
        sellerRepository.deleteById(id);
    }
}
