package com.example.WebSocketsServer.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "[User]", schema = "dbo", catalog = "Test")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "[user]")
    private String user;

    private String pass;

}
