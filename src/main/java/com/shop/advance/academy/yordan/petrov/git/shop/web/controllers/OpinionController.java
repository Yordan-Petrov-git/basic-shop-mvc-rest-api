package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/opinion")
public class OpinionController {

    private final OpinionService opinionService;

    @Autowired
    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<OpinionServiceModel> createOpinion(@RequestBody OpinionServiceModel opinionServiceModel) {
        opinionService.createOpinion(opinionServiceModel);
        return new ResponseEntity<>(opinionServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public void updateOpinion(@PathVariable("id") Long id,@RequestBody OpinionServiceModel opinionServiceModel) {
        opinionService.updateOpinion(opinionServiceModel);
    }


    @GetMapping("/{id}")
    public OpinionServiceViewModel getOpinion(@PathVariable("id")final Long id) {
        return opinionService.getOpinionById(id);
    }

    @GetMapping()
    public List<OpinionServiceViewModel> getOpinions() {
        return opinionService.getAllOpinions();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteOpinion(@PathVariable("id") Long id) {
        opinionService.deleteOpinionById(id);
    }
}
