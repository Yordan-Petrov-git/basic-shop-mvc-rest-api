package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.OpinionDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.UserDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Opinion;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OpinionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OpinionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.UserServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.UserServiceViewModel;
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

    private final OpinionDao opinionDao;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final UserDao userDao;

    @Autowired
    public OpinionServiceImpl(OpinionDao opinionDao, ModelMapper modelMapper, UserService userService,
                              UserDao userDao) {
        this.opinionDao = opinionDao;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userDao = userDao;
    }

    @Override
    public OpinionServiceViewModel createOpinion(OpinionServiceModel opinionServiceModel) {
        Opinion opinion = mapOpinionServiceModelToOpinion(opinionServiceModel);
        setOpinionToUser(opinionServiceModel);
        this.opinionDao.saveAndFlush(opinion);
        return mapOpinionToOpinionServiceViewModel(opinion);
    }


    @Override
    @Transactional
    public OpinionServiceViewModel updateOpinion(OpinionServiceModel opinionServiceModel) {
        Opinion opinion = mapOpinionServiceModelToOpinion(opinionServiceModel);
        getOpinionById(opinionServiceModel.getId());
        this.opinionDao.saveAndFlush(opinion);
        return mapOpinionToOpinionServiceViewModel(opinion);
    }

    public OpinionServiceViewModel mapOpinionToOpinionServiceViewModel(Opinion opinion) {
        return this.modelMapper.map(opinion, OpinionServiceViewModel.class);
    }

    @Override
    public OpinionServiceViewModel getOpinionById(long id) {
        return this.modelMapper
                .map(this.opinionDao.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Opinion  with ID %s not found.", id))), OpinionServiceViewModel.class);
    }

    @Override
    public List<OpinionServiceViewModel> getAllOpinions() {
        this.opinionDao.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Opinions were found"));
        List<Opinion> opinions = opinionDao.findAll();
        return modelMapper.map(opinions, new TypeToken<List<OpinionServiceViewModel>>() {
        }.getType());
    }

    @Override
    public OpinionServiceViewModel deleteOpinionById(long id) {
        OpinionServiceViewModel opinionServiceViewModel = this.getOpinionById(id);
        this.opinionDao.deleteById(id);
        return opinionServiceViewModel;
    }

    private void setOpinionToUser(OpinionServiceModel opinionServiceModel) {
        userDao.findById(opinionServiceModel.getUser().getId())
                .ifPresent(c -> {
                    opinionServiceModel.setUser(this.modelMapper.map(getUserServiceViewModel(opinionServiceModel), UserServiceModel.class));
                });
    }

    private UserServiceViewModel getUserServiceViewModel(OpinionServiceModel opinionServiceModel) {
        return this.userService.getUserById(opinionServiceModel.getUser().getId());
    }

    public Opinion mapOpinionServiceModelToOpinion(OpinionServiceModel opinionServiceModel) {
        return this.modelMapper.map(opinionServiceModel, Opinion.class);
    }

}
