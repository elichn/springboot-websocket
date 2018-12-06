package top.elichn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p>Title: WebSocketApplication</p>
 * <p>Description: WebSocketApplication</p>
 *
 * @author elichn
 * @version 1.0
 * @date 2018/12/6 11:02
 */
@EnableScheduling
@SpringBootApplication
public class WebSocketApplication {

    private final static Logger LOG = LoggerFactory.getLogger(WebSocketApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
        LOG.info("WebSocketApplication start success..");
    }
}
