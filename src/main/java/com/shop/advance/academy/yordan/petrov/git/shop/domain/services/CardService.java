package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CardServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CardServiceViewModel;
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
public interface CardService {

    /**
     * @param cardServiceModel
     * @return
     */
    CardServiceViewModel createCard(CardServiceModel cardServiceModel);

    /**
     * @param cardServiceModel
     * @return
     */
    CardServiceViewModel updateCard(CardServiceModel cardServiceModel);

    /**
     * @param id
     * @return
     */
    CardServiceViewModel getCardById(long id);

    /**
     * @return
     */
    List<CardServiceViewModel> getAllCards();

    /**
     * @param id
     * @return
     */
    CardServiceViewModel deleteCardById(long id);
}
