package top.elichn.websocket.test;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.elichn.websocket.WebSocketSubject;

/**
 * <p>Title: WebSocketTest</p>
 * <p>Description: 服务端推送消息测试代码 每隔2分钟向客户端发送消息</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2018/12/6 11:13
 */
@Component
public class WebSocketTest {

    private final static Logger LOG = LoggerFactory.getLogger(WebSocketTest.class);

    @Scheduled(cron = "10 */2 * * * ?")
    public synchronized void notifyTest() {
        LOG.info("WebSocketTest start");
        String principal = "1";
        String type = "radio";
        JSONObject data = new JSONObject();
        data.put("title", "test web socket");
        data.put("content", "Hi client, Have you recieve this msg?  this msg from server.");
        // 通知客户端
        WebSocketSubject subject = WebSocketSubject.Holder.getSubject(principal);
        subject.notify(type, data.toJSONString());
        LOG.info("WebSocketTest end...");
    }
}
