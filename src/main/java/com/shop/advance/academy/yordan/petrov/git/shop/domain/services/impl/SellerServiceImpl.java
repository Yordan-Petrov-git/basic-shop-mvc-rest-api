package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.SellerRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Seller;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.SellerService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
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

        this.sellerRepository.findByName(sellerServiceModel.getName()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Seller with name '%s' already exists.", sellerServiceModel.getName()));
        });

        return this.modelMapper.map(this.sellerRepository.saveAndFlush(seller), SellerServiceModel.class);

    }

    @Override
    public void updateSeller(SellerServiceModel sellerServiceModel) {

        Seller seller = this.modelMapper.map(sellerServiceModel, Seller.class);

        this.sellerRepository.findById(sellerServiceModel.getId())
                .orElseThrow(() -> new InvalidEntityException(String.format("Seller with id '%d' not found .", sellerServiceModel.getId())));

        this.modelMapper.map(this.sellerRepository.saveAndFlush(seller), SellerServiceModel.class);

    }

    @Override
    public SellerServiceViewModel getSellerById(long id) {
        return this.modelMapper
                .map(this.sellerRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Seller  with ID %s not found.", id))), SellerServiceViewModel.class);

    }

    @Override
    public List<SellerServiceViewModel> getAllSellers() {

        this.sellerRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Sellers were found"));

        List<Seller> sellers = sellerRepository.findAll();

        return modelMapper.map(sellers, new TypeToken<List<SellerServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteSellerById(long id) {

        this.sellerRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Seller  with id '%d' not found .", id)));

        this.sellerRepository.deleteById(id);
    }
}
