package network.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * java 原生socket 测试  服务端
 */
public class MySocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socketServer = new ServerSocket(2000);
        System.out.println("服务端已准备好");
        System.out.println("服务器信息：" + socketServer.getInetAddress() + "端口号：" + socketServer.getLocalPort());

        for (; ; ) {
            Socket clientSocket = socketServer.accept();
            ClientHandle clientHandle = new ClientHandle(clientSocket);
            clientHandle.start();

        }
    }

    static class ClientHandle extends Thread {

        private Socket socket;

        private boolean flag = true;

        public ClientHandle(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("新客户端信息：" + socket.getInetAddress() + "端口号：" + socket.getPort());
            try {
                // 获取socket输出流，用于回送数据
                OutputStream socketOutput = socket.getOutputStream();
                // 输出流转换成打印流，用于屏幕展示
                PrintStream printStream = new PrintStream(socketOutput);

                // 获取输入流，接受客户端请求信息
                InputStream socketInput = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketInput));

                do {
                    // 客户端拿到一条数据
                    String inputStr = bufferedReader.readLine();
                    if ("bye".equalsIgnoreCase(inputStr)) {
                        System.out.println(inputStr);
                        flag = false;
                        // 回送
                        printStream.println("bye");
                    } else {
                        System.out.println(inputStr);
                        // 回送 接受数据的长度
                        printStream.println(inputStr.length());
                    }
                } while (flag);
                printStream.close();
                bufferedReader.close();
            } catch (Exception e) {
                System.out.println("服务端异常");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
