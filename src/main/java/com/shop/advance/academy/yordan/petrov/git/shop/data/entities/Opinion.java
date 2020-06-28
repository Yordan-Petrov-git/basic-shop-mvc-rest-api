package com.shop.advance.academy.yordan.petrov.git.shop.data.entities;

import com.shop.advance.academy.yordan.petrov.git.shop.data.entities.enums.Rating;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "opinions")
public class Opinion extends BaseEntity{

    private String comment;
    private Rating rating = Rating.NONE;
    private Double vote;
    private Media media;
    private User user;

    public Opinion() {
    }
    @Column(name = "comment")
    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    @Column(name = "rating")
    @Enumerated(EnumType.STRING)
    public Rating getRating() {
        return this.rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
    @Column(name = "vote")
    public Double getVote() {
        return this.vote;
    }

    public void setVote(Double vote) {
        this.vote = vote;
    }

    @ManyToOne(targetEntity = Media.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "media_id")
    public Media getMedia() {
        return this.media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }


    @ManyToOne(targetEntity = User.class,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Opinion)) return false;
        if (!super.equals(o)) return false;
        Opinion opinion = (Opinion) o;
        return Objects.equals(comment, opinion.comment) &&
                rating == opinion.rating &&
                Objects.equals(vote, opinion.vote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), comment, rating, vote);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Opinion{");
        sb.append("comment='").append(comment).append('\'');
        sb.append(", rating=").append(rating);
        sb.append(", vote=").append(vote);
        sb.append(", user=").append(user);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
