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


    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets picture path.
     *
     * @return the picture path
     */
    public String getPicturePath() {
        return this.picturePath;
    }

    /**
     * Sets picture path.
     *
     * @param picturePath the picture path
     */
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    /**
     * Gets video path.
     *
     * @return the video path
     */
    public String getVideoPath() {
        return this.videoPath;
    }

    /**
     * Sets video path.
     *
     * @param videoPath the video path
     */
    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    /**
     * Gets document path.
     *
     * @return the document path
     */
    public String getDocumentPath() {
        return this.documentPath;
    }

    /**
     * Sets document path.
     *
     * @param documentPath the document path
     */
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
