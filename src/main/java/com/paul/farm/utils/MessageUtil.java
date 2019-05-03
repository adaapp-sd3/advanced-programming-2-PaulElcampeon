package com.paul.farm.utils;


import com.paul.farm.dtos.ErrorMessageResDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessage(String location, String message, boolean result) {
        if (!result) {
            simpMessagingTemplate.convertAndSend("/topic/farm/message/" + location, new ErrorMessageResDto(message));
        }
    }
}
