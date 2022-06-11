package com.example.WebSocketsServer.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "[User]")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "[user]")
    private String user;

    private String pass;

    public UserEntity(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public UserEntity() {
    }
}
