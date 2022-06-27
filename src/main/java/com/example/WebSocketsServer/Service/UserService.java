package com.example.WebSocketsServer.Service;

import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Resourse.UserResource;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

//    private SessionFactory factory;
//    Session session = new HibernateSessionFactory().getSessionFactory().openSession();
    private final UserResource userResource;


    public UserService(UserResource userResource) {
        this.entityManager = entityManager;
        this.userResource = userResource;
    }

    public List<UserEntity> findAll(){
        return (List<UserEntity>) userResource.findAll();
    }

    public Optional<UserEntity> getUserById(long id){
        return userResource.findById(id);
    }

    public UserEntity saveUser(UserEntity answerModel){
        return userResource.save(answerModel);
    }

    public void deleteUserById(long id){
        userResource.deleteById(id);
    }

//    public void update(long id){
//        userResource.sa
//    }

}
