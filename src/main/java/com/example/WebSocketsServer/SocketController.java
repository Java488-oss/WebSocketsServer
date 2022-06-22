package com.example.WebSocketsServer;

import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Service.UserRepoImpl;
import com.example.WebSocketsServer.Service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class SocketController {

    private final SimpMessagingTemplate messagingTemplate;

    private final UserService userService;

    private final UserRepoImpl userRepo;

    SocketController(UserService userService, UserRepoImpl userRepo, SimpMessagingTemplate messagingTemplate){
        this.userService = userService;
        this.userRepo = userRepo;
        this.messagingTemplate = messagingTemplate;
    }


    @MessageMapping("/Login")
    public void echoMessageMapping(@Payload String jsonStr){
        JSONObject jsonObject = new JSONObject(jsonStr);

        String name = jsonObject.getString("user");
        String pass = jsonObject.getString("pass");

        List<UserEntity> entityList = userRepo.getUserByName(name,pass);
        String s = entityList.size() != 0 ? "true" : "false";
        messagingTemplate.convertAndSendToUser(jsonObject.getString("id"), "/queue/updates", s);

    }

    @MessageMapping("/register")
    public void register(String message) {
        JSONObject jsonObject = new JSONObject(message);
        String name = "null";
        String pass = "null";
        JSONArray jsonArray = jsonObject.getJSONArray("register");

        for(int i=0;i<jsonArray.length();i++){
            name=jsonArray.getJSONObject(i).getString("user");
            pass=jsonArray.getJSONObject(i).getString("pass");
        }

        UserEntity userEntity = new UserEntity(name, pass);

        userService.saveUser(userEntity);

    }
}