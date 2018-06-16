/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smapling;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.standard.PrinterName;

import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.ZebraPrintException;
import fr.w3blog.zpl.model.ZebraPrintNotFoundException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

/**
 *
 * @author buddh
 */
public class PrinterSettings extends javax.swing.JFrame {

    /**
     * Creates new form PrinterSettings
     */
    public PrinterSettings() {
        initComponents();
    }

    public static void printZpl(String zpl, String ip, int port) throws ZebraPrintException {
        Socket clientSocket = null;
        try {
            try {
                clientSocket = new Socket(ip, port);
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                outToServer.writeBytes(zpl);
                clientSocket.close();
            } finally {
                if (clientSocket != null) {
                    clientSocket.close();
                }
            }
        } catch (IOException e1) {
            throw new ZebraPrintException("Cannot print label on this printer : " + ip + ":" + port, e1);
        }
    }

    /**
     * Function to print code Zpl to local zebra(usb)
     *
     * @param zpl code Zpl to print
     * @param ip ip adress
     * @param port port
     * @throws ZebraPrintException if zpl could not be printed
     */
    public static void printZpl(String zpl, String printerName) throws ZebraPrintException {
        try {

            PrintService psZebra = null;
            String sPrinterName = null;
            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

            for (int i = 0; i < services.length; i++) {
                PrintServiceAttribute attr = services[i].getAttribute(PrinterName.class);
                sPrinterName = ((PrinterName) attr).getValue();
                if (sPrinterName.toLowerCase().indexOf(printerName) >= 0) {
                    psZebra = services[i];
                    break;
                }
            }

            if (psZebra == null) {
                throw new ZebraPrintNotFoundException("Zebra printer not found : " + printerName);
            }
            DocPrintJob job = psZebra.createPrintJob();

            byte[] by = zpl.getBytes();
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(by, flavor, null);
            job.print(doc, null);

        } catch (PrintException e) {
            throw new ZebraPrintException("Cannot print label on this printer : " + printerName, e);
        }
    }

    /**
     * Fonction to print zebraLabel
     *
     * @param zebraLabel zebraLabel
     * @param ip ip adress
     * @param port port
     * @throws ZebraPrintException if zpl could not be printed
     */
    public static void printZpl(ZebraLabel zebraLabel, String ip, int port) throws ZebraPrintException {
        printZpl(zebraLabel.getZplCode(), ip, port);
    }

    /**
     * Fonction to print zebraLabel
     *
     * @param zebraLabel zebraLabel
     * @param ip ip adress
     * @param port port
     * @throws ZebraPrintException if zpl could not be printed
     */
    public static void printZpl(ZebraLabel zebraLabel, String printerName) throws ZebraPrintException {
        printZpl(zebraLabel.getZplCode(), printerName);
    }

    /**
     * Fonction to print multiple zebraLabel to network printer
     *
     * @param zebraLabels list of zebra labels
     * @param ip ip adress
     * @param port port
     * @throws ZebraPrintException if zpl could not be printed
     */
    public static void printZpl(List<ZebraLabel> zebraLabels, String ip, int port) throws ZebraPrintException {
        StringBuilder zpl = new StringBuilder();
        for (ZebraLabel zebraLabel : zebraLabels) {
            zpl.append(zebraLabel.getZplCode());
        }
        printZpl(zpl.toString(), ip, port);
    }

    /**
     * Fonction to print multiple zebraLabel to local printer
     *
     * @param zebraLabels list of zebra labels
     * @param ip ip adress
     * @param port port
     * @throws ZebraPrintException if zpl could not be printed
     */
    public static void printZpl(List<ZebraLabel> zebraLabels, String printerName) throws ZebraPrintException {
        StringBuilder zpl = new StringBuilder();
        for (ZebraLabel zebraLabel : zebraLabels) {
            zpl.append(zebraLabel.getZplCode());
        }
        printZpl(zpl.toString(), printerName);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTest = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstPrinters = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        btnSelectPrinter = new javax.swing.JButton();
        btnTestAny = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnTest.setText("Test Printing");
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

        lstPrinters.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstPrinters);

        jLabel1.setText("Printers");

