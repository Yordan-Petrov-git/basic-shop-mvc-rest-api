package com.shop.advance.academy.yordan.petrov.git.shop.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/item")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping("/register")
    public ResponseEntity<ItemServiceViewModel> createItem(@RequestBody ItemServiceModel itemServiceModel) {

        ItemServiceViewModel itemServiceViewModel = itemService.createItem(itemServiceModel);

        log.info("Item  created : {}", itemServiceViewModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(itemServiceViewModel);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemServiceViewModel> updateItem(@PathVariable("id") Long id, @RequestBody ItemServiceModel itemServiceModel) {

        ItemServiceViewModel itemServiceViewModel = itemService.updateItem(itemServiceModel);

        log.info("Item  UPDATED : {}", itemServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(itemServiceViewModel);


    }


    @GetMapping("/{id}")
    public ResponseEntity<ItemServiceViewModel> getItem(@PathVariable("id") final Long id) {

        ItemServiceViewModel itemServiceViewModel = itemService.getItemById(id);

        log.info("Item  found : {}", itemServiceViewModel);

        return ResponseEntity.status(HttpStatus.FOUND).body(itemServiceViewModel);

    }

    @GetMapping()
    public ResponseEntity<List<ItemServiceViewModel>> getItems() {

        List<ItemServiceViewModel> itemServiceViewModels = itemService.getAllItems();

        log.info("Item Found: {} ", itemServiceViewModels);

        return ResponseEntity.status(HttpStatus.FOUND).body(itemServiceViewModels);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ItemServiceViewModel> deleteItem(@PathVariable("id") Long id) {

        ItemServiceViewModel itemServiceViewModel = itemService.deleteItemById(id);

        log.info("Item  deleted : {}", itemServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(itemServiceViewModel);

    }

}


