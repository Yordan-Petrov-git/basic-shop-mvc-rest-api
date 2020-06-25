package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CardServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.CardServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/card")
public class CardController {

    private final CardService cardService;

     @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<CardServiceModel> createCard(@RequestBody CardServiceModel cardServiceModel) {
        cardService.createCard(cardServiceModel);
        return new ResponseEntity<>(cardServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateCard(@PathVariable("id") Long id,@RequestBody CardServiceModel cardServiceModel) {
        cardService.updateCard(cardServiceModel);
    }


    @GetMapping("/{id}")
    public CardServiceViewModel getCard(@PathVariable("id")final Long id) {
        return cardService.getCardById(id);
    }

    @GetMapping()
    public List<CardServiceViewModel> getCards() {
        return cardService.getAllCards();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCard(@PathVariable("id") Long id) {
        cardService.deleteCardById(id);
    }


}
