package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MediaServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MediaServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface MediaService {

    MediaServiceViewModel createMedia(MediaServiceModel Media);

    MediaServiceViewModel updateMedia(MediaServiceModel Media);

    MediaServiceViewModel getMediaById(long id);

    List<MediaServiceViewModel> getAllMedias();

    MediaServiceViewModel deleteMediaById(long id);
}
