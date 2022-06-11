package com.example.WebSocketsServer.Service;

import com.example.WebSocketsServer.Entity.UserEntity;
import com.example.WebSocketsServer.Resourse.UserRepoCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRepoImpl implements UserRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserEntity> getUserByName(String name) {
        String hql="from UserEntity where user='"+name+"'";
        return entityManager.createQuery(hql, UserEntity.class).getResultList();
    }
}
