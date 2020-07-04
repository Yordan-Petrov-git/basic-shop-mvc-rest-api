package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.OpinionRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.UserRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Opinion;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.UserServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OpinionService;
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
public class OpinionServiceImpl implements OpinionService {

    private final OpinionRepository opinionRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public OpinionServiceImpl(OpinionRepository opinionRepository, ModelMapper modelMapper, UserService userService, UserRepository userRepository) {
        this.opinionRepository = opinionRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public OpinionServiceViewModel createOpinion(OpinionServiceModel opinionServiceModel) {

        //No exception thrown here because one user can have multiple opinions
        Opinion opinion = this.modelMapper.map(opinionServiceModel, Opinion.class);

        //Adds user  to opinion if user exists
        UserServiceViewModel userServiceModel = this.userService.getUserById(opinionServiceModel.getUser().getId());

        userRepository.findById(opinionServiceModel.getUser().getId())
                .ifPresent(c -> {
                    opinionServiceModel.setUser(this.modelMapper.map(userServiceModel, UserServiceModel.class));
                });



        return this.modelMapper.map(this.opinionRepository.saveAndFlush(opinion), OpinionServiceViewModel.class);

    }

    @Override
    @Transactional
    public OpinionServiceViewModel updateOpinion(OpinionServiceModel opinionServiceModel) {

        Opinion opinion = this.modelMapper.map(opinionServiceModel, Opinion.class);

        this.opinionRepository.findById(opinionServiceModel.getId())
                .orElseThrow(() -> new InvalidEntityException(String.format("Opinion with id '%d' not found .", opinionServiceModel.getId())));


        return this.modelMapper.map(this.opinionRepository.saveAndFlush(opinion), OpinionServiceViewModel.class);

    }

    @Override
    public OpinionServiceViewModel getOpinionById(long id) {

        return this.modelMapper
                .map(this.opinionRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Opinion  with ID %s not found.", id))), OpinionServiceViewModel.class);

    }

    @Override
    public List<OpinionServiceViewModel> getAllOpinions() {

        this.opinionRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Opinions were found"));

        List<Opinion> opinions = opinionRepository.findAll();

        return modelMapper.map(opinions, new TypeToken<List<OpinionServiceViewModel>>() {
        }.getType());
    }

    @Override
    public OpinionServiceViewModel deleteOpinionById(long id) {

        OpinionServiceViewModel opinionServiceViewModel = this.getOpinionById(id);

        this.opinionRepository.deleteById(id);

        return opinionServiceViewModel;
    }
}
