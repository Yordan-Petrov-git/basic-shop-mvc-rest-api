package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.MediaRepository;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Media;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.MediaServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.MediaServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.MediaService;
import com.shop.advance.academy.yordan.petrov.git.shop.exeption.InvalidEntityException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
    public MediaServiceViewModel createMedia(MediaServiceModel mediaServiceModel) {

        Media media = this.modelMapper.map(mediaServiceModel, Media.class);

        this.mediaRepository.findByDocumentPath(mediaServiceModel.getDocumentPath()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Media with document path '%s' already exists.", mediaServiceModel.getDocumentPath()));
        });

        this.mediaRepository.findByPicturePath(mediaServiceModel.getPicturePath()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Media with picture path '%s' already exists.", mediaServiceModel.getPicturePath()));
        });

        this.mediaRepository.findByVideoPath(mediaServiceModel.getVideoPath()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Media with video path '%s' already exists.", mediaServiceModel.getVideoPath()));
        });

        return this.modelMapper.map(this.mediaRepository.saveAndFlush(media), MediaServiceViewModel.class);

    }

    @Override
    @Transactional
    public MediaServiceViewModel updateMedia(MediaServiceModel mediaServiceModel) {

        Media media = this.modelMapper.map(mediaServiceModel, Media.class);

        this.mediaRepository.findById(mediaServiceModel.getId())
                .orElseThrow(() -> new InvalidEntityException(String.format("Media with id '%d' not found .", mediaServiceModel.getId())));

        return this.modelMapper.map(this.mediaRepository.saveAndFlush(media), MediaServiceViewModel.class);

    }

    @Override
    public MediaServiceViewModel getMediaById(long id) {
        return this.modelMapper
                .map(this.mediaRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Media  with ID %s not found.", id))), MediaServiceViewModel.class);
    }

    @Override
    public List<MediaServiceViewModel> getAllMedias() {

        this.mediaRepository.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Media was found"));

        List<Media> media = mediaRepository.findAll();

        return modelMapper.map(media, new TypeToken<List<MediaServiceViewModel>>() {
        }.getType());

    }

    @Override
    public MediaServiceViewModel deleteMediaById(long id) {
        MediaServiceViewModel mediaServiceViewModel = this.getMediaById(id);
        this.mediaRepository.deleteById(id);
        return mediaServiceViewModel;
    }
}
