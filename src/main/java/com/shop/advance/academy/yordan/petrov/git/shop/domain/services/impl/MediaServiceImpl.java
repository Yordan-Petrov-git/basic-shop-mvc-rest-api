package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.MediaRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.MediaServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.MediaServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.MediaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public MediaServiceModel createMedia(MediaServiceModel Media) {
        return null;
    }

    @Override
    public void updateMedia(MediaServiceModel Media) {

    }

    @Override
    public MediaServiceViewModel getMediaById(long id) {
        return null;
    }

    @Override
    public List<MediaServiceViewModel> getAllMedias() {
        return null;
    }

    @Override
    public void deleteMediaById(long id) {

    }
}
