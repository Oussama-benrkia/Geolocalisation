package org.app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
public class msgHandlerController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public msgHandlerController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/location")
    @SendTo("/topic/locations")
    public LocationMessage sendLocation(LocationMessage locationMessage) {
        return locationMessage;
    }

    // If you want to broadcast without @SendTo annotation, you can use SimpMessagingTemplate
    public void broadcastLocation(LocationMessage locationMessage) {
        messagingTemplate.convertAndSend("/topic/locations", locationMessage);
    }
}

class LocationMessage {
    private double x;
    private double y;

    // getters and setters
    public double getX() { return x; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }
}

