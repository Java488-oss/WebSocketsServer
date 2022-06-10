package com.example.WebSocketsServer;

import com.example.WebSocketsServer.Service.UserService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
class SocketController {

    private final UserService userService;

    SocketController(UserService userService) {
        this.userService = userService;
    }

    @MessageMapping("/hello-msg-mapping")
    @SendTo("/topic/greetings")
    public String echoMessageMapping(String message) {
        System.out.println(message.trim());

//        List<UserEntity> userEntityList = userService.findAll();
//
//        userEntityList.forEach(list->{
//            System.out.println(list.getUser()+" "+list.getPass());
//        });

        return "return "+message.trim();
    }
}