package com.shop.advance.academy.yordan.petrov.git.shop.web.controllers;


import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
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
    public ResponseEntity<MediaServiceViewModel> createMedia(@RequestBody MediaServiceModel mediaServiceModel) {


        MediaServiceViewModel mediaServiceViewModel = mediaService.createMedia(mediaServiceModel);

        log.info("Media  created : {}", mediaServiceViewModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(mediaServiceViewModel);

    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaServiceViewModel> updateMedia(@PathVariable("id") Long id, @RequestBody MediaServiceModel mediaServiceModel) {

        MediaServiceViewModel mediaServiceViewModel = mediaService.updateMedia(mediaServiceModel);

        log.info("Media  UPDATED : {}", mediaServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(mediaServiceViewModel);


    }


    @GetMapping("/{id}")
    public ResponseEntity<MediaServiceViewModel> getMedia(@PathVariable("id") final Long id) {

        MediaServiceViewModel mediaServiceViewModel = mediaService.getMediaById(id);

        log.info("Media  FOUND : {}", mediaServiceViewModel);

        return ResponseEntity.status(HttpStatus.FOUND).body(mediaServiceViewModel);

    }

    @GetMapping()
    public ResponseEntity<List<MediaServiceViewModel>> getMedias() {

        List<MediaServiceViewModel> mediaServiceViewModels = mediaService.getAllMedias();

        log.info("Medias Found: {} ", mediaServiceViewModels);

        return ResponseEntity.status(HttpStatus.FOUND).body(mediaServiceViewModels);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MediaServiceViewModel> deleteMedia(@PathVariable("id") Long id) {

        MediaServiceViewModel mediaServiceViewModel = mediaService.deleteMediaById(id);

        log.info("Media deleted : {}", mediaServiceViewModel);

        return ResponseEntity.status(HttpStatus.OK).body(mediaServiceViewModel);

    }
}
