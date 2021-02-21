package network.socket;

import java.io.*;
import java.net.*;

/**
 * java 原生 socket 测试
 * socketClient socket客户端
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        // 设置超时时间  3000 毫秒
        socket.setSoTimeout(3000);
        // 建立连接 本机 端口号 2000
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000), 3000);
        System.out.println("已经发起服务器连接");
        System.out.println("客户端信息：" + socket.getLocalAddress() + "端口号：" + socket.getLocalPort());
        System.out.println("服务器信息：" + socket.getInetAddress() + "端口号：" + socket.getPort());

        try {
            send(socket);
        } catch (Exception e) {
            System.out.println("发送消息异常");
        }
        //释放资源
        System.out.println("发送完成~~~~~~");
        socket.close();
    }

    /**
     * 发布消息
     *
     * @param socketClient
     */
    private static void send(Socket socketClient) throws IOException {
        // 获取键盘输入流
        InputStream in = System.in;
        // 将键盘输入流转换成缓冲流
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        // 获取 socket 输出流
        OutputStream outputStream = socketClient.getOutputStream();
        // 将输出流转换成 打印流
        PrintStream printStream = new PrintStream(outputStream);
        // 获取socket输入流 获取服务器回送信息
        InputStream inputStream = socketClient.getInputStream();
        // 将输入流转换成缓冲流
        BufferedReader socketInput = new BufferedReader(new InputStreamReader(inputStream));
        boolean flag = true;
        do {
            // 获取键盘输入信息
            String str = input.readLine();
            // 发送输入信息
            printStream.println(str);
            // 获取服务端返回的响应信息
            String returnMessage = socketInput.readLine();
            if ("bye".equalsIgnoreCase(returnMessage)) {
                System.out.println(returnMessage);
                flag = false;
            } else {
                System.out.println(returnMessage);
            }
        } while (flag);
        input.close();
        printStream.close();
        socketInput.close();
    }
}