package com.example.WebSocketsServer.Service;

import com.example.WebSocketsServer.Entity.MsgEntity;
import com.example.WebSocketsServer.Resourse.MsgResource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MsgService {

    @PersistenceContext
    private EntityManager entityManager;

    private final MsgResource msgResource;


    public MsgService(MsgResource msgResource) {
        this.entityManager = entityManager;
        this.msgResource = msgResource;
    }

    public List<MsgEntity> findAll(){
        return msgResource.findAll();
    }

    public MsgEntity getUserById(long id){
        return msgResource.getOne(id);
    }

    public void saveMsg(MsgEntity msgEntity){
        msgResource.save(msgEntity);
    }

    public void deleteUserById(long id){
        msgResource.deleteById(id);
    }

}
