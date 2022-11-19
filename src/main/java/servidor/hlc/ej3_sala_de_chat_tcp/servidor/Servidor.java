/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.hlc.ej3_sala_de_chat_tcp.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nitro
 */
public class Servidor extends Thread {
    
    public static ArrayList<Socket> listaConexiones;
    private Panel_Servidor panel;
    private Boolean salir = false;

    public Servidor(Panel_Servidor panel) throws IOException {
        this.panel = panel;
        listaConexiones = new ArrayList<Socket>();
    }

    public Boolean getSalir() {
        return salir;
    }

    public void setSalir(Boolean salir) {
        this.salir = salir;
    }
    
    @Override
    public void run(){        
        
        try {
            
            int puerto = 6070;
            
            ServerSocket socketServidor = new ServerSocket(puerto);
            
            panel.getTxtChat().setText("Servidor encendido...\nEscuchando en el puerto: " + socketServidor.getLocalPort());
            
            while(!salir){
                
                Socket conexionCliente = socketServidor.accept();
                
                listaConexiones.add(conexionCliente);
                
                HiloServidor hiloServidor = new HiloServidor(conexionCliente, panel);
                
                hiloServidor.start();
                
            }
            
            socketServidor.close();
            
        } catch (IOException ex) {
            
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }
}
