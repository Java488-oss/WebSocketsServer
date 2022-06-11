package com.example.WebSocketsServer;

import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Service.UserRepoImpl;
import com.example.WebSocketsServer.Service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class SocketController {

    private final UserService userService;

    private final UserRepoImpl userRepo;

    SocketController(UserService userService, UserRepoImpl userRepo){
        this.userService = userService;
        this.userRepo = userRepo;
    }

    @MessageMapping("/hello-msg-mapping")
    @SendTo("/topic/greetings")
    public String echoMessageMapping(String message) {

        JSONObject jsonObject = new JSONObject(message);
        String user = "null";
        String pass = "null";
        JSONArray jsonArray = jsonObject.getJSONArray("register");

        for(int i=0;i<jsonArray.length();i++){
            user =jsonArray.getJSONObject(i).getString("user");
            pass=jsonArray.getJSONObject(i).getString("pass");
        }

        List<UserEntity> entityList = userRepo.getUserByName(user);
        entityList.forEach(i->{
            System.out.println("return to BD by user "+i.getUser()+"  "+i.getPass());
        });

        return "return "+ user +"  "+pass;
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