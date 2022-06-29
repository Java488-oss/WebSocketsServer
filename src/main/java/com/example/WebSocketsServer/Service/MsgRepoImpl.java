package com.example.WebSocketsServer.Service;

import com.example.WebSocketsServer.Entity.MsgEntity;
import com.example.WebSocketsServer.Resourse.MsgRepoCustom;
import org.hibernate.engine.spi.ManagedEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MsgRepoImpl implements MsgRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MsgEntity> updateUserState(String date) {

        String hql="from MsgEntity where MsgDate='"+date+"'";
        return entityManager.createQuery(hql, MsgEntity.class).getResultList();

    }

    @Override
    public List<MsgEntity> updateIsOnline(String pass,Boolean state) {
//        String hql="from MsgEntity where isSend=false";
        String hql="from MsgEntity where isSend='"+state+"' and TabFrom='"+pass+"'";
        return entityManager.createQuery(hql, MsgEntity.class).getResultList();
    }

}
