# springboot-websocket
[![JDK 1.8](https://img.shields.io/badge/JDK-1.8-green.svg "JDK 1.8")]()

a simple websocket demo for springboot（观察者模式）

利用[j2ee-websocket](https://github.com/svili365/j2ee-websocket.git)修改成springboot版本的websocket。


## websocket简介

websocket协议是基于TCP的一种新的网络协议。它实现了浏览器与服务器全双工（full-duplex）通信，**允许服务器主动发送信息给客户端**。

* 与HTTP协议有着良好的兼容性。默认端口也是80和443。

* 协议标识符是ws（如果加密，则为wss）。

## 运行

jquery.websocket增加了消息的类型，将消息拆分为{"type":"","message":""}。
这样更灵活，可以根据业务类型，定义type，如：通知，公告，广播，发文等...

运行：spring-boot:run

访问：http://localhost:8080/index.html





