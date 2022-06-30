package com.example.WebSocketsServer.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MSG")
@Getter
@Setter
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
    }



    
    
}
