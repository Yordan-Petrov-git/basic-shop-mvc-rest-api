package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OpinionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.OpinionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OpinionService;
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
 * Class controller for the opinion.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@RestController
@RequestMapping("api/opinion")
@Slf4j
public class OpinionController {

    private final OpinionService opinionService;

    /**
     * Constructor
     */
    @Autowired
    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }


    @PostMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<OpinionServiceViewModel> createOpinion(@RequestBody OpinionServiceModel opinionServiceModel) {
        OpinionServiceViewModel opinionServiceViewModel = opinionService.createOpinion(opinionServiceModel);
        URI location = MvcUriComponentsBuilder.fromMethodName(OpinionController.class, "createOpinion", OpinionServiceViewModel.class)
                .pathSegment("{id}").buildAndExpand(opinionServiceViewModel.getId()).toUri();
        log.info("Opinion  created : {} {}", opinionServiceViewModel, location);
        return ResponseEntity.created(location).body(opinionServiceViewModel);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<OpinionServiceViewModel> updateOpinion(@RequestBody OpinionServiceModel opinionServiceModel) {
        OpinionServiceViewModel opinionServiceViewModel = opinionService.updateOpinion(opinionServiceModel);
        log.info("Opinion  updated : {}", opinionServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(opinionServiceViewModel);
    }


    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<OpinionServiceViewModel> getOpinion(@PathVariable("id") final Long id) {
        OpinionServiceViewModel opinionServiceViewModel = opinionService.getOpinionById(id);
        log.info("Opinion  FOUND : {}", opinionServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(opinionServiceViewModel);
    }

    @GetMapping()
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<List<OpinionServiceViewModel>> getOpinions() {
        List<OpinionServiceViewModel> opinionServiceViewModelList = opinionService.getAllOpinions();
        log.info("Opinions Found: {} ", opinionServiceViewModelList);
        return ResponseEntity.status(HttpStatus.FOUND).body(opinionServiceViewModelList);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_USER')")
    public ResponseEntity<OpinionServiceViewModel> deleteOpinion(@PathVariable("id") Long id) {
        OpinionServiceViewModel opinionServiceViewModel = opinionService.deleteOpinionById(id);
        log.info("Opinion  deleted : {}", opinionServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(opinionServiceViewModel);
    }
}
