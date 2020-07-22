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
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CardServiceViewModel> createCard(@RequestBody CardServiceModel cardServiceModel) {
        CardServiceViewModel cardServiceViewModel = cardService.createCard(cardServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(CardController.class, "createCard", CardServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(cardServiceViewModel.getId()).toUri();
        log.info("Card  created : {} {}", cardServiceViewModel, location);
        return ResponseEntity.created(location).body(cardServiceViewModel);
    }

    @PutMapping()
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CardServiceViewModel> updateCard(@RequestBody CardServiceModel cardServiceModel) {
        CardServiceViewModel cardServiceViewModel = cardService.updateCard(cardServiceModel);
        log.info("Card  updated : {}", cardServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(cardServiceViewModel);

    }


    @PatchMapping()
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CardServiceViewModel> partialUpdateCard(@RequestBody CardServiceModel cardServiceModel) {
        CardServiceViewModel cardServiceViewModel = cardService.updateCard(cardServiceModel);
        log.info("Card  updated : {}", cardServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(cardServiceViewModel);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<CardServiceViewModel> getCard(@PathVariable("id") final Long id) {
        CardServiceViewModel cardServiceViewModel = cardService.getCardById(id);
        log.info("Card  found : {}", cardServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(cardServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_USER')")
    public ResponseEntity<List<CardServiceViewModel>> getCards() {
        List<CardServiceViewModel> cardServiceViewModel = cardService.getAllCards();
        log.info("CardS  found : {}", cardServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(cardServiceViewModel);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CardServiceViewModel> deleteCard(@PathVariable("id") Long id) {
        CardServiceViewModel cardServiceViewModel = cardService.deleteCardById(id);
        log.info("Card deleted : {}", cardServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(cardServiceViewModel);
    }


}
