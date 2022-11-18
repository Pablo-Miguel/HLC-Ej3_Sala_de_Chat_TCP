/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.hlc.ej3_sala_de_chat_tcp.servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dam
 */
public class HiloServidor extends Thread{
    private static String chatGrupal = "";
    private Socket conexionCliente;
    private BufferedReader flujoEntrada;
    private PrintWriter flujoSalida;
    private Boolean salir = false;
    private String nick;

    public HiloServidor(Socket conexionCliente) throws IOException {
        this.conexionCliente = conexionCliente;
        this.flujoEntrada = new BufferedReader(new InputStreamReader(conexionCliente.getInputStream()));
        this.flujoSalida =  new PrintWriter(conexionCliente.getOutputStream(), true);
    }
    
    @Override
    public void run(){
        
        try {
            
            nick = flujoEntrada.readLine();
            
            chatGrupal += "Ha entrado " + nick + " a la sala>";
            flujoSalida.println(chatGrupal);
            flujoSalida.flush();
            
            /*
            while (!salir) {                
                String mensajeCliente = flujoEntrada.readLine();
            
                chatGrupal += nick + "> " + mensajeCliente + "\n";

                PrintWriter difusionSalida = null;
                for(Socket cli : Servidor.listaConexiones){
                    difusionSalida =  new PrintWriter(cli.getOutputStream(), true);
                    difusionSalida.println(chatGrupal);
                }
                difusionSalida.close();
            }
            */
            
            flujoEntrada.close();
            flujoSalida.close();
            conexionCliente.close();
            
        } catch (IOException ex) {
            
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
}
