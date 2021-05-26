package network.udp;

import network.contants.UDPContants;

import java.io.IOException;
import java.net.*;
import java.util.Objects;
import java.util.UUID;

/**
 * UDP 提供者
 */
public class UDPProvider {

    private static Provider provider;

    static void start(int port) {
        String sn = UUID.randomUUID().toString();
        Provider udpProvider = new Provider(port, sn);
        udpProvider.start();
        provider = udpProvider;
    }

    static void stop() {
        if (Objects.nonNull(provider)) {
            provider.exit();
            provider = null;
        }
    }

    static class Provider extends Thread {
        private byte[] sn;
        private boolean done = false;
        private int port;
        private DatagramSocket ds = null;

        /**
         * 缓存消息的 buffer
         */
        private byte[] buffer = new byte[128];

        public Provider(int port, String sn) {
            this.port = port;
            this.sn = sn.getBytes();
        }

        public void run() {
            try {
                // 初始化udp 监听 20000 端口，默认为设置的固定端口
                ds = new DatagramSocket(UDPContants.UDP_SERVER_PORT);
                // 接受消息的 packet
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);

                do {
                    ds.receive(datagramPacket);
                    // 获取发送者ip
                    String clientIp = datagramPacket.getAddress().getHostAddress();
                    // 发送者端口
                    int clientPort = datagramPacket.getPort();
                    // 获取接受数据总长度
                    int clientDataLen = datagramPacket.getLength();
                    // 获取接受到的数据
                    byte[] clientData = datagramPacket.getData();
                    // 判断是否为约定好的口令格式，确定是不是我们需要处理的信息
                    boolean isValid = clientDataLen >= UDPContants.head.getBytes().length + 2 + 4;
                    if (!isValid) {
                        continue;
                    }

                    int len = UDPContants.head.getBytes().length;

                } while (!done);

            } catch (IOException e) {
            } finally {

            }
        }

        /**
         * 关闭 udp
         */
        public void close() {
            if (Objects.nonNull(ds)) {
                ds.close();
                ds = null;
            }
        }

        /**
         * 退出
         */
        public void exit() {
            done = true;
            close();
        }
    }
}
