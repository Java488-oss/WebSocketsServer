package com.example.WebSocketsServer.Service;

import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Resourse.UserRepoCustom;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
public class UserRepoImpl implements UserRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<UserEntity> getUserByName(String name, String pass) {

        String hql="from UserEntity where user='"+name+"' and pass='"+pass+"'";
        return entityManager.createQuery(hql, UserEntity.class).getResultList();
    }

    @Override
    public List<UserEntity> updateUserState(String pass) {

        String hql="from UserEntity where pass='"+pass+"'";
        return entityManager.createQuery(hql, UserEntity.class).getResultList();

    }

}
