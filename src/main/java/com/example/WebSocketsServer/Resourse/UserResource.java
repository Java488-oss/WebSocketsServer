package com.example.WebSocketsServer.Resourse;

import com.example.WebSocketsServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserResource extends CrudRepository<UserEntity, Long> {

}
