package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import java.util.Objects;

public class MediaServiceViewModel {
    private Long id;
    private String picturePath;
    private String videoPath;
    private String documentPath;


    public MediaServiceViewModel() {
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
        if (!(o instanceof MediaServiceViewModel)) return false;
        MediaServiceViewModel that = (MediaServiceViewModel) o;
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
        final StringBuilder sb = new StringBuilder("MediaServiceViewModel{");
        sb.append("picturePath='").append(picturePath).append('\'');
        sb.append(", videoPath='").append(videoPath).append('\'');
        sb.append(", documentPath='").append(documentPath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
