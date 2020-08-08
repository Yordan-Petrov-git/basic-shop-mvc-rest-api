package com.shop.advance.academy.yordan.petrov.git.shop.domain;

import com.shop.advance.academy.yordan.petrov.git.shop.data.dao.MessageDao;
import com.shop.advance.academy.yordan.petrov.git.shop.data.models.Message;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.dto.MessageServiceViewModel;
import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {

    @MockBean
    MessageDao messageDao;

    @Autowired
    MessageService messageService;

    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    private void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMessageServiceReturnsAllMessage() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message());
        messages.add(new Message());
        messages.add(new Message());

        Mockito.when(messageDao.findAll()).thenReturn(messages);
        List<MessageServiceViewModel> messageServiceViewModels = messageService.getAllMessages();

        assertEquals(3, messageServiceViewModels.size());
    }


    @Test
    public void testMessageServiceGetMessageById() {
        Message message = new Message();
        message.setId(15L);

        Mockito.when(messageDao.findById(15L))
                .thenReturn(java.util.Optional.of(message));
        MessageServiceViewModel messageServiceViewModels = this.modelMapper.map(message, MessageServiceViewModel.class);

        assertEquals(messageServiceViewModels, messageService.getMessageById(15L));
    }

    //TODO ADD TEST IF CREATES

    //TODO ADD TEST IF REMOVES

    //TODO ADD TEST IF UPDATES
}
