package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "medias")
public class Media extends BaseEntity {

    private String picturePath;
    private String videoPath;
    private String documentPath;

    public Media() {
    }

    @Column(name = "picture_path")
    public String getPicturePath() {
        return this.picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Column(name = "video_path")
    public String getVideoPath() {
        return this.videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Column(name = "document_path")
    public String getDocumentPath() {
        return this.documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Media)) return false;
        if (!super.equals(o)) return false;
        Media media = (Media) o;
        return Objects.equals(picturePath, media.picturePath) &&
                Objects.equals(videoPath, media.videoPath) &&
                Objects.equals(documentPath, media.documentPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), picturePath, videoPath, documentPath);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Media{");
        sb.append("picturePath='").append(picturePath).append('\'');
        sb.append(", videoPath='").append(videoPath).append('\'');
        sb.append(", documentPath='").append(documentPath).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
