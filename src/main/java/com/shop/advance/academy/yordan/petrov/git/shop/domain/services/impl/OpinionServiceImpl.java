package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.OpinionRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Opinion;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OpinionService;
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

    @Autowired
    public OpinionServiceImpl(OpinionRepository opinionRepository, ModelMapper modelMapper) {
        this.opinionRepository = opinionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OpinionServiceViewModel createOpinion(OpinionServiceModel opinionServiceModel) {

        //No exception thrown here because one user can have multiple opinions
        Opinion opinion = this.modelMapper.map(opinionServiceModel, Opinion.class);

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
