package com.shop.advance.academy.yordan.petrov.git.shop.domain.services.impl;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.MessageDao;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MessageServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MessageServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class interface service implementation  for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao;
    private final ModelMapper modelMapper;

    /**
     * Constructor
     */
    public MessageServiceImpl(MessageDao messageDao, ModelMapper modelMapper) {
        this.messageDao = messageDao;
        this.modelMapper = modelMapper;
    }

    /**
     * @param messageServiceModel
     * @return
     */
    @Override
    public MessageServiceModel createMessage(MessageServiceModel messageServiceModel) {
        return null;
    }

    /**
     * @param messageServiceModel
     * @return
     */
    @Override
    public MessageServiceViewModel updateMessage(MessageServiceModel messageServiceModel) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MessageServiceViewModel getMessageById(long id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<MessageServiceViewModel> getAllMessages() {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MessageServiceViewModel deleteMessageById(long id) {
        return null;
    }

    /**
     * @param messageToSend
     */
    @Override
    public void sendMessageToSingleUser(String messageToSend) {
        //TODO SEND MESSAGE
    }
}
