package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 * Interface dao for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Repository
public interface MediaDao extends JpaRepository<Media, Long> {

    /**
     * @param id
     * @return
     */
    Optional<Media> findById(Long id);

    /**
     * @param path
     * @return
     */
    Optional<Media> findByPicturePath(String path);

    /**
     * @param path
     * @return
     */
    Optional<Media> findByVideoPath(String path);

    /**
     * @param path
     * @return
     */
    Optional<Media> findByDocumentPath(String path);

}

