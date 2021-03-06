package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.ItemServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.ItemService;
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
 * Class controller for the items.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/items")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    /**
     * Constructor
     */
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Method for
     *
     * @param itemServiceModel
     * @return
     */
    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ItemServiceViewModel> createItem(@RequestBody ItemServiceModel itemServiceModel) {
        ItemServiceViewModel itemServiceViewModel = itemService.createItem(itemServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(ItemController.class, "createItem", ItemServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(itemServiceViewModel.getId()).toUri();
        log.info("Item  created : {} {}", itemServiceViewModel, location);
        return ResponseEntity.created(location).body(itemServiceViewModel);
    }

    /**
     * Method for
     *
     * @param itemServiceModel
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ItemServiceViewModel> updateItem(@RequestBody ItemServiceModel itemServiceModel) {
        ItemServiceViewModel itemServiceViewModel = itemService.updateItem(itemServiceModel);
        log.info("Item  UPDATED : {}", itemServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(itemServiceViewModel);
    }

    /**
     * Method for
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ItemServiceViewModel> getItemById(@PathVariable("id") final Long id) {
        ItemServiceViewModel itemServiceViewModel = itemService.getItemById(id);
        log.info("Item  found : {}", itemServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(itemServiceViewModel);
    }

    /**
     * Method for
     *
     * @param title
     * @return
     */
    @GetMapping("/serach/item/title/{title}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ItemServiceViewModel> getItemByTitle(@PathVariable String title) {
        ItemServiceViewModel itemServiceViewModel = itemService.getItemByTitle(title);
        log.info("Item  found : {}", itemServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(itemServiceViewModel);
    }

    /**
     * Method for
     *
     * @param title
     * @return
     */
    @GetMapping("/serach/item/title/like/{title}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<ItemServiceViewModel>> getItemByTitleLike(@PathVariable String title) {
        List<ItemServiceViewModel> itemServiceViewModels = itemService.getItemByTitleLike(title);
        log.info("Item Found: {} ", itemServiceViewModels);
        return ResponseEntity.status(HttpStatus.FOUND).body(itemServiceViewModels);
    }

    /**
     * Method for
     *
     * @return
     */
    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<ItemServiceViewModel>> getAllItems() {
        List<ItemServiceViewModel> itemServiceViewModels = itemService.getAllItems();
        log.info("Item Found: {} ", itemServiceViewModels);
        return ResponseEntity.status(HttpStatus.FOUND).body(itemServiceViewModels);
    }

    /**
     * Method for
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<ItemServiceViewModel> deleteItem(@PathVariable("id") Long id) {
        ItemServiceViewModel itemServiceViewModel = itemService.deleteItemById(id);
        log.info("Item  deleted : {}", itemServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(itemServiceViewModel);
    }

}


