/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataFeedApi;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author jhoel
 * @version 0.1.0 Jaff Data Feed API
 * @serial JAFF-376021
 */
public class Ativo {

    public Ativo() throws IOException {

    }


    /*    protected void test(){
         if(client.isConnected()){
           try {
               Socket client = new Socket("datafeedcd3.cedrotech.com",81);
               System.out.println(client);
           } catch (IOException ex) {
               Logger.getLogger(Ativo.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("Servidor está off, contate o desenvolvedor");
           }
           
           
       }
        System.out.println(client);
        
    }*/
    Socket client = new Socket("datafeedcd3.cedrotech.com", 81);
    DataOutputStream dOut = new DataOutputStream(client.getOutputStream());
   
    DataInputStream  in = new DataInputStream(client.getInputStream());

    //String readLine; //leitura das linhas do servidor remoto
    //String[] separa = new String[220];// faz a leitura assim que o comando inserir é chamado e apresenta na tabela
    //String verifica; // verifica se o ativo é valido
    //efetua o login
    DataInputStream dIn = new DataInputStream(client.getInputStream());
 
    public synchronized String[] soma() throws IOException { 
        
  
        synchronized(this){
           
           String caracter = ":!";
        String[] split = dIn.readLine().split("[" + Pattern.quote(caracter) + "]");
       
        return split;  
        }
       
    }
    public synchronized String[] soma1() throws IOException { 
        
    
        synchronized(this){
           String caracter = ":!";
        String[] split = in.readLine().split("[" + Pattern.quote(caracter) + "]");
        return split;  
        }
       
    }
  

    public String login(String lo, String senha) throws SocketException, IOException {

        byte[] message;
        message = "\n".getBytes();
        dOut.write(message);

        message = lo.getBytes();
        dOut.write(message);

        message = "\n".getBytes();
        dOut.write(message);

        message = senha.getBytes();
        dOut.write(message);

        message = "\n".getBytes();
        dOut.write(message);
        dOut.flush();

        String readLine = in.readLine();

        for (int i = 0; i < 7; i++) {
            readLine = in.readLine();
            System.out.println(readLine);

        }
        String b;
        if (readLine == null) {
            b = "0";

        } else {
            b = "1";
        }

        client.setKeepAlive(true);
        return b;

    }
 
    protected synchronized String[] inserir(String a) throws IOException {
       
        String[] separa;
        
         
        /* if(separa[0] != null){
            for(int i =0; i < separa.length; i++){
               separa[i] = null;
           }
        }*/

        // String b = "sqt " + a;
        byte[] message;
        message = "\n".getBytes();
        dOut.write(message);

        message = "\n".getBytes();
        dOut.write(message);

        message = "sqt ".getBytes();
        dOut.write(message);
        
        message = a.getBytes();
        dOut.write(message);

        message = "\n".getBytes();
        dOut.write(message);

        dOut.flush();

         
        separa = soma1();

        System.out.println("Conteudo do separa " + Arrays.toString(separa));
      
        
        /*System.out.println(separa[1]);
             System.out.println(separa[44]);
             System.out.println(separa[8]);
             System.out.println(separa[10]);
         */
        return separa;

    }

    public void desassinarAtivo(String a) {
        try {
            byte[] message;

            message = "\n".getBytes();
            dOut.write(message);

            message = "usq ".getBytes();
            dOut.write(message);

            message = a.getBytes();
            dOut.write(message);

            message = "\n".getBytes();
            dOut.write(message);

            dOut.flush();

        } catch (IOException ex) {
            Logger.getLogger(Ativo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Por favor, coontate o desenvolvedor");
        }

    }

    //Fiz um looping para poder ficar lendo as linhas eternamente, 
    //dentro dele tbm serão colocados funções que precisam de constante verificação 


  

}
