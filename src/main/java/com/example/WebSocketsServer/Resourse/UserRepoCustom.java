package com.example.WebSocketsServer.Resourse;

import com.example.WebSocketsServer.Entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepoCustom {
    List<UserEntity> getUserByName(String name, String pass);

//    @Query("update UserEntity set isOnline=:state where pass=:pass")
    List<UserEntity> updateUserState(String pass);
}
