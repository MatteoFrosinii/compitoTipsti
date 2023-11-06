package com.example;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2750);
            while(true){
                Socket s = server.accept();
                ArrayList<String> listaNote = new ArrayList<String>();
                ServerThread thread = new ServerThread(s, listaNote);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}