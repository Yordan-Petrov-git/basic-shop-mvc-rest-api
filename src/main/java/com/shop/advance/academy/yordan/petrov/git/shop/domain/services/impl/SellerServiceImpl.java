package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.SellerDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Seller;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.User;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.SellerServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.SellerServiceViewModel;
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
/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public class SellerServiceImpl implements SellerService {

    private final SellerDao sellerDao;
    private final ModelMapper modelMapper;
    private final UserService userService;

    /**
     * Constructor
     */
    @Autowired
    public SellerServiceImpl(SellerDao sellerDao, ModelMapper modelMapper, UserService userService) {
        this.sellerDao = sellerDao;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    /**
     * @param sellerServiceModel
     * @return
     */
    @Override
    public SellerServiceViewModel createSeller(SellerServiceModel sellerServiceModel) {

        Seller seller = this.modelMapper.map(sellerServiceModel, Seller.class);

        this.sellerDao.findByName(sellerServiceModel.getName()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Seller with name '%s' already exists.", sellerServiceModel.getName()));
        });

        //Set user account  to seller
        this.sellerDao.findByUser(this.modelMapper.map(sellerServiceModel.getUser(), User.class))
                .ifPresent(c -> {
                    throw new InvalidEntityException(String.format("User with id : '%s' is  already a seller!", sellerServiceModel.getUser().getId()));
                });

        seller.setUser(this.modelMapper.map(this.userService
                .getUserById(sellerServiceModel.getUser().getId()), User.class));


        return this.modelMapper.map(this.sellerDao.saveAndFlush(seller), SellerServiceViewModel.class);

    }

    /**
     * @param sellerServiceModel
     * @return
     */
    @Override
    @Transactional
    public SellerServiceViewModel updateSeller(SellerServiceModel sellerServiceModel) {

        Seller seller = this.modelMapper.map(sellerServiceModel, Seller.class);

        this.sellerDao.findById(sellerServiceModel.getId())
                .orElseThrow(() -> new InvalidEntityException(String.format("Seller with id '%d' not found .", sellerServiceModel.getId())));

        return this.modelMapper.map(this.sellerDao.saveAndFlush(seller), SellerServiceViewModel.class);

    }

    /**
     * @param id
     * @return
     */
    @Override
    public SellerServiceViewModel getSellerById(long id) {
        return this.modelMapper
                .map(this.sellerDao.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Seller  with ID %s not found.", id))), SellerServiceViewModel.class);

    }

    /**
     * @return
     */
    @Override
    public List<SellerServiceViewModel> getAllSellers() {

        this.sellerDao.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Sellers were found"));

        List<Seller> sellers = sellerDao.findAll();

        return modelMapper.map(sellers, new TypeToken<List<SellerServiceViewModel>>() {
        }.getType());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public SellerServiceViewModel deleteSellerById(long id) {

        SellerServiceViewModel sellerServiceViewModel = this.getSellerById(id);

        this.sellerDao.deleteById(id);

        return sellerServiceViewModel;

    }
}