        btnSelectPrinter.setText("Select Printer");
        btnSelectPrinter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPrinterActionPerformed(evt);
            }
        });

        btnTestAny.setText("Test Printing Other Printers");
        btnTestAny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestAnyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(btnSelectPrinter, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(194, 194, 194))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnTest)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTestAny)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnSelectPrinter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTestAny)
                    .addComponent(btnTest))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        loadPrinters();
    }//GEN-LAST:event_formWindowOpened

    private void loadPrinters() {
        Prefs.loadPrefs();
        DefaultListModel demoList = new DefaultListModel();
        PrintService[] printServices = PrinterJob.lookupPrintServices();
        for (PrintService printService : printServices) {
            String name = printService.getName();
            demoList.addElement(name);
        }
        lstPrinters.setModel(demoList);
        lstPrinters.setSelectedValue(Prefs.getPrinter(), false);
    }

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
        PrintService[] printServices = PrinterJob.lookupPrintServices();
        PrintService pservice = null;
        for (PrintService printService : printServices) {
            if (printService.getName().equals(printService.getName())) {
                pservice = printService;
            }
        }
        if (pservice != null) {
            DocPrintJob job = pservice.createPrintJob();
            String commands = "^XA\n\r^MNM\n\r^FO050,50\n\r^B8N,100,Y,N\n\r^FD1234567\n\r^FS\n\r^PQ3\n\r^XZ";
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            Doc doc = new SimpleDoc(commands.getBytes(), flavor, null);
            try {
                job.print(doc, null);
                JOptionPane.showMessageDialog(null, "Successfully sent to " + Prefs.getPrinter(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (PrintException ex) {
                JOptionPane.showMessageDialog(null, "Printer NOT available" + ex.getMessage(), "printer Error", JOptionPane.ERROR);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Printer NOT available", "printer Error", JOptionPane.ERROR);
        }

    }//GEN-LAST:event_btnTestActionPerformed

    private void btnSelectPrinterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPrinterActionPerformed
        Prefs.setPrinter(lstPrinters.getSelectedValue().toString());
        Prefs.savePrefs();
    }//GEN-LAST:event_btnSelectPrinterActionPerformed

    private void btnTestAnyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestAnyActionPerformed
        String commands = "^XA\n\r^MNM\n\r^FO050,50\n\r^B8N,100,Y,N\n\r^FD1234567\n\r^FS\n\r^PQ3\n\r^XZ";
        String defaultPrinter
                = PrintServiceLookup.lookupDefaultPrintService().getName();
        System.out.println("Default printer: " + defaultPrinter);
        PrintService service = PrintServiceLookup.lookupDefaultPrintService();

        // prints the famous hello world! plus a form feed
        InputStream is = null;
        try {
            is = new ByteArrayInputStream(commands.getBytes("UTF8"));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(PrinterSettings.class.getName()).log(Level.SEVERE, null, ex);
        }

        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        pras.add(new Copies(1));

        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        Doc doc = new SimpleDoc(is, flavor, null);
        DocPrintJob job = service.createPrintJob();

        PrintJobWatcher pjw = new PrintJobWatcher(job);
        try {
            job.print(doc, pras);
        } catch (PrintException ex) {
            Logger.getLogger(PrinterSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        pjw.waitForDone();
        try {
            is.close();
        } catch (IOException ex) {
            Logger.getLogger(PrinterSettings.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnTestAnyActionPerformed

    class PrintJobWatcher {

        boolean done = false;

        PrintJobWatcher(DocPrintJob job) {
            job.addPrintJobListener(new PrintJobAdapter() {
                public void printJobCanceled(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobCompleted(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobFailed(PrintJobEvent pje) {
                    allDone();
                }

                public void printJobNoMoreEvents(PrintJobEvent pje) {
                    allDone();
                }

                void allDone() {
                    synchronized (PrintJobWatcher.this) {
                        done = true;
                        System.out.println("Printing done ...");
                        PrintJobWatcher.this.notify();
                    }
                }
            });
        }

        public synchronized void waitForDone() {
            try {
                while (!done) {
                    wait();
                }
            } catch (InterruptedException e) {
            }
        }
    }

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
            java.util.logging.Logger.getLogger(PrinterSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrinterSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrinterSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrinterSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrinterSettings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSelectPrinter;
    private javax.swing.JButton btnTest;
    private javax.swing.JButton btnTestAny;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstPrinters;
    // End of variables declaration//GEN-END:variables
}
