package com.example;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    private Socket s;
    private ArrayList<String> listaNote;
    
    public ServerThread(Socket s, ArrayList<String> listaNote){
        this.s = s;
        this.listaNote = listaNote;
    }  

    @Override
    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String stringaRicevuta;
            boolean connesioneOn = true;
            do  {
                stringaRicevuta = in.readLine();
                System.out.println("Input client" + this.getName() +stringaRicevuta);
                switch(stringaRicevuta){
                case "1": 
                    System.out.println("1"); 
                    stringaRicevuta = in.readLine();
                    this.listaNote.add(stringaRicevuta);
                    System.out.println(this.getName()+" a aggiunto roba alle sue note");
                    break;
                case "@": 
                    System.out.println("2"); 
                    out.writeBytes(String.valueOf(this.listaNote.size())+"\n");
                    for (String i : this.listaNote) {
                        System.out.println("Inviato :" + i);
                        i += '\n';
                        out.writeBytes(i);
                    }
                    break;
                case "3":
                    connesioneOn = false;
                break;
            }    
            }while (connesioneOn);
        } catch (Exception e) {}
    }
}
