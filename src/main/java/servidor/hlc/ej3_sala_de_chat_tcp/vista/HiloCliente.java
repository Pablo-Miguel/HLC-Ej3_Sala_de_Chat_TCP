/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.hlc.ej3_sala_de_chat_tcp.vista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private String chatGrupal;

    public HiloCliente(Socket cliente, JTextArea txtChatGrupal) throws IOException {
        this.cliente = cliente;
        this.txtChatGrupal = txtChatGrupal;
        
    }

    @Override
    public void run() {

        try {
            
            while (true) {
                BufferedReader flujoEntrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                chatGrupal = flujoEntrada.readLine();
                
                if (chatGrupal != null) {
                    txtChatGrupal.setText(chatGrupal);
                    txtChatGrupal.updateUI();
                }
                
            }

        } catch (IOException ex) {

            Logger.getLogger(HiloCliente.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
