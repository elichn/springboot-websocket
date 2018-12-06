package top.elichn.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * <p>Title: DefaultWebSocket</p>
 * <p>Description: WebScoket服务  观察者模式</p>
 * <p>not use SSL , ws://host/{yourProjectName}/websocket/{principal}</p>
 * <p>if use SSL , wss://host/{yourProjectName}/websocket/{principal}</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2018/12/6 11:13
 */
@Component
@ServerEndpoint(value = "/websocket/{principal}")
public class DefaultWebSocket {

    private final static Logger LOG = LoggerFactory.getLogger(DefaultWebSocket.class);

    /**
     * WebSocket连接建立成功的回调方法
     */
    @OnOpen
    public void onOpen(@PathParam("principal") String principal, Session session) {
        // create observer
        WebSocketObserver observer = new WebSocketObserver(session);
        // get subject
        WebSocketSubject subject = WebSocketSubject.Holder.getSubject(principal);
        // register observer into subject
        subject.addObserver(observer);
    }

    /**
     * 服务端收到客户端发来的消息
     */
    @OnMessage
    public void onMessage(@PathParam("principal") String principal, String message, Session session) {
        // message ex:{"type":"","data":""}
        JSONObject json = JSON.parseObject(message);
        String type = json.getString("type");
        String data = json.getString("data");
        // 对消息的处理,根据义务自行定义。
        // 这里以打印消息为例
        String log = "receive msg from client,principal : " + principal + ", type : " + type + ", data : " + data;
        LOG.info("服务端收到客户端发来的消息：{}", log);
    }

    @OnClose
    public void onClose(@PathParam("principal") String principal, Session session) {
        // get subject
        WebSocketSubject subject = WebSocketSubject.Holder.getSubject(principal);
        // get observer
        WebSocketObserver observer = new WebSocketObserver(session);
        // delete observer from subject
        subject.deleteObserver(observer);
        // close session and close Web Socket connection
        try {
            if (session.isOpen()) {
                session.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("close web socket session error.", e);
        }

    }

    @OnError
    public void onError(@PathParam("principal") String principal, Session session, Throwable error) {
        throw new RuntimeException("web socket error.", error);
    }

}
