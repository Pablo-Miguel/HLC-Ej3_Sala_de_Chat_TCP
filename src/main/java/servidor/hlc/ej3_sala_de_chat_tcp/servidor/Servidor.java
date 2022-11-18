/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.hlc.ej3_sala_de_chat_tcp.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Dam
 */
public class Servidor {
    
    public static ArrayList<Socket> listaConexiones;
    
    public static void main(String[] args) throws IOException {
        
        listaConexiones = new ArrayList<Socket>();
        
        int puerto = 6070;
        
        ServerSocket socketServidor = new ServerSocket(puerto);
        
        while(true){
            System.out.println("Escuchando en el puerto: " + socketServidor.getLocalPort());
            
            Socket conexionCliente = socketServidor.accept();
            
            listaConexiones.add(conexionCliente);
            
            HiloServidor hiloServidor = new HiloServidor(conexionCliente);
            
            hiloServidor.start();
            
        }
        
    }
}
