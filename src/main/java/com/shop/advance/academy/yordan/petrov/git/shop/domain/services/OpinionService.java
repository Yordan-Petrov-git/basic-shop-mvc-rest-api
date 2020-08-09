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

    /**
     * @param opinionServiceModel
     * @return
     */
    OpinionServiceViewModel createOpinion(OpinionServiceModel opinionServiceModel);

    /**
     * @param opinionServiceModel
     * @return
     */
    OpinionServiceViewModel updateOpinion(OpinionServiceModel opinionServiceModel);

    /**
     * @param id
     * @return
     */
    OpinionServiceViewModel getOpinionById(long id);

    /**
     * @return
     */
    List<OpinionServiceViewModel> getAllOpinions();

    /**
     * @param id
     * @return
     */
    OpinionServiceViewModel deleteOpinionById(long id);
}
