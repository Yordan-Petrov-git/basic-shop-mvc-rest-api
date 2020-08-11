package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.User;

import java.time.Instant;

/**
 * Class dto for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
public class MessageServiceModel {

    private Long id;
    private User senderId;
    private User reciverId;
    private String subjectOfTheMessa;
    private String messageToSend;
    private Instant sentOnDateTime;
    private Instant recivedOnDateTim;
    private Instant readOnDateTime;

    /**
     * Constructor
     */
    public MessageServiceModel() {
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
     * Gets sender id.
     *
     * @return the sender id
     */
    public User getSenderId() {
        return this.senderId;
    }

    /**
     * Sets sender id.
     *
     * @param senderId the sender id
     */
    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    /**
     * Gets reciver id.
     *
     * @return the reciver id
     */
    public User getReciverId() {
        return this.reciverId;
    }

    /**
     * Sets reciver id.
     *
     * @param reciverId the reciver id
     */
    public void setReciverId(User reciverId) {
        this.reciverId = reciverId;
    }

    /**
     * Gets subject of the messa.
     *
     * @return the subject of the messa
     */
    public String getSubjectOfTheMessa() {
        return this.subjectOfTheMessa;
    }

    /**
     * Sets subject of the messa.
     *
     * @param subjectOfTheMessa the subject of the messa
     */
    public void setSubjectOfTheMessa(String subjectOfTheMessa) {
        this.subjectOfTheMessa = subjectOfTheMessa;
    }

    /**
     * Gets message to send.
     *
     * @return the message to send
     */
    public String getMessageToSend() {
        return this.messageToSend;
    }

    /**
     * Sets message to send.
     *
     * @param messageToSend the message to send
     */
    public void setMessageToSend(String messageToSend) {
        this.messageToSend = messageToSend;
    }

    /**
     * Gets sent on date time.
     *
     * @return the sent on date time
     */
    public Instant getSentOnDateTime() {
        return this.sentOnDateTime;
    }

    /**
     * Sets sent on date time.
     *
     * @param sentOnDateTime the sent on date time
     */
    public void setSentOnDateTime(Instant sentOnDateTime) {
        this.sentOnDateTime = sentOnDateTime;
    }

    /**
     * Gets recived on date tim.
     *
     * @return the recived on date tim
     */
    public Instant getRecivedOnDateTim() {
        return this.recivedOnDateTim;
    }

    /**
     * Sets recived on date tim.
     *
     * @param recivedOnDateTim the recived on date tim
     */
    public void setRecivedOnDateTim(Instant recivedOnDateTim) {
        this.recivedOnDateTim = recivedOnDateTim;
    }

    /**
     * Gets read on date time.
     *
     * @return the read on date time
     */
    public Instant getReadOnDateTime() {
        return this.readOnDateTime;
    }

    /**
     * Sets read on date time.
     *
     * @param readOnDateTime the read on date time
     */
    public void setReadOnDateTime(Instant readOnDateTime) {
        this.readOnDateTime = readOnDateTime;
    }
}
