/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package servidor.hlc.ej3_sala_de_chat_tcp.servidor;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Nitro
 */
public class Panel_Servidor extends javax.swing.JPanel {
    
    private Servidor servidor;
    
    /**
     * Creates new form Panel_Servidor
     */
    public Panel_Servidor() throws IOException {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIntro = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();
        btnEncenderApagar = new javax.swing.JButton();
        lblOnline = new javax.swing.JLabel();

        lblIntro.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblIntro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIntro.setText("PANEL DE CONTROL SERVIDOR");

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setRows(5);
        jScrollPane1.setViewportView(txtChat);

        btnEncenderApagar.setLabel("Encender");
        btnEncenderApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncenderApagarActionPerformed(evt);
            }
        });

        lblOnline.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblOnline.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblOnline.setText("Personas online en el chat de texto: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIntro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEncenderApagar, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIntro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEncenderApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOnline, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEncenderApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncenderApagarActionPerformed
        
        try {
            
            if(btnEncenderApagar.getText().equals("Encender")){
                servidor = new Servidor(this);
                servidor.start();
                btnEncenderApagar.setText("Apagar");
            }
            else{
                if(Servidor.listaConexiones.size() == 0){
                    servidor.setSalir(Boolean.TRUE);
                    System.exit(0);
                }
                else{
                    JOptionPane.showMessageDialog(this, "Hay personas conecctadas al chat, no se puede cerrar el servidor");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Panel_Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnEncenderApagarActionPerformed

    public JTextArea getTxtChat() {
        return txtChat;
    }

    public void setTxtChat(JTextArea txtChat) {
        this.txtChat = txtChat;
    }

    public JLabel getLblOnline() {
        return lblOnline;
    }

    public void setLblOnline(JLabel lblOnline) {
        this.lblOnline = lblOnline;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEncenderApagar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIntro;
    private javax.swing.JLabel lblOnline;
    private javax.swing.JTextArea txtChat;
    // End of variables declaration//GEN-END:variables

}