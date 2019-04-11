package com.example.springboot_active.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket/{platformType}/{userId}")
@Component
public class text_socket {

    public static int onlineCount = 0;    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //进行消息群发的功能
     public static CopyOnWriteArraySet<text_socket> webSocketSet = new CopyOnWriteArraySet<text_socket>();

    // 与某个客户端的连接会话，需要通过它来给客户端发送数据
     public Session session;
    // 接收参数中的用户ID
     public Long userId;
    // 接收用户的名字
     public String platformType;

    @OnOpen
    public void onOpen(Session session, @PathParam("platformType") String platformType, @PathParam("userId") Long userId)
    {
        this.session = session;
        this.userId = userId;
        this.platformType = platformType;
        webSocketSet.add(this);
        //加入set中
         addOnlineCount();
        // 在线数加1
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount() + "  userId==== " + userId + "  platformType==== " + platformType);
       try {
           sendAllMessage(platformType+" 进入聊天室");
           }
         catch (IOException e)
         {
             System.out.println("websocket IO异常");        }

         }

    @OnMessage//接受客户数据，
    public void text(String message, Session session) throws IOException {

         System.out.println("来自客户端的消息|:" + message);
        message="<font color='red'>"+platformType+"</font>:"+message;
        sendAllMessage(message);
    }
    @OnClose
    public void OnClose(){
        System.out.println("关闭");
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendAllMessage(String message) throws IOException {
            Iterator<text_socket> list=webSocketSet.iterator();
            String Tname=this.platformType;

            //遍历每一个去发送消息
            while(list.hasNext()){

                text_socket c=list.next();
                 //不是当前用户时推数据
//                if(!c.platformType.equals(Tname)){
                    c.session.getBasicRemote().sendText(message);
                    System.out.println("群发消息 之一-"+c.platformType);
//                }

            }
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        text_socket.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        text_socket.onlineCount--;
    }

}
