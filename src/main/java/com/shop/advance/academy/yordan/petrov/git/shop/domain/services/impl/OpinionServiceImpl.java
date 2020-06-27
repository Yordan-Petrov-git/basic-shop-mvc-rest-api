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
    public OpinionServiceModel createOpinion(OpinionServiceModel opinionServiceModel) {
        Opinion opinion = this.modelMapper.map(opinionServiceModel, Opinion.class);
        return this.modelMapper.map( this.opinionRepository.saveAndFlush(opinion), OpinionServiceModel.class);
    }

    @Override
    public void updateOpinion(OpinionServiceModel opinionServiceModel) {
        Opinion opinion = this.modelMapper.map(opinionServiceModel, Opinion.class);
         this.modelMapper.map( this.opinionRepository.saveAndFlush(opinion), OpinionServiceModel.class);
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
    public void deleteOpinionById(long id) {

        this.opinionRepository.findById(id)
                .orElseThrow(() -> new InvalidEntityException(String.format("Opinion  with id '%d' not found .", id)));

        this.opinionRepository.deleteById(id);
    }
}
