package com.example.WebSocketsServer.Resourse;

import com.example.WebSocketsServer.Entity.MsgEntity;
import com.example.WebSocketsServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgResource extends JpaRepository<MsgEntity, Long> {



}
