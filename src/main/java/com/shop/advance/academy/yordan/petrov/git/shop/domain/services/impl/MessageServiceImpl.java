package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.MessageDao;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MessageServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MessageServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao;
    private final ModelMapper modelMapper;

    public MessageServiceImpl(MessageDao messageDao, ModelMapper modelMapper) {
        this.messageDao = messageDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public MessageServiceModel createMessage(MessageServiceModel messageServiceModel) {
        return null;
    }

    @Override
    public MessageServiceViewModel updateMessage(MessageServiceModel messageServiceModel) {
        return null;
    }

    @Override
    public MessageServiceViewModel getMessageById(long id) {
        return null;
    }

    @Override
    public List<MessageServiceViewModel> getAllMessages() {
        return null;
    }

    @Override
    public MessageServiceViewModel deleteMessageById(long id) {
        return null;
    }

    @Override
    public void sendMessageToSingleUser(String messageToSend) {
        //TODO SEND MESSAGE
    }
}
