package com.example.WebSocketsServer.Service;

import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Resourse.UserResource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return userResource.findAll();
    }

    public UserEntity getUserById(long id){
        return userResource.getOne(id);
    }

//    public Boolean findUserAndPass(String name, String pass){
//        System.out.print(name+"  "+pass);
//        Boolean aBoolean = userResource.findUserAndPass(name, pass);
//        System.out.println(aBoolean);
//        return true;
//    }

    public UserEntity saveUser(UserEntity answerModel){
        return userResource.save(answerModel);
    }

    public void deleteUserById(long id){
        userResource.deleteById(id);
    }

}
