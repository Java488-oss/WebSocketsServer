package com.example.WebSocketsServer;

import com.example.WebSocketsServer.Entity.MsgEntity;
import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Service.MsgRepoImpl;
import com.example.WebSocketsServer.Service.MsgService;
import com.example.WebSocketsServer.Service.UserRepoImpl;
import com.example.WebSocketsServer.Service.UserService;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;

@RestController
class SocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserService userService;
    private final MsgService msgService;
    private final UserRepoImpl userRepo;

    private final MsgRepoImpl msgRepo;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm");


    SocketController(UserService userService, UserRepoImpl userRepo, SimpMessagingTemplate messagingTemplate, MsgService msgService, MsgRepoImpl msgRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.messagingTemplate = messagingTemplate;
        this.msgService = msgService;
        this.msgRepo = msgRepo;
    }

    @MessageMapping("/Login")
    public void LogIn(@Payload String jsonStr) {
        JSONObject jsonObject = new JSONObject(jsonStr);

        String name = jsonObject.getString("user");
        String pass = jsonObject.getString("pass");
        List<UserEntity> entityList = userRepo.getUserByName(name, pass);
        String s = entityList.size() != 0 ? "true" : "false";
        JSONObject student = new JSONObject();
        student.put("user", name);
        student.put("pass", pass);
        student.put("result", s);

        messagingTemplate.convertAndSendToUser(jsonObject.getString("id"), "/queue/updates", String.valueOf(student));

        GetStateUser();
    }

    @MessageMapping("/SendMsg")
    public void GetAndSendMSg(@Payload String jsonStr) {



        JSONObject jsonObject = new JSONObject(jsonStr);

        String userTO = jsonObject.getString("userTO");
        String userFrom = jsonObject.getString("userFrom");
        String msg = jsonObject.getString("msg");

        messagingTemplate.convertAndSendToUser(jsonObject.getString("userFrom"), "/queue/updates", String.valueOf(jsonObject));

        MsgEntity msgEntity = new MsgEntity();
        msgEntity.setMsgDate(dateFormat.format(new Date()));
        msgEntity.setNameFrom(userFrom);
        msgEntity.setTabFrom(Integer.parseInt(userFrom));
        msgEntity.setNameTo(userTO);
        msgEntity.setTabTo(Integer.parseInt(userTO));
        msgEntity.setMsg(msg);
        msgEntity.setStatus(0);
        msgEntity.setSend(false);

        msgService.saveMsg(msgEntity);
    }

    public void GetStateUser() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        List<UserEntity> entityList1 = userService.findAll();

                        entityList1.forEach(pass->{
                            if(pass.isOnline()) {
                                UserEntity userEntity = new UserEntity();
                                userEntity.setId(pass.getId());
                                userEntity.setUser(pass.getUser());
                                userEntity.setPass(pass.getPass());
                                userEntity.setOnline(false);
                                userService.saveUser(userEntity);
                            }
                            messagingTemplate.convertAndSendToUser(pass.getPass(), "/queue/state", "Your online?");
                        });

                        GetStateUser();
                    }
                }, 5000);
    }
    @MessageMapping("/isOnline")
    public void isOnline(String msg){

        JSONObject jsonObject = new JSONObject(msg);
        List<UserEntity> entityList = userRepo.updateUserState(jsonObject.getString("pass"));

        entityList.forEach(t->{
            UserEntity userEntity = new UserEntity();
            userEntity.setId(t.getId());
            userEntity.setUser(t.getUser());
            userEntity.setPass(t.getPass());
            userEntity.setOnline(Boolean.parseBoolean(jsonObject.getString("state")));
            userService.saveUser(userEntity);
        });
    }

    @MessageMapping("/isSend")
    public void isSend(String msg){

        JSONObject jsonObject = new JSONObject(msg);
        List<MsgEntity> entityList = msgRepo.updateUserState(jsonObject.getString("date"));

        entityList.forEach(t->{

            MsgEntity msgEntity = new MsgEntity();
            msgEntity.setMsgDate(t.getMsgDate());
            msgEntity.setNameFrom(t.getNameFrom());
            msgEntity.setTabFrom(t.getTabFrom());

            msgEntity.setNameTo(t.getNameTo());
            msgEntity.setTabTo(t.getTabTo());
            msgEntity.setMsg(t.getMsg());
            msgEntity.setStatus(0);
            msgEntity.setSend(true);

            msgService.saveMsg(msgEntity);
//            userService.saveUser(userEntity);
        });
    }

    public void register(MsgEntity message) {

        try {
            System.out.println(message.getMsg());

//            Files.write( Paths.get("test.txt"), message.getMsg().getBytes());
//            FileUtils.writeStringToFile(new File("test.txt"), "Hello File", StandardCharsets.UTF_8);
//            FileOutputStream out = new FileOutputStream("test.txt");
//            out.write(message);
//            out.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}