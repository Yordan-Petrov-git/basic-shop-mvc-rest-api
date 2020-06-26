package com.shop.advance.academy.yordan.petrov.git.shop.domain.models;


import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.Rating;

import java.util.Objects;

public class OpinionServiceViewModel {

    private String comment;
    private Rating rating = Rating.NONE;
    private Double vote;
    private MediaServiceViewModel media;
    private UserServiceViewModel user;

    public OpinionServiceViewModel() {
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Rating getRating() {
        return this.rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Double getVote() {
        return this.vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }

    public MediaServiceViewModel getMedia() {
        return this.media;
    }

    public void setMedia(MediaServiceViewModel media) {
        this.media = media;
    }

    public UserServiceViewModel getUser() {
        return this.user;
    }

    public void setUser(UserServiceViewModel user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpinionServiceViewModel)) return false;
        OpinionServiceViewModel that = (OpinionServiceViewModel) o;
        return Objects.equals(comment, that.comment) &&
                rating == that.rating &&
                Objects.equals(vote, that.vote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comment, rating, vote);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OpinionServiceViewModel{");
        sb.append("comment='").append(comment).append('\'');
        sb.append(", rating=").append(rating);
        sb.append(", vote=").append(vote);
        sb.append('}');
        return sb.toString();
    }
}
