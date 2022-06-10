package com.example.WebSocketsServer.Config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

public class ServerWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        //отпрака сообщения обратно
        //session.sendMessage(new TextMessage(response));

        JSONObject jsonObject = new JSONObject(message.getPayload());

        final JSONArray data = jsonObject.getJSONArray("test");

        for (int i = 0; i < data.length(); ++i) {
            final JSONObject person = data.getJSONObject(i);
            System.out.println(person.getString("name"));
            System.out.println(person.getString("msg"));
        }
    }
}
