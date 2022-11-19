/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.hlc.ej3_sala_de_chat_tcp.cliente;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author Dam
 */
public class HiloCliente extends Thread {

    private Socket cliente;
    private JTextArea txtChatGrupal;
    private String chatGrupal;
    private DataInputStream flujoEntrada;
    private Boolean salir = false;

    public HiloCliente(Socket cliente, JTextArea txtChatGrupal) throws IOException {
        this.cliente = cliente;
        this.txtChatGrupal = txtChatGrupal;
        flujoEntrada = new DataInputStream(cliente.getInputStream());
    }

    @Override
    public void run() {

        try {
            
            while (!salir) {
                chatGrupal = flujoEntrada.readUTF();
                
                if (chatGrupal != null) {
                    
                    if(chatGrupal.equals("/exit")){
                        salir = true;
                    }
                    else{
                        txtChatGrupal.setText(chatGrupal);
                    }
                        
                } else {
                    throw new IOException("Ha ocurrido un error con el chat");
                }
                
            }
            
            flujoEntrada.close();
            cliente.close();

        } catch (IOException ex) {
            
            System.out.println("Se ha cerrado el socket");
            //Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    
}
