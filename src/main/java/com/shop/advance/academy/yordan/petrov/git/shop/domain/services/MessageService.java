package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MessageServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MessageServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface service for .
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Jul 8, 2020.
 */
@Service
public interface MessageService {


    /**
     * @param messageServiceModel
     * @return
     */
    MessageServiceModel createMessage(MessageServiceModel messageServiceModel);

    /**
     * @param messageServiceModel
     * @return
     */
    MessageServiceViewModel updateMessage(MessageServiceModel messageServiceModel);

    /**
     * @param id
     * @return
     */
    MessageServiceViewModel getMessageById(long id);

    /**
     * @return
     */
    List<MessageServiceViewModel> getAllMessages();

    /**
     * @param id
     * @return
     */
    MessageServiceViewModel deleteMessageById(long id);

    /**
     * @param messageToSend
     */
    void sendMessageToSingleUser(String messageToSend);

}
