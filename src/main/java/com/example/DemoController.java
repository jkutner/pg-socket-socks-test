package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.net.SocketFactory;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 */
@RestController
public class DemoController {

    @RequestMapping("/")
    public String home() {
        return "Hello world<br/><br/>To see problem, go to /test?host=HOST&post=PORT";
    }

    @RequestMapping("/test")
    public String test(@RequestParam("host") String host, @RequestParam("port") Integer port) {
        try {
            SocketFactory socketFactory = SocketFactory.getDefault();
            Socket socket = socketFactory.createSocket();
            socket.connect(new InetSocketAddress(host, port), 10000);
            return "Done!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
