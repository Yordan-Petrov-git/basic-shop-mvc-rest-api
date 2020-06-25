package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.OpinionRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OpinionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public void updateOpinion(OpinionServiceModel opinionServiceModel) {

    }

    @Override
    public OpinionServiceViewModel getOpinionById(long id) {
        return null;
    }

    @Override
    public List<OpinionServiceViewModel> getAllOpinions() {
        return null;
    }

    @Override
    public void deleteOpinionById(long id) {

    }
}
