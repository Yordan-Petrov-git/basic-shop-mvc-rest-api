package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.MediaServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.models.MediaServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface MediaService {
    MediaServiceModel createMedia(MediaServiceModel Media);

    void updateMedia(MediaServiceModel Media);

    MediaServiceViewModel getMediaById(long id);

    List<MediaServiceViewModel> getAllMedias();

    void deleteMediaById(long id);


}
