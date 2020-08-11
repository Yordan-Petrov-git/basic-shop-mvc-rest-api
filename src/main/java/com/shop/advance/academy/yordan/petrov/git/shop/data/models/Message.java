package com.shop.advance.academy.yordan.petrov.git.shop.data.models;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

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

    /**
     * @return
     */
    @ManyToOne(targetEntity = User.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH})
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    public User getSenderId() {
        return this.senderId;
    }

    /**
     * @param senderId
     */
    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    /**
     * @return
     */
    @ManyToOne(targetEntity = User.class
            , fetch = FetchType.EAGER
            , cascade = {CascadeType.DETACH})
    @JoinColumn(name = "reciver_id", referencedColumnName = "id")
    public User getReciverId() {
        return this.reciverId;
    }

    /**
     * @param reciverId
     */
    public void setReciverId(User reciverId) {
        this.reciverId = reciverId;
    }

    /**
     * @return
     */
    @Column(name = "message")
    public String getMessageToSend() {
        return this.messageToSend;
    }

    /**
     * @param messageToSend
     */
    public void setMessageToSend(String messageToSend) {
        this.messageToSend = messageToSend;
    }

    /**
     * @return
     */
    @Column(name = "date_sent")
    public Instant getSentOnDateTime() {
        return this.sentOnDateTime;
    }

    /**
     * @param sentOnDateTime
     */
    public void setSentOnDateTime(Instant sentOnDateTime) {
        this.sentOnDateTime = sentOnDateTime;
    }

    /**
     * @return
     */
    @Column(name = "subject_of_the_message")
    public String getSubjectOfTheMessage() {
        return this.subjectOfTheMessage;
    }

    /**
     * @param subjectOfTheMessage
     */
    public void setSubjectOfTheMessage(String subjectOfTheMessage) {
        this.subjectOfTheMessage = subjectOfTheMessage;
    }

    /**
     * @return
     */
    @Column(name = "recived_on_timestamp")
    public Instant getRecivedOnDateTime() {
        return this.recivedOnDateTime;
    }

    /**
     * @param recivedOnDateTime
     */
    public void setRecivedOnDateTime(Instant recivedOnDateTime) {
        this.recivedOnDateTime = recivedOnDateTime;
    }

    /**
     * @return
     */
    @Column(name = "read_on_timestamp")
    public Instant getReadOnDateTime() {
        return this.readOnDateTime;
    }

    /**
     * @param readOnDateTime
     */
    public void setReadOnDateTime(Instant readOnDateTime) {
        this.readOnDateTime = readOnDateTime;
    }


    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        if (!super.equals(o)) return false;
        Message message = (Message) o;
        return Objects.equals(subjectOfTheMessage, message.subjectOfTheMessage) &&
                Objects.equals(messageToSend, message.messageToSend) &&
                Objects.equals(sentOnDateTime, message.sentOnDateTime) &&
                Objects.equals(recivedOnDateTime, message.recivedOnDateTime) &&
                Objects.equals(readOnDateTime, message.readOnDateTime);
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subjectOfTheMessage, messageToSend, sentOnDateTime
                , recivedOnDateTime, readOnDateTime);
    }


    /**
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Message{");
        sb.append("subjectOfTheMessage='").append(subjectOfTheMessage).append('\'');
        sb.append(", messageToSend='").append(messageToSend).append('\'');
        sb.append(", sentOnDateTime=").append(sentOnDateTime);
        sb.append(", recivedOnDateTime=").append(recivedOnDateTime);
        sb.append(", readOnDateTime=").append(readOnDateTime);
        sb.append('}');
        return sb.toString();
    }
}
