package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
        Socket s = new Socket("localhost", 2750);

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());

        System.out.println("Lista note: premi il numero corrispondente per eseguire una delle seguenti azioni");
        System.out.println("1-Aggiungi una nota");
        System.out.println("2-Lista note");
        System.out.println("3-Chiudi il programma");

        String stringaDaInviare = tastiera.readLine();

        do{
            switch (stringaDaInviare) {
                case "1": 
                    stringaDaInviare += '\n';
                    out.writeBytes(stringaDaInviare);
                    System.out.println("Inserisci la nota da aggiugere");
                    stringaDaInviare = tastiera.readLine();
                    stringaDaInviare += '\n';
                    out.writeBytes(stringaDaInviare);
                    break;
                case "2": 
                    out.writeBytes("@"+"\n");
                    String sizeArray = in.readLine();
                    for (int i = 0; i < Integer.parseInt(sizeArray); i++) {
                        System.out.println(in.readLine());
                    }
                    break;
                case "3": 
                    stringaDaInviare += '\n';
                    out.writeBytes(stringaDaInviare);
                    s.close(); 
                    System.exit(0);
                    break;
                default: System.out.println("Devi inserire un numero tra i seguenti: 1-2-3");break;
            }
            System.out.println("Lista note: premi il numero corrispondente per eseguire una delle seguenti azioni");
            System.out.println("1-Aggiungi una nota");
            System.out.println("2-Lista note");
            System.out.println("3-Chiudi il programma");
            stringaDaInviare = tastiera.readLine();
        }while (!stringaDaInviare.equals("3"));    
        }
         catch (Exception e) {
            e.printStackTrace();
        }
    }
}