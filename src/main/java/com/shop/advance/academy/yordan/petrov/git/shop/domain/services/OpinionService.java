package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OpinionService {

    OpinionServiceModel createOpinion(OpinionServiceModel opinionServiceModel);

    void updateOpinion(OpinionServiceModel opinionServiceModel);

    OpinionServiceViewModel getOpinionById(long id);

    List<OpinionServiceViewModel> getAllOpinions();

    void deleteOpinionById(long id);


}
