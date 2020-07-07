package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.SellerRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Seller;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.SellerServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.SellerService;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.UserService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;


    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ModelMapper modelMapper, UserService userService) {
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @Override
    public SellerServiceViewModel createSeller(SellerServiceModel sellerServiceModel) {

        Seller seller = this.modelMapper.map(sellerServiceModel, Seller.class);

        this.sellerRepository.findByName(sellerServiceModel.getName()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Seller with name '%s' already exists.", sellerServiceModel.getName()));
        });

        //Set user account  to seller
        this.sellerRepository.findByUser(this.modelMapper.map(sellerServiceModel.getUser(), User.class))
                .ifPresent(c -> {
                    throw new InvalidEntityException(String.format("User with id : '%s' is  already a seller!", sellerServiceModel.getUser().getId()));
                });

        seller.setUser(this.modelMapper.map(this.userService
                .getUserById(sellerServiceModel.getUser().getId()), User.class));


        return this.modelMapper.map(this.sellerRepository.saveAndFlush(seller), SellerServiceViewModel.class);

    }

    @Override
    @Transactional
    public SellerServiceViewModel updateSeller(SellerServiceModel sellerServiceModel) {

        Seller seller = this.modelMapper.map(sellerServiceModel, Seller.class);

        this.sellerRepository.findById(sellerServiceModel.getId())
                .orElseThrow(() -> new InvalidEntityException(String.format("Seller with id '%d' not found .", sellerServiceModel.getId())));

        return this.modelMapper.map(this.sellerRepository.saveAndFlush(seller), SellerServiceViewModel.class);

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
    public SellerServiceViewModel deleteSellerById(long id) {

        SellerServiceViewModel sellerServiceViewModel = this.getSellerById(id);

        this.sellerRepository.deleteById(id);

        return sellerServiceViewModel;

    }
}
