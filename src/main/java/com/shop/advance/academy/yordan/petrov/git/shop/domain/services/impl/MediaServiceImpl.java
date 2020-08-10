package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.MediaDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Media;
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

/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public class MediaServiceImpl implements MediaService {

    private final MediaDao mediaDao;
    private final ModelMapper modelMapper;

    /**
     * Constructor
     */
    @Autowired
    public MediaServiceImpl(MediaDao mediaDao, ModelMapper modelMapper) {
        this.mediaDao = mediaDao;
        this.modelMapper = modelMapper;
    }

    /**
     * @param mediaServiceModel
     * @return
     */
    @Override
    public MediaServiceViewModel createMedia(MediaServiceModel mediaServiceModel) {
        Media media = mapMediaServiceModelToMedia(mediaServiceModel);
        findMediaByDocumentPath(mediaServiceModel);
        findMediaByPicturePath(mediaServiceModel);
        findMediaByVideoPath(mediaServiceModel);
        this.mediaDao.saveAndFlush(media);
        return mapMediaToMediaServiceViewModel(media);
    }

    /**
     * @param mediaServiceModel
     * @return
     */
    @Override
    @Transactional
    public MediaServiceViewModel updateMedia(MediaServiceModel mediaServiceModel) {
        Media media = mapMediaServiceModelToMedia(mediaServiceModel);
        getMediaById(mediaServiceModel.getId());
        this.mediaDao.saveAndFlush(media);
        return mapMediaToMediaServiceViewModel(media);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MediaServiceViewModel getMediaById(long id) {
        return this.modelMapper
                .map(this.mediaDao.findById(id).orElseThrow(() ->
                        new EntityNotFoundException(String.format("Media  with ID %s not found.", id))), MediaServiceViewModel.class);
    }

    /**
     * @return
     */
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

    /**
     * @param id
     * @return
     */
    @Override
    public MediaServiceViewModel deleteMediaById(long id) {
        MediaServiceViewModel mediaServiceViewModel = this.getMediaById(id);
        this.mediaDao.deleteById(id);
        return mediaServiceViewModel;
    }


    /**
     * @param mediaServiceModel
     */
    private void findMediaByVideoPath(MediaServiceModel mediaServiceModel) {
        this.mediaDao.findByVideoPath(mediaServiceModel.getVideoPath()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Media with video path '%s' already exists.", mediaServiceModel.getVideoPath()));
        });
    }

    /**
     * @param mediaServiceModel
     */
    private void findMediaByPicturePath(MediaServiceModel mediaServiceModel) {
        this.mediaDao.findByPicturePath(mediaServiceModel.getPicturePath()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Media with picture path '%s' already exists.", mediaServiceModel.getPicturePath()));
        });
    }

    /**
     * @param mediaServiceModel
     */
    private void findMediaByDocumentPath(MediaServiceModel mediaServiceModel) {
        this.mediaDao.findByDocumentPath(mediaServiceModel.getDocumentPath()).ifPresent(c -> {
            throw new InvalidEntityException(String.format("Media with document path '%s' already exists.", mediaServiceModel.getDocumentPath()));
        });
    }

    /**
     * @param media
     * @return
     */
    private MediaServiceViewModel mapMediaToMediaServiceViewModel(Media media) {
        return this.modelMapper.map(media, MediaServiceViewModel.class);
    }

    /**
     * @param mediaServiceModel
     * @return
     */
    private Media mapMediaServiceModelToMedia(MediaServiceModel mediaServiceModel) {
        return this.modelMapper.map(mediaServiceModel, Media.class);
    }
}
