/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataFeedApi;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author jhoel
 */
public class TabelaAtivos extends javax.swing.JInternalFrame {

    /**
     * Creates new form TabelaAtivos
     *
     * @version 0.1.0 Jaff Data Feed API
     * @serial JAFF-376021
     *
     * @throws java.io.IOException
     */
    public TabelaAtivos() throws IOException {

        initComponents();
        call();
        t1 = new minhaThread();
        Thread thread = new Thread(t1);
        thread.start();
        t1.suspend();

    }
    Ativo ativo = new Ativo();
    login login;
    minhaThread t1;

    static String[] linhas = new String[30];

    private void call() { //Faz o login do servidor cedro 
        SwingWorker log = new SwingWorker() {
            @Override
            public Void doInBackground() {

                try {

                    ativo.login(login.login, login.senha);

                    ativo.client.setKeepAlive(false);

                    return null;

                    // Simulate doing something useful.
                    //Faz a troca de emojis de acordo com a resposta do servidor
                } catch (IOException ex) {
                    Logger.getLogger(TabelaAtivos.class.getName()).log(Level.SEVERE, null, ex);
                    call();
                }
                return null;

            }

        };

        log.execute();

    }
//Thread que faz a leitura de dados constante com os metodos suspend e resume integrados

    public class minhaThread implements Runnable {

        private Boolean estaSuspensa;
        DataOutputStream out;

        public minhaThread() throws IOException {

            this.estaSuspensa = false;

            //new Thread().start();
        }

