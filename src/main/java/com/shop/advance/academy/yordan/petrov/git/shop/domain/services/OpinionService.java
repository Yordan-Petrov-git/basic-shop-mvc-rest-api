package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OpinionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OpinionServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface OpinionService {

    OpinionServiceViewModel createOpinion(OpinionServiceModel opinionServiceModel);

    OpinionServiceViewModel updateOpinion(OpinionServiceModel opinionServiceModel);

    OpinionServiceViewModel getOpinionById(long id);

    List<OpinionServiceViewModel> getAllOpinions();

    OpinionServiceViewModel deleteOpinionById(long id);
}
