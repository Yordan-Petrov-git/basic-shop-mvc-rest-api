package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;
/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class MediaServiceModel {
    private Long id;
    private String picturePath;
    private String videoPath;
    private String documentPath;

    /**
     * Constructor
     */

    public MediaServiceModel() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicturePath() {
        return this.picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getVideoPath() {
        return this.videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getDocumentPath() {
        return this.documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaServiceModel)) return false;
        MediaServiceModel that = (MediaServiceModel) o;
        return Objects.equals(picturePath, that.picturePath) &&
                Objects.equals(videoPath, that.videoPath) &&
                Objects.equals(documentPath, that.documentPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(picturePath, videoPath, documentPath);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MediaServiceModel{");
        sb.append("picturePath='").append(picturePath).append('\'');
        sb.append(", videoPath='").append(videoPath).append('\'');
        sb.append(", documentPath='").append(documentPath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
