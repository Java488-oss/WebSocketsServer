package com.example.WebSocketsServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static Timer timer = new Timer();
    public static void main(String[] args) {
        GetStateUser();
    }

    public static void GetStateUser() {

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("test "+new Date());
                        GetStateUser();
                    }
                },
                1000
        );

    }
}