        @Override
        public void run() {
            while (true) {
                 String[] leitura = null;
                 
                   synchronized (this) {
                   
                    while (estaSuspensa) {
                         

                        try {
                            wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(TabelaAtivos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    

                    }
                        
                }
                 
                 
                  try {
                    leitura = ativo.soma();
                } catch (IOException ex) {
                    Logger.getLogger(TabelaAtivos.class.getName()).log(Level.SEVERE, null, ex);
                }
              
               
                
                
                
                
                System.out.println("Leitura no loop de verificação"+Arrays.toString(leitura));
                if(leitura !=null){
                if (leitura.length < 100 && leitura[0].equalsIgnoreCase("T")) {
                    
                    for (int l = 0; l < 30; l++) {
                        
                        if (leitura[1].equalsIgnoreCase(linhas[l])) {
                            
                            //Tem que fazer aqui o tratamento das celulas da tabela
                            for (int a = 3; a < leitura.length; a += 2) {
                                switch (leitura[a]) {
                                    case "6":
                                        ++a;
                                        tabelaAtivos.setValueAt(leitura[a], l, 1);
                                        --a;
                                        break;
                                    case "44":
                                        tabelaAtivos.setValueAt(leitura[++a], l, 2);
                                        --a;
                                        break;
                                    case "18":
                                        tabelaAtivos.setValueAt(leitura[++a], l, 3);
                                        --a;
                                        break;
                                    case "120":
                                        tabelaAtivos.setValueAt(leitura[++a], l, 4);
                                        --a;
                                        break;
                                    case "100":
                                        tabelaAtivos.setValueAt(leitura[++a], l, 5);
                                        --a;
                                        break;
                                    case "10":
                                        tabelaAtivos.setValueAt(leitura[++a], l, 6);
                                        --a;
                                        break;
                                    case "8":
                                        tabelaAtivos.setValueAt(leitura[++a], l, 7);
                                        --a;
                                        break;
                                    case "42":
                                        tabelaAtivos.setValueAt(leitura[++a], l, 8);
                                        --a;
                                        break;
                                    case "108":
                                        tabelaAtivos.setValueAt(leitura[++a], l, 9);
                                        --a;
                                        break;
                                    case "192":
                                        tabelaAtivos.setValueAt(leitura[++a], l, 10);
                                        --a;
                                        break;
                                    default:
                                        break;
                                }
                            }

                        }

                    }

                }
                }

            }
        }

        void suspend() throws IOException {

    

            this.estaSuspensa = true;
            System.out.println("Suspendeu");

        }

        synchronized void resume() throws IOException {

            this.estaSuspensa = false;
            System.out.println("Inicia a Thread novamente");
            notify();
        }

    }

    //Metodo que insere o ativo pela classe ativo no metodo inserir e coloca na tabela
    private void rodaAtivo() throws IOException, InterruptedException {

        SwingWorker roda = new SwingWorker() {

            @Override
            protected Void doInBackground() throws IOException, InterruptedException {

                for (int i = 0; i < 30; i++) {

                    String[] separa;
                    String veri;
                    veri = (String) tabelaAtivos.getValueAt(i, 0);

                    if (tabelaAtivos.getValueAt(i, 0) != linhas[i]) {

                        t1.suspend();
                                
                        //reseta tudo se o usuário apagar tudo
                        if ("".equalsIgnoreCase(veri)) {
                        
                            tabelaAtivos.setValueAt(null, i, 0);
                            tabelaAtivos.setValueAt(null, i, 1);
                            tabelaAtivos.setValueAt(null, i, 2);
                            tabelaAtivos.setValueAt(null, i, 3);
                            tabelaAtivos.setValueAt(null, i, 4);
                            tabelaAtivos.setValueAt(null, i, 5);
                            tabelaAtivos.setValueAt(null, i, 6);
                            tabelaAtivos.setValueAt(null, i, 7);
                            tabelaAtivos.setValueAt(null, i, 8);
                            tabelaAtivos.setValueAt(null, i, 9);
                            tabelaAtivos.setValueAt(null, i, 10);
                            System.out.println("passout pelo laço que reseta o array");
                            if (linhas[i] != null) {

                                Boolean n;
                                      for (int a= 0; a < 30; a++) {
                                    if (linhas[i].equalsIgnoreCase(linhas[a])) {
                                        n = true;
                                        if (n) {

                                            ativo.desassinarAtivo((String) linhas[a]);
                                        }
                                    }

                                }

                            }

                            //seta o array pra null de novo
                            linhas[i] = null;

                            t1.resume();

                        } else {

                               linhas[i] = veri;

                            System.out.println(linhas[i] + " Mostrando o conteudo do array");

                            separa = ativo.inserir((linhas[i]));
                             

                            System.out.println("Mostrando o conteudo do separa da tabela ativos " + separa[0]);
                          
                            Boolean verifica = true;
                            while (verifica == true) {

                                if (!separa[0].equalsIgnoreCase("SYN")) {
                                    if (separa.length > 100) {

                                        if (separa[0].equalsIgnoreCase("E")) {
                                            if (separa[3].equalsIgnoreCase((String) tabelaAtivos.getValueAt(i, 0)) || separa[4].equalsIgnoreCase((String) tabelaAtivos.getValueAt(i, 0))) {
                                                System.out.println("Conteudo do separa dentro do E " + Arrays.toString(separa));
                                                tabelaAtivos.setValueAt(null, i, 1);
                                                tabelaAtivos.setValueAt(null, i, 2);
                                                tabelaAtivos.setValueAt(null, i, 3);
                                                tabelaAtivos.setValueAt(null, i, 4);

                                                tabelaAtivos.setValueAt(null, i, 5);

                                                tabelaAtivos.setValueAt(null, i, 6);
                                                tabelaAtivos.setValueAt(null, i, 7);
                                                tabelaAtivos.setValueAt(null, i, 8);
                                                tabelaAtivos.setValueAt(null, i, 9);
                                                tabelaAtivos.setValueAt(null, i, 10);
                                                System.out.println("passou por aqui " + linhas[i] + " numero da linha " + i);
                                                verifica = false;

                                            } else {
                                                separa = ativo.inserir((String) tabelaAtivos.getValueAt(i, 0));

                                            }
                                        } else if (separa[0].equalsIgnoreCase("T")) {
                                         
                                          
                                            if (separa[1].equalsIgnoreCase((String) tabelaAtivos.getValueAt(i, 0))) 
                                            { 
                                                if (separa.length > 100) {
                                                    
                                                    System.out.println("passou pelo laço que executa o ativo " + linhas[i] + " Executa de primeira");
                                                    linhas[i] = separa[1];
                                                    tabelaAtivos.setValueAt(separa[1], i, 0);
                                                    tabelaAtivos.setValueAt(separa[6], i, 1);
                                                    tabelaAtivos.setValueAt(separa[44], i, 2);
                                                    tabelaAtivos.setValueAt(separa[18], i, 3);
                                                    tabelaAtivos.setValueAt(separa[120], i, 4);
                                                    if (!separa[100].equalsIgnoreCase("00000000")) {
                                                        String[] converteData = separa[100].split("");
                                                        String dia = converteData[6] + converteData[7];
                                                        String mes =  converteData[4]+converteData[5];
                                                        String ano = converteData[0]+converteData[1]+converteData[2]+converteData[3];
                                                        String dataFormatada = dia +"/"+ mes + "/" + ano ;
                                                        tabelaAtivos.setValueAt(dataFormatada, i, 5);
                                                    }

                                                    tabelaAtivos.setValueAt(separa[10], i, 6);
                                                    tabelaAtivos.setValueAt(separa[8], i, 7);
                                                    tabelaAtivos.setValueAt(separa[42], i, 8);
                                                    tabelaAtivos.setValueAt(separa[108], i, 9);
                                                    tabelaAtivos.setValueAt(separa[192], i, 10);
                                                    verifica = false;
                                                    
                                                } else {
                                                    separa = ativo.inserir((String) tabelaAtivos.getValueAt(i, 0));
                                                }

                                            } else {
                                                separa = ativo.inserir((String) tabelaAtivos.getValueAt(i, 0));
                                            }
                                        } else {
                                            separa = ativo.inserir((String) tabelaAtivos.getValueAt(i, 0));

                                        }
                                    } else {
                                        separa = ativo.inserir((String) tabelaAtivos.getValueAt(i, 0));
                                    }
                                } else {
                                    separa = ativo.inserir((String) tabelaAtivos.getValueAt(i, 0));
                                }
                            }

                            t1.resume();

                        }

                    }

                }
                return null;

            }

        };

        roda.execute();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaAtivos = new javax.swing.JTable();

        setClosable(true);
        setResizable(true);
        setTitle("Tabela de Ativos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/DataFeedApi/img/logo_jaff_circuitos.png"))); // NOI18N

