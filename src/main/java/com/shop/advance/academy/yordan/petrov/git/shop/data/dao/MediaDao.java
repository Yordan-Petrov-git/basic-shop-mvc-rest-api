package com.shop.advance.academy.yordan.petrov.git.shop.data.dao;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MediaDao extends JpaRepository<Media, Long> {

    Optional<Media> findById(Long id);

    Optional<Media> findByPicturePath(String path);

    Optional<Media> findByVideoPath(String path);

    Optional<Media> findByDocumentPath(String path);

}

