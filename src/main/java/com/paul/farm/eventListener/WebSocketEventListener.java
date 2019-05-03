package com.paul.farm.eventListener;

import com.paul.farm.services.interfaces.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private FarmService farmService;

    private Logger logger = Logger.getLogger(WebSocketEventListener.class.getName());

    @EventListener
    private void handleSessionConnected(SessionConnectEvent event) {
        logger.log(Level.INFO, String.format("%s just connected to the server", event.getUser().getName()));
    }

    @EventListener
    private void handleSessionDisconnect(SessionDisconnectEvent event) {
        logger.log(Level.INFO, String.format("%s just disconnected from the server", event.getUser().getName()));
        farmService.setOffline(event.getUser().getName());
    }

}
