package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.MediaDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Media;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MediaServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MediaServiceViewModel;
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

    private final MediaDao mediaDao;
    private final ModelMapper modelMapper;

    @Autowired
    public MediaServiceImpl(MediaDao mediaDao, ModelMapper modelMapper) {
        this.mediaDao = mediaDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public MediaServiceViewModel createMedia(MediaServiceModel mediaServiceModel) {
        Media media = mapMediaServiceModelToMedia(mediaServiceModel);
        findMediaByDocumentPath(mediaServiceModel);
        findMediaByPicturePath(mediaServiceModel);
        findMediaByVideoPath(mediaServiceModel);
        this.mediaDao.saveAndFlush(media);
        return mapMediaToMediaServiceViewModel(media);
    }

    @Override
    @Transactional
    public MediaServiceViewModel updateMedia(MediaServiceModel mediaServiceModel) {
        Media media = mapMediaServiceModelToMedia(mediaServiceModel);
        getMediaById(mediaServiceModel.getId());
        this.mediaDao.saveAndFlush(media);
        return mapMediaToMediaServiceViewModel(media);
    }

    @Override
    public MediaServiceViewModel getMediaById(long id) {
        return this.modelMapper
                .map(this.mediaDao.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Media  with ID %s not found.", id))), MediaServiceViewModel.class);
    }

    @Override
    public List<MediaServiceViewModel> getAllMedias() {

        this.mediaDao.findAll()
                .stream()
                .findAny()
                .orElseThrow(() -> new InvalidEntityException("No Media was found"));

        List<Media> media = mediaDao.findAll();

        return modelMapper.map(media, new TypeToken<List<MediaServiceViewModel>>() {
        }.getType());

    }

    @Override
    public MediaServiceViewModel deleteMediaById(long id) {
        MediaServiceViewModel mediaServiceViewModel = this.getMediaById(id);
        this.mediaDao.deleteById(id);
        return mediaServiceViewModel;
    }


    private void findMediaByVideoPath(MediaServiceModel mediaServiceModel) {
        this.mediaDao.findByVideoPath(mediaServiceModel.getVideoPath()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Media with video path '%s' already exists.", mediaServiceModel.getVideoPath()));
        });
    }

    private void findMediaByPicturePath(MediaServiceModel mediaServiceModel) {
        this.mediaDao.findByPicturePath(mediaServiceModel.getPicturePath()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Media with picture path '%s' already exists.", mediaServiceModel.getPicturePath()));
        });
    }

    private void findMediaByDocumentPath(MediaServiceModel mediaServiceModel) {
        this.mediaDao.findByDocumentPath(mediaServiceModel.getDocumentPath()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Media with document path '%s' already exists.", mediaServiceModel.getDocumentPath()));
        });
    }

    private MediaServiceViewModel mapMediaToMediaServiceViewModel(Media media) {
        return this.modelMapper.map(media, MediaServiceViewModel.class);
    }

    private Media mapMediaServiceModelToMedia(MediaServiceModel mediaServiceModel) {
        return this.modelMapper.map(mediaServiceModel, Media.class);
    }
}
