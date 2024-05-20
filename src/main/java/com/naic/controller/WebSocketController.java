package com.naic.controller;

import com.alibaba.fastjson.JSON;
import com.naic.common.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@ServerEndpoint("/ws/")
public class WebSocketController {
    //    存储当前对象
    public static final Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    //    建立连接
    @OnOpen
    public void onOpen(Session session, @PathParam("u") String u){
        System.out.println("谁发送:"+u);
        sessionMap.put(u,session);
        // 发送登录人员消息给所有的客户端
        sendAllMessage(setUserList());
    }
    //关闭连接
    /**
     * 1.把登出的用户从sessionMap中剃除
     * 2.发送给所有人当前登录人员信息
     */
    @OnClose
    public void onClose(@PathParam("u") String u) {
        sessionMap.remove(u);
        sendAllMessage(setUserList());
    }

    /**
     * 接收处理客户端发来的数据
     */
    @OnMessage
    public void onMessage(String message) {
//        解析消息为java对象
        Message msg = JSON.parseObject(message, Message.class);
        System.out.println(msg);
        System.out.println(msg.getTo());
        if(msg.getTo()==null){
            sendAllMessage(JSON.toJSONString(msg));
        }else{
            Session sessionTo = sessionMap.get(msg.getTo());
            System.out.println(message);
            System.out.println(sessionTo);
            sendMessage(message,sessionTo);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        System.out.println(session);
        error.printStackTrace();
    }

    private String setUserList(){
        ArrayList<String> list = new ArrayList<>();
        for(String key:sessionMap.keySet()){
            list.add(key);
        }
        Message message = new Message();
        message.setUserNames(list);
        return JSON.toJSONString(message);
    }

    //    服务端发送消息给指定客户端
    private void sendMessage(String message, Session toSession) {
        try {
            toSession.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //   服务端发送消息给所有客户端
    private void sendAllMessage(String message) {
        try {
            for (Session session : sessionMap.values()) {
                session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}