package com.example.WebSocketsServer.Config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

public class ServerWebSocketHandlerTest extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        JSONObject jsonObject = new JSONObject(message);

        final JSONArray geodata = jsonObject.getJSONArray("");
        final int n = geodata.length();
        for (int i = 0; i < n; ++i) {
            final JSONObject person = geodata.getJSONObject(i);
            System.out.println(person.getInt("name"));
            System.out.println(person.getString("msg"));
        }
    }



//        String request = message.getPayload();
//
//        String response = String.format("response from server websockets to '%s'", HtmlUtils.htmlEscape(request));
//
//        System.out.println(response);
//        session.sendMessage(new TextMessage(response));

}
