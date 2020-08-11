package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CardServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.CardServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Class controller for the card.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/card")
@Slf4j
public class CardController {

    private final CardService cardService;

    /**
     * Constructor
     */
    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    /**
     * Method for
     *
     * @param cardServiceModel
     * @return
     */
    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CardServiceViewModel> createCard(@RequestBody CardServiceModel cardServiceModel) {
        CardServiceViewModel cardServiceViewModel = cardService.createCard(cardServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(CardController.class, "createCard", CardServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(cardServiceViewModel.getId()).toUri();
        log.info("Card  created : {} {}", cardServiceViewModel, location);
        return ResponseEntity.created(location).body(cardServiceViewModel);
    }

    /**
     * Method for
     *
     * @param cardServiceModel
     * @return
     */
    @PutMapping()
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CardServiceViewModel> updateCard(@RequestBody CardServiceModel cardServiceModel) {
        CardServiceViewModel cardServiceViewModel = cardService.updateCard(cardServiceModel);
        log.info("Card  updated : {}", cardServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(cardServiceViewModel);

    }


    /**
     * Method for
     *
     * @param cardServiceModel
     * @return
     */
    @PatchMapping()
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CardServiceViewModel> partialUpdateCard(@RequestBody CardServiceModel cardServiceModel) {
        CardServiceViewModel cardServiceViewModel = cardService.updateCard(cardServiceModel);
        log.info("Card  updated : {}", cardServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(cardServiceViewModel);
    }

    /**
     * Method for
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<CardServiceViewModel> getCard(@PathVariable("id") final Long id) {
        CardServiceViewModel cardServiceViewModel = cardService.getCardById(id);
        log.info("Card  found : {}", cardServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(cardServiceViewModel);
    }

    /**
     * Method for
     *
     * @return
     */
    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<List<CardServiceViewModel>> getCards() {
        List<CardServiceViewModel> cardServiceViewModel = cardService.getAllCards();
        log.info("CardS  found : {}", cardServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(cardServiceViewModel);
    }

    /**
     * Method for
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CardServiceViewModel> deleteCard(@PathVariable("id") Long id) {
        CardServiceViewModel cardServiceViewModel = cardService.deleteCardById(id);
        log.info("Card deleted : {}", cardServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(cardServiceViewModel);
    }


}
