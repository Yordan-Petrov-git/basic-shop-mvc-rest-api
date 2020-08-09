package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import javax.persistence.*;
import java.time.Instant;
/**
 * Class model for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Entity
@Table(name = "messages")
public class Message extends BaseEntity {

    private User senderId;
    private User reciverId;
    private String subjectOfTheMessage;
    private String messageToSend;
    private Instant sentOnDateTime;
    private Instant recivedOnDateTime;
    private Instant readOnDateTime;

    /**
     * Constructor
     */
    public Message() {
    }

    @ManyToOne(targetEntity = User.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH})
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    public User getSenderId() {
        return this.senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    @ManyToOne(targetEntity = User.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH})
    @JoinColumn(name = "reciver_id", referencedColumnName = "id")
    public User getReciverId() {
        return this.reciverId;
    }

    public void setReciverId(User reciverId) {
        this.reciverId = reciverId;
    }

    @Column(name = "message")
    public String getMessageToSend() {
        return this.messageToSend;
    }

    public void setMessageToSend(String messageToSend) {
        this.messageToSend = messageToSend;
    }

    @Column(name = "date_sent")
    public Instant getSentOnDateTime() {
        return this.sentOnDateTime;
    }

    public void setSentOnDateTime(Instant sentOnDateTime) {
        this.sentOnDateTime = sentOnDateTime;
    }

    @Column(name = "subject_of_the_message")
    public String getSubjectOfTheMessage() {
        return this.subjectOfTheMessage;
    }

    public void setSubjectOfTheMessage(String subjectOfTheMessage) {
        this.subjectOfTheMessage = subjectOfTheMessage;
    }

    @Column(name = "recived_on_timestamp")
    public Instant getRecivedOnDateTime() {
        return this.recivedOnDateTime;
    }

    public void setRecivedOnDateTime(Instant recivedOnDateTime) {
        this.recivedOnDateTime = recivedOnDateTime;
    }

    @Column(name = "read_on_timestamp")
    public Instant getReadOnDateTime() {
        return this.readOnDateTime;
    }

    public void setReadOnDateTime(Instant readOnDateTime) {
        this.readOnDateTime = readOnDateTime;
    }
}
