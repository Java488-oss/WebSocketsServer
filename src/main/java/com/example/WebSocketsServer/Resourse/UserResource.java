package com.example.WebSocketsServer.Resourse;

import com.example.WebSocketsServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserResource extends JpaRepository<UserEntity, Long>{

}
