package com.how2java.tmall.util;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;

public class PortUtil {
    public static boolean testPort(int port){
        try {
            ServerSocket ss = new ServerSocket(port);
            ss.close();
            return false;
        } catch (java.net.BindException e) {
            return true;
        } catch (IOException e) {
            return true;
        }
    }

    public static void checkPort(int port,String service,boolean shutdown){
        if (!testPort(port)){
            if (shutdown){
                String message=String.format("在端口%d未检查到%S启动%n",port,service);
                JOptionPane.showMessageDialog(null,message);
                System.exit(1);
            }

        }
    }
}
