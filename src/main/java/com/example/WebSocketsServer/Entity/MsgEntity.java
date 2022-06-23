package com.example.WebSocketsServer.Entity;

import lombok.Data;
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
//    @Column(name = "[MsgDate")
    private Date MsgDate;
    private String Msg;
    private int Status;

    public MsgEntity() {
    }

    public MsgEntity(String nameTo, int tabTo, String nameFrom, int tabFrom, Date date, String msg, int status) {
        NameTo = nameTo;
        TabTo = tabTo;
        NameFrom = nameFrom;
        TabFrom = tabFrom;
        MsgDate = date;
        Msg = msg;
        Status = status;
    }

}
