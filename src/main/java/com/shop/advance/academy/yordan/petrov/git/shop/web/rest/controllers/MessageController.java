package com.shop.advance.academy.yordan.petrov.git.shop.web.rest.controllers;

import com.shop.advance.academy.yordan.petrov.git.shop.domain.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class controller for the messages.
 *
 * @author Yordan Petrov
 * @version 1.0.0.0
 * @since Aug 9, 2020.
 */
@RestController
@RequestMapping("api/message")
@Slf4j
public class MessageController {

    private final MessageService messageService;

    /**
     * Constructor
     */
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
}
