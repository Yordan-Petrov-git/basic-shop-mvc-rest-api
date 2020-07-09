package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OpinionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/opinion")
@Slf4j
public class OpinionController {

    private final OpinionService opinionService;

    @Autowired
    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }


    @PostMapping()
    public ResponseEntity<OpinionServiceViewModel> createOpinion(@RequestBody OpinionServiceModel opinionServiceModel) {
        OpinionServiceViewModel opinionServiceViewModel = opinionService.createOpinion(opinionServiceModel);
        log.info("Opinion  created : {}", opinionServiceViewModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(opinionServiceViewModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OpinionServiceViewModel> updateOpinion(@PathVariable("id") Long id, @RequestBody OpinionServiceModel opinionServiceModel) {
        OpinionServiceViewModel opinionServiceViewModel = opinionService.updateOpinion(opinionServiceModel);
        log.info("Opinion  updated : {}", opinionServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(opinionServiceViewModel);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OpinionServiceViewModel> getOpinion(@PathVariable("id") final Long id) {
        OpinionServiceViewModel opinionServiceViewModel = opinionService.getOpinionById(id);
        log.info("Opinion  FOUND : {}", opinionServiceViewModel);
        return ResponseEntity.status(HttpStatus.FOUND).body(opinionServiceViewModel);
    }

    @GetMapping()
    public ResponseEntity<List<OpinionServiceViewModel>> getOpinions() {
        List<OpinionServiceViewModel> opinionServiceViewModelList = opinionService.getAllOpinions();
        log.info("Opinions Found: {} ", opinionServiceViewModelList);
        return ResponseEntity.status(HttpStatus.FOUND).body(opinionServiceViewModelList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OpinionServiceViewModel> deleteOpinion(@PathVariable("id") Long id) {
        OpinionServiceViewModel opinionServiceViewModel = opinionService.deleteOpinionById(id);
        log.info("Opinion  deleted : {}", opinionServiceViewModel);
        return ResponseEntity.status(HttpStatus.OK).body(opinionServiceViewModel);
    }
}
