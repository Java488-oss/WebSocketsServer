package com.example.WebSocketsServer.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MSG")
public class MsgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "MsgId")
    private Long id;

    private String NameTo;
    private int TabTo;
    private String NameFrom;
    private int TabFrom;
    private String MsgDate;
    private String Msg;
    private int Status;
    private Boolean isSend;

    public MsgEntity() {
    }

    public MsgEntity(String nameTo, int tabTo, String nameFrom, int tabFrom, String msgDate, String msg, int status, Boolean isSend) {
        NameTo = nameTo;
        TabTo = tabTo;
        NameFrom = nameFrom;
        TabFrom = tabFrom;
        MsgDate = msgDate;
        Msg = msg;
        Status = status;
        this.isSend = isSend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTo() {
        return NameTo;
    }

    public void setNameTo(String nameTo) {
        NameTo = nameTo;
    }

    public int getTabTo() {
        return TabTo;
    }

    public void setTabTo(int tabTo) {
        TabTo = tabTo;
    }

    public String getNameFrom() {
        return NameFrom;
    }

    public void setNameFrom(String nameFrom) {
        NameFrom = nameFrom;
    }

    public int getTabFrom() {
        return TabFrom;
    }

    public void setTabFrom(int tabFrom) {
        TabFrom = tabFrom;
    }

    public String getMsgDate() {
        return MsgDate;
    }

    public void setMsgDate(String msgDate) {
        MsgDate = msgDate;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Boolean getSend() {
        return isSend;
    }

    public void setSend(Boolean send) {
        isSend = send;
    }
    
    
}
