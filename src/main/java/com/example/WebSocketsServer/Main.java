package com.example.WebSocketsServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) {
        System.out.println(textSend());
    }

    private static JSONObject textSend() {
        JSONObject student1 = new JSONObject();
        student1.put("user", "name");
        student1.put("pass", "pass");
        return student1;
    }
}
