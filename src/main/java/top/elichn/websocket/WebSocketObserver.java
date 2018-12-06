package top.elichn.websocket;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * <p>Title: WebSocketObserver</p>
 * <p>Description: WebSocket 观察者 重写equals方法</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2018/12/6 11:13
 */
public class WebSocketObserver implements Observer {

    /**
     * WebSocket session
     */
    private Session session;

    public WebSocketObserver(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    @Override
    public void update(Observable observer, Object arg) {
        String message = (String) arg;
        try {
            if (session.isOpen()) {
                session.getBasicRemote().sendText(message);
            }
        } catch (IOException e) {
            throw new RuntimeException("websocket sendText error", e);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((session == null) ? 0 : session.getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        WebSocketObserver other = (WebSocketObserver) obj;
        if (session == null) {
            if (other.session != null) {
                return false;
            }
        }
        return session.getId().equals(other.session.getId());
    }

}
