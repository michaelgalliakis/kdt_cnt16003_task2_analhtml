 /**
  * TEI of Athens, Department of Informatics
  * Master of Science in Computing and Network Technologies
  * Distributed Web Applications, Task 2 Ενδεικτική λύση,
  * Subject: Ανάλυση κώδικα HTML ιστοσελίδας.
  * @author Michael Galliakis AM: CNT16003.
  * Ημερομηνία : 3/12/2016
  */
package kdt.task2.analhtml;

import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FMain extends javax.swing.JFrame {
    static final String TITLE = "Michael Galliakis CNT 16003 * KDT * Task 2 Analysis HTML" ;
    
    AnalysisHTMLFile anaHTMLFile ;
    public FMain() {
        initComponents();
        anaHTMLFile = new AnalysisHTMLFile();
        
        tpView.setText(TITLE);
        tpView.setEditable(false);
        anaHTMLFile.setOutputTextPane(tpView) ;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lURL = new javax.swing.JLabel();
        bAnalysis = new javax.swing.JButton();
        tfURL = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tpView = new javax.swing.JTextPane();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lURL.setText("Give URL:");
        lURL.setToolTipText("");

        bAnalysis.setText("Analysis");
        bAnalysis.setToolTipText("Πάτα το για ανάλυση!");
        bAnalysis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bAnalysisMouseClicked(evt);
            }
        });

        tfURL.setText("www.teiath.gr");
        tfURL.setToolTipText("Πάτα \"Enter\" για ανάλυση!");
        tfURL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfURLKeyPressed(evt);
            }
        });

        jScrollPane1.setViewportView(tpView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lURL, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bAnalysis)
                    .addComponent(tfURL, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lURL, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAnalysis)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    static private String fixURL(String url)
    {
        if (url.toLowerCase().startsWith("http://") ||url.toLowerCase().startsWith("https://"))
            return url;
        else
            return "http://" + url ;
    }
    
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    static private boolean isBusy = false ;
    private void reloadAndPrint()
    {
        if (!isBusy){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    anaHTMLFile.clearOutputTextPane() ;
                    anaHTMLFile.printMessage(TITLE);
                    anaHTMLFile.printMessage("Busy...");
                    isBusy = true ;
                    AnalysisHTMLFile.ReloadStatus rs = anaHTMLFile.reload(fixURL(tfURL.getText())) ;
                    isBusy = false ;
                    if (rs==AnalysisHTMLFile.ReloadStatus.ok)
                    {
                        anaHTMLFile.printMessage("The job is done!");
                        anaHTMLFile.printMessage(AnalysisHTMLFile.getMessageRelSta(rs));
                        anaHTMLFile.printAll();
                    }
                    else
                    {
                        anaHTMLFile.printMessage("Finished with Error!");
                        anaHTMLFile.printMessage(AnalysisHTMLFile.getMessageRelSta(rs));
                    }
                }
            });
        }
        else
            JOptionPane.showMessageDialog(this, "Είναι busy!!","Message",JOptionPane.INFORMATION_MESSAGE) ;
        
    }
    private void bAnalysisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAnalysisMouseClicked
        reloadAndPrint() ;
    }//GEN-LAST:event_bAnalysisMouseClicked
    
    private void tfURLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfURLKeyPressed
        if (evt.getKeyCode() ==  KeyEvent.VK_ENTER)
        {
            reloadAndPrint();
        }
    }//GEN-LAST:event_tfURLKeyPressed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FMain tmp = new FMain();
                tmp.setExtendedState(tmp.getExtendedState()|JFrame.MAXIMIZED_BOTH);
                tmp.setVisible(true);
                
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAnalysis;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lURL;
    private javax.swing.JTextField tfURL;
    private javax.swing.JTextPane tpView;
    // End of variables declaration//GEN-END:variables
}
