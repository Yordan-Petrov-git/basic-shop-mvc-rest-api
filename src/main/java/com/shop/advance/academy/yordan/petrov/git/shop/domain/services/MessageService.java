package com.shop.advance.academy.yordan.petrov.git.shop.domain.services;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MessageServiceModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MessageServiceViewModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {


    MessageServiceModel createMessage(MessageServiceModel messageServiceModel);

    MessageServiceViewModel updateMessage(MessageServiceModel messageServiceModel);

    MessageServiceViewModel getMessageById(long id);

    List<MessageServiceViewModel> getAllMessages();

    MessageServiceViewModel deleteMessageById(long id);


    public void sendMessageToSingleUser(String messageToSend);

}
