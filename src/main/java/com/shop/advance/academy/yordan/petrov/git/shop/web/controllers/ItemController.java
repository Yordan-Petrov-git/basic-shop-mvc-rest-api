package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.ItemServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<ItemServiceModel> createItem(@RequestBody ItemServiceModel itemServiceModel) {
        itemService.createItem(itemServiceModel);
        return new ResponseEntity<>(itemServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateItem(@PathVariable("id") Long id,@RequestBody ItemServiceModel itemServiceModel) {
        itemService.updateItem(itemServiceModel);
    }


    @GetMapping("/{id}")
    public ItemServiceViewModel getItem(@PathVariable("id")final Long id) {
        return itemService.getItemById(id);
    }

    @GetMapping()
    public List<ItemServiceViewModel> getItems() {
        return itemService.getAllItems();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItemById(id);
    }

}


