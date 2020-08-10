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

    /**
     * @param Media
     * @return
     */
    MediaServiceViewModel createMedia(MediaServiceModel Media);

    /**
     * @param Media
     * @return
     */
    MediaServiceViewModel updateMedia(MediaServiceModel Media);

    /**
     * @param id
     * @return
     */
    MediaServiceViewModel getMediaById(long id);

    /**
     * @return
     */
    List<MediaServiceViewModel> getAllMedias();

    /**
     * @param id
     * @return
     */
    MediaServiceViewModel deleteMediaById(long id);
}
