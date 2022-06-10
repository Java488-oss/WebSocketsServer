package com.example.WebSocketsServer.Service;

import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Resourse.UserResource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserResource userResource;


    public UserService(UserResource userResource) {
        this.entityManager = entityManager;
        this.userResource = userResource;
    }


    public List<UserEntity> findAll(){
        return userResource.findAll();
    }

    public UserEntity getUserById(long id){
        return userResource.getOne(id);
    }

//    public UserEntity saveUser(UserEntity answerModel){
//        return UserResource.save(answerModel);
//    }
//
//    public void deleteUserById(long id){
//        UserResource.deleteById(id);
//    }

}
