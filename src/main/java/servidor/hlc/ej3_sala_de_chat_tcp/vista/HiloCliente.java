/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.hlc.ej3_sala_de_chat_tcp.vista;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Dam
 */
public class HiloCliente extends Thread {

    private Socket cliente;
    private JTextArea txtChatGrupal;
    private String chatGrupal, nick;
    private DataInputStream flujoEntrada;
    private DataOutputStream flujoSalida;
    private Boolean salir = false;

    public HiloCliente(Socket cliente, JTextArea txtChatGrupal, String nick) throws IOException {
        this.cliente = cliente;
        this.txtChatGrupal = txtChatGrupal;
        flujoEntrada = new DataInputStream(cliente.getInputStream());
        flujoSalida = new DataOutputStream(cliente.getOutputStream());
        this.nick = nick;
    }

    @Override
    public void run() {

        try {
            
            while (!salir) {
                chatGrupal = flujoEntrada.readUTF();
                
                if (chatGrupal != null) {
                    
                    if(chatGrupal.equals("Ha salido " + nick + " de la sala>\n")){
                        salir = true;
                    }
                    else{
                        txtChatGrupal.setText(chatGrupal);
                        txtChatGrupal.updateUI();
                    }
                    
                } else {
                    throw new IOException("Ha ocurrido un error con el chat");
                }
                
            }
            
            System.out.println("HILO CLIENTE CERRADO");
            
            flujoEntrada.close();
            flujoSalida.close();
            cliente.close();

        } catch (IOException ex) {

            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
