/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.hlc.ej3_sala_de_chat_tcp.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
    private String nick, mensajeCliente;
    private Panel_Servidor panel;

    public HiloServidor(Socket conexionCliente, Panel_Servidor panel) throws IOException {
        this.conexionCliente = conexionCliente;
        this.panel = panel;
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
                    mensajeCliente = flujoEntrada.readUTF();
                    
                    if(mensajeCliente.equals("/exit")){
                        salir = true;
                        chatGrupal += "Ha salido " + nick + " de la sala>\n";
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
                
                panel.getLblOnline().setText("Personas online en el chat de texto: " + Servidor.listaConexiones.size());
                panel.getTxtChat().setText(chatGrupal);
                
            }
            
            flujoSalida.writeUTF(mensajeCliente);
            flujoSalida.flush();
            
            flujoEntrada.close();
            flujoSalida.close();
            difusionSalida.close();
            conexionCliente.close();
            
        } catch (IOException ex) {
            
            System.out.println("Se ha cerrado el socket");
            //Logger.getLogger(HiloServidor.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
}
