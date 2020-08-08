package com.shop.advance.academy.yordan.petrov.git.shop.domain.dto;

import com.shop.advance.academy.yordan.petrov.git.shop.data.models.User;

import java.time.Instant;

public class MessageServiceViewModel {
    private Long id;
    private User senderId;
    private User reciverId;
    private String subjectOfTheMessa;
    private String messageToSend;
    private Instant sentOnDateTime;
    private Instant recivedOnDateTim;
    private Instant readOnDateTime;

    public MessageServiceViewModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSenderId() {
        return this.senderId;
    }

    public void setSenderId(User senderId) {
        this.senderId = senderId;
    }

    public User getReciverId() {
        return this.reciverId;
    }

    public void setReciverId(User reciverId) {
        this.reciverId = reciverId;
    }

    public String getSubjectOfTheMessa() {
        return this.subjectOfTheMessa;
    }

    public void setSubjectOfTheMessa(String subjectOfTheMessa) {
        this.subjectOfTheMessa = subjectOfTheMessa;
    }

    public String getMessageToSend() {
        return this.messageToSend;
    }

    public void setMessageToSend(String messageToSend) {
        this.messageToSend = messageToSend;
    }

    public Instant getSentOnDateTime() {
        return this.sentOnDateTime;
    }

    public void setSentOnDateTime(Instant sentOnDateTime) {
        this.sentOnDateTime = sentOnDateTime;
    }

    public Instant getRecivedOnDateTim() {
        return this.recivedOnDateTim;
    }

    public void setRecivedOnDateTim(Instant recivedOnDateTim) {
        this.recivedOnDateTim = recivedOnDateTim;
    }

    public Instant getReadOnDateTime() {
        return this.readOnDateTime;
    }

    public void setReadOnDateTime(Instant readOnDateTime) {
        this.readOnDateTime = readOnDateTime;
    }


}
