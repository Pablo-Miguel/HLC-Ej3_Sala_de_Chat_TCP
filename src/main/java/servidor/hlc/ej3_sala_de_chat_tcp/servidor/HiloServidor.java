/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.hlc.ej3_sala_de_chat_tcp.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
    private DataInputStream flujoEntrada;
    private DataOutputStream difusionSalida, flujoSalida;
    private Boolean salir = false, sesion = false;
    private String nick;

    public HiloServidor(Socket conexionCliente) throws IOException {
        this.conexionCliente = conexionCliente;
        flujoEntrada = new DataInputStream(conexionCliente.getInputStream());
        flujoSalida = new DataOutputStream(conexionCliente.getOutputStream());
    }
    
    @Override
    public void run(){
        
        try {
            
            while (!salir || Servidor.listaConexiones.size() > 0) {
                
                if(!sesion){
                    nick = flujoEntrada.readUTF();

                    chatGrupal += "Ha entrado " + nick + " a la sala>\n";
                    
                    sesion = true;
                }
                else {
                    String mensajeCliente = flujoEntrada.readUTF();
                    
                    if(mensajeCliente.equals("Ha salido " + nick + " de la sala>\n")){
                        chatGrupal += mensajeCliente;
                        flujoSalida.writeUTF(mensajeCliente);
                        flujoSalida.flush();
                        salir = true;
                        Servidor.listaConexiones.remove(conexionCliente);
                    }
                    else{
                        chatGrupal += nick + "> " + mensajeCliente + "\n";
                    }
                    
                }
                
                for(Socket cli : Servidor.listaConexiones){
                    difusionSalida =  new DataOutputStream(cli.getOutputStream());
                    difusionSalida.writeUTF(chatGrupal);
                    difusionSalida.flush();
                }
                
                System.out.println("Tamaño: " + Servidor.listaConexiones.size());
                System.out.println(chatGrupal);
                
            }
            
            System.out.println("HILO SERVIDOR CERRADO");
            
            flujoEntrada.close();
            flujoSalida.close();
            //difusionSalida.close();
            conexionCliente.close();
            
        } catch (IOException ex) {
            
            Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
}
