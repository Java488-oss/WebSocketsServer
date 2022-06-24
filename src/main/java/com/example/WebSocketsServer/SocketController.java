package com.example.WebSocketsServer;

import com.example.WebSocketsServer.Entity.MsgEntity;
import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Service.MsgService;
import com.example.WebSocketsServer.Service.UserRepoImpl;
import com.example.WebSocketsServer.Service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@RestController
class SocketController {

    private final SimpMessagingTemplate messagingTemplate;

    private final UserService userService;

    private final MsgService msgService;
    private final UserRepoImpl userRepo;
    private Timer timer = new Timer();

    private Boolean aBoolean = false;

    SocketController(UserService userService, UserRepoImpl userRepo, SimpMessagingTemplate messagingTemplate, MsgService msgService){
        this.userService = userService;
        this.userRepo = userRepo;
        this.messagingTemplate = messagingTemplate;
        this.msgService = msgService;
    }


    @MessageMapping("/Login")
    public void LogIn(@Payload String jsonStr){
        JSONObject jsonObject = new JSONObject(jsonStr);

        String name = jsonObject.getString("user");
        String pass = jsonObject.getString("pass");

        List<UserEntity> entityList = userRepo.getUserByName(name,pass);

        String s = entityList.size() != 0 ? "true" : "false";

        JSONObject student = new JSONObject();
        student.put("user", name);
        student.put("pass", pass);
        student.put("result", s);

        System.out.println("\n\n"+student);

        messagingTemplate.convertAndSendToUser(jsonObject.getString("id"), "/queue/updates", String.valueOf(student));

//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                GetStateUser();
//            }
//        }, 2000);

    }

    @MessageMapping("/SendMsg")
    public void GetAndSendMSg(@Payload String jsonStr){
        JSONObject jsonObject = new JSONObject(jsonStr);

        String userTO = jsonObject.getString("userTO");
        String userFrom = jsonObject.getString("userFrom");

        String msg = jsonObject.getString("msg");
        messagingTemplate.convertAndSendToUser(jsonObject.getString("userFrom"), "/queue/updates", String.valueOf(jsonObject));

        MsgEntity msgEntity = new MsgEntity();

        msgEntity.setMsgDate(new Date());
        msgEntity.setNameFrom(userFrom);
        msgEntity.setTabFrom(Integer.parseInt(userFrom));
        msgEntity.setNameTo(userTO);
        msgEntity.setTabTo(Integer.parseInt(userTO));
        msgEntity.setMsg(msg);
        msgEntity.setStatus(0);


        msgService.saveMsg(msgEntity);
//        register(msgService.getUserById(51));
    }


    public void GetStateUser(){

        messagingTemplate.convertAndSendToUser("13", "/queue/state", "Your online?");

    }

//    @MessageMapping("/register")
//    public void register(String message) {
//        JSONObject jsonObject = new JSONObject(message);
//        String name = "null";
//        String pass = "null";
//        JSONArray jsonArray = jsonObject.getJSONArray("register");
//
//        for(int i=0;i<jsonArray.length();i++){
//            name=jsonArray.getJSONObject(i).getString("user");
//            pass=jsonArray.getJSONObject(i).getString("pass");
//        }
//
//        UserEntity userEntity = new UserEntity(name, pass);
//
//        userService.saveUser(userEntity);
//
//    }

    public void register(MsgEntity message) {

        try{

            System.out.println(message.getMsg());

//            Files.write( Paths.get("test.txt"), message.getMsg().getBytes());


//            FileUtils.writeStringToFile(new File("test.txt"), "Hello File", StandardCharsets.UTF_8);
//
//            FileOutputStream out = new FileOutputStream("test.txt");
//            out.write(message);
//            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}