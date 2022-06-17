package com.example.WebSocketsServer;

import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Service.UserRepoImpl;
import com.example.WebSocketsServer.Service.UserService;
import org.hibernate.result.Output;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
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


    @MessageMapping("/hello-msg-mapping")
//    @SendTo("/test/greetings")
    public void echoMessageMapping(@Payload UserEntity userEntity, @Header("simpSessionId") String sessionId){
        UserEntity userEntity1 = new UserEntity(userEntity.getUser(), userEntity.getPass());

        System.out.println("\n\n\n"+sessionId);

        System.out.println("\n\n\n"+userEntity1.getPass()+"  "+userEntity1.getUser());

        messagingTemplate.convertAndSendToUser(userEntity1.getPass(), "/user/11/queue/updates", userEntity1);

//        return "";
    }




//    @MessageMapping("/hello-msg-mapping")
//    @SendTo("/test/greetings")
//    public String echoMessageMapping(String message) {
//
//        JSONObject jsonObject = new JSONObject(message);
//        String user = "null";
//        String pass = "null";
//        JSONArray jsonArray = jsonObject.getJSONArray("register");
//
//        for(int i=0;i<jsonArray.length();i++){
//            user =jsonArray.getJSONObject(i).getString("user");
//            pass=jsonArray.getJSONObject(i).getString("pass");
//        }
//
//        System.out.println("\n\n\n\n"+user+"  "+pass);
//
//        List<UserEntity> entityList = userRepo.getUserByName(user, pass);
//
//        String s = entityList.size() != 0 ? "true" : "false";
//
//        return s;
//    }

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