package org.app.backend.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/sendLocation")
    @SendTo("/topic/location")
    public LocationData sendLocation(LocationData locationData) {

        return locationData;
    }

}
