package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.MediaRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Address;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Media;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.ShoppingCart;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.*;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.MediaService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public MediaServiceImpl(MediaRepository mediaRepository, ModelMapper modelMapper) {
        this.mediaRepository = mediaRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public MediaServiceModel createMedia(MediaServiceModel mediaServiceModel) {
        Media media = this.modelMapper.map(mediaServiceModel, Media.class);
        return this.modelMapper.map( this.mediaRepository.saveAndFlush(media), MediaServiceModel.class);
    }

    @Override
    public void updateMedia(MediaServiceModel Media) {

    }

    @Override
    public MediaServiceViewModel getMediaById(long id) {
        return this.modelMapper
                .map(this.mediaRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Media  with ID %s not found.", id))), MediaServiceViewModel.class);
    }

    @Override
    public List<MediaServiceViewModel> getAllMedias() {
        List<Media> media = mediaRepository.findAll();

        return modelMapper.map(media, new TypeToken<List<MediaServiceViewModel>>() {
        }.getType());
    }

    @Override
    public void deleteMediaById(long id) {
        mediaRepository.deleteById(id);
    }
}
