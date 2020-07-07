package com.shop.advance.academy.yordan.petrov.git.shop.rest.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CardServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CardServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/card")
@Slf4j
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping()
    public ResponseEntity<CardServiceViewModel> createCard(@RequestBody CardServiceModel cardServiceModel) {

        CardServiceViewModel cardServiceViewModel = cardService.createCard(cardServiceModel);

        log.info("Card  created : {}", cardServiceViewModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(cardServiceViewModel);

    }

    @PutMapping()
    public ResponseEntity<CardServiceViewModel> updateCard(@RequestBody CardServiceModel cardServiceModel) {

        CardServiceViewModel cardServiceViewModel = cardService.updateCard(cardServiceModel);

        log.info("Card  updated : {}", cardServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(cardServiceViewModel);

    }


    @PatchMapping()
    public ResponseEntity<CardServiceViewModel> partialUpdateCard(@RequestBody CardServiceModel cardServiceModel) {

        CardServiceViewModel cardServiceViewModel = cardService.updateCard(cardServiceModel);

        log.info("Card  updated : {}", cardServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(cardServiceViewModel);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CardServiceViewModel> getCard(@PathVariable("id") final Long id) {

        CardServiceViewModel cardServiceViewModel = cardService.getCardById(id);

        log.info("Card  found : {}", cardServiceViewModel);

        return ResponseEntity.status(HttpStatus.FOUND).body(cardServiceViewModel);
    }

    @GetMapping()
    public ResponseEntity<List<CardServiceViewModel>> getCards() {

        List<CardServiceViewModel> cardServiceViewModel = cardService.getAllCards();

        log.info("CardS  found : {}", cardServiceViewModel);

        return ResponseEntity.status(HttpStatus.FOUND).body(cardServiceViewModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CardServiceViewModel> deleteCard(@PathVariable("id") Long id) {

        CardServiceViewModel cardServiceViewModel = cardService.deleteCardById(id);

        log.info("Card deleted : {}", cardServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(cardServiceViewModel);

    }


}
