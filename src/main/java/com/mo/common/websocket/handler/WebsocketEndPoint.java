package com.mo.common.websocket.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * <p>Title:  </p>
 * <p>Date: 2015-12-27 14:56 </p>
 *
 * @author <a href="mailto: ">mo</a>
 * @version 1.0.1
 */
public class WebsocketEndPoint extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        TextMessage returnMessage = new TextMessage(message.getPayload()+" received at server");
        session.sendMessage(returnMessage);
    }
}
