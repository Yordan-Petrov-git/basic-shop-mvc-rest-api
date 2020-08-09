package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.enums.Rating;

import java.util.Objects;
import java.util.Set;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class OpinionServiceModel {

    private Long id;
    private String comment;
    private Rating rating = Rating.NONE;
    private Double vote;
    private Set<MediaServiceModel> media;
    private UserServiceModel user;

    /**
     * Constructor
     */
    public OpinionServiceModel() {
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
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public Rating getRating() {
        return this.rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }

    /**
     * Gets vote.
     *
     * @return the vote
     */
    public Double getVote() {
        return this.vote;
    }

    /**
     * Sets vote.
     *
     * @param vote the vote
     */
    public void setVote(Double vote) {
        this.vote = vote;
    }

    /**
     * Gets media.
     *
     * @return the media
     */
    public Set<MediaServiceModel> getMedia() {
        return this.media;
    }

    /**
     * Sets media.
     *
     * @param media the media
     */
    public void setMedia(Set<MediaServiceModel> media) {
        this.media = media;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public UserServiceModel getUser() {
        return this.user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(UserServiceModel user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OpinionServiceModel)) return false;
        OpinionServiceModel that = (OpinionServiceModel) o;
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
        final StringBuilder sb = new StringBuilder("OpinionServiceModel{");
        sb.append("comment='").append(comment).append('\'');
        sb.append(", rating=").append(rating);
        sb.append(", vote=").append(vote);
        sb.append('}');
        return sb.toString();
    }
}
