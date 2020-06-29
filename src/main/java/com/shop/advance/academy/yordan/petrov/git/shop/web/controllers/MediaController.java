package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.MediaServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.MediaServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OpinionServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.OrderServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.MediaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/media")
@Slf4j
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }


    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<MediaServiceModel> createMedia(@RequestBody MediaServiceModel mediaServiceModel) {
        mediaService.createMedia(mediaServiceModel);
        return new ResponseEntity<>(mediaServiceModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<> updateMedia(@PathVariable("id") Long id, @RequestBody MediaServiceModel mediaServiceModel) {
        mediaService.updateMedia(mediaServiceModel);
    }


    @GetMapping("/{id}")
    public MediaServiceViewModel getMedia(@PathVariable("id") final Long id) {
        return mediaService.getMediaById(id);
    }

    @GetMapping()
    public ResponseEntity<List<MediaServiceViewModel>> getMedias() {

        List<MediaServiceViewModel> mediaServiceViewModels = mediaService.getAllMedias();

        log.info("Media Found: {} ", mediaServiceViewModels);

        return ResponseEntity.status(HttpStatus.FOUND).body(mediaServiceViewModels);

    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<> deleteMedia(@PathVariable("id") Long id) {
        mediaService.deleteMediaById(id);
    }
}