        jScrollPane1.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        tabelaAtivos.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        tabelaAtivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Símbolo", "Ult.", "%", "Neg", "Pex", "DtVc", "Qtdc", "QtdV", "Venda", "Tp Opção", "Op.Ref"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaAtivos.setFillsViewportHeight(true);
        tabelaAtivos.setFocusCycleRoot(true);
        tabelaAtivos.setPreferredSize(new java.awt.Dimension(469, 402));
        tabelaAtivos.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                tabelaAtivosComponentAdded(evt);
            }
        });
        tabelaAtivos.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                tabelaAtivosHierarchyChanged(evt);
            }
        });
        tabelaAtivos.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tabelaAtivosAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                tabelaAtivosAncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tabelaAtivos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabelaAtivosFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabelaAtivosFocusLost(evt);
            }
        });
        tabelaAtivos.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
                tabelaAtivosAncestorMoved1(evt);
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
            }
        });
        tabelaAtivos.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentMoved(java.awt.event.ComponentEvent evt) {
                tabelaAtivosComponentMoved(evt);
            }
        });
        tabelaAtivos.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                tabelaAtivosInputMethodTextChanged(evt);
            }
        });
        tabelaAtivos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tabelaAtivosPropertyChange(evt);
            }
        });
        tabelaAtivos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabelaAtivosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabelaAtivosKeyReleased(evt);
            }
        });
        tabelaAtivos.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                tabelaAtivosVetoableChange(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaAtivos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabelaAtivosComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_tabelaAtivosComponentAdded


    }//GEN-LAST:event_tabelaAtivosComponentAdded

    private void tabelaAtivosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tabelaAtivosPropertyChange
        // TODO add your handling code here:


    }//GEN-LAST:event_tabelaAtivosPropertyChange

    private void tabelaAtivosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabelaAtivosAncestorAdded
        // TODO add your handling code here:


    }//GEN-LAST:event_tabelaAtivosAncestorAdded

    private void tabelaAtivosAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabelaAtivosAncestorMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelaAtivosAncestorMoved

    private void tabelaAtivosInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_tabelaAtivosInputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelaAtivosInputMethodTextChanged

    private void tabelaAtivosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaAtivosKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_DOWN || evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_LEFT || evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            try {
                rodaAtivo();
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(TabelaAtivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_tabelaAtivosKeyPressed
    /*   
          Object result = tabelaAtivos.getValueAt(tabelaAtivos.getSelectedRow(), 0);
           Object l = a.linhas[tabelaAtivos.getSelectedRow()];
             
           
                  
                          
                    a.linhas[tabelaAtivos.getSelectedRow()] = tabelaAtivos.getValueAt(tabelaAtivos.getSelectedRow(), tabelaAtivos.getSelectedColumn());
                          System.out.println(a.linhas[tabelaAtivos.getSelectedRow()]);
                 
     */
    private void tabelaAtivosVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_tabelaAtivosVetoableChange
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelaAtivosVetoableChange

    private void tabelaAtivosComponentMoved(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tabelaAtivosComponentMoved
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelaAtivosComponentMoved

    private void tabelaAtivosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabelaAtivosFocusLost
        // TODO add your handling code here:


    }//GEN-LAST:event_tabelaAtivosFocusLost

    private void tabelaAtivosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabelaAtivosFocusGained
        // TODO add your handling code here:


    }//GEN-LAST:event_tabelaAtivosFocusGained

    private void tabelaAtivosHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_tabelaAtivosHierarchyChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelaAtivosHierarchyChanged

    private void tabelaAtivosAncestorMoved1(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_tabelaAtivosAncestorMoved1
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelaAtivosAncestorMoved1

    private void tabelaAtivosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabelaAtivosKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tabelaAtivosKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabelaAtivos;
    // End of variables declaration//GEN-END:variables
}
