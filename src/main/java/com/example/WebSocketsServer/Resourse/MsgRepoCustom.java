package com.example.WebSocketsServer.Resourse;

import com.example.WebSocketsServer.Entity.MsgEntity;
import org.hibernate.engine.spi.ManagedEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgRepoCustom {

    List<MsgEntity> updateUserState(String date);
}
