/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.accounting;

import classes.Accounts;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author HTC
 */
public class SabtRouzname extends javax.swing.JFrame {

    int doc;
    int date;
    String[][] data;
    Accounts accounts = new Accounts();
    int bedehkarId;
    int bestankarId;
    double bedehkarMande;
    double bestankarMande;
    String tozih;

    /**
     * Creates new form SabtRouzname
     */
    public SabtRouzname() {
        initComponents();
        getDocId();
        getDate();
        getAccounts();
        mandeBedehkar();
        mandeBestankar();

        jComboBox1.addItemListener(new BedehkarItemListener());
        jComboBox2.addItemListener(new BestankarItemListener());
        jLabel10.setText("");
    }

    public class BedehkarItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            jLabel10.setText(" ");
            int accountId;
            String account = jComboBox1.getSelectedItem().toString();
            account = account.substring(account.indexOf("-") + 1, account.indexOf("/"));
            accountId = Integer.parseInt(account);
            if (accounts.hasTafsili(accountId)) {
                jComboBox1.setSelectedIndex(0);
                jLabel10.setText("حساب تفصیلی دارد.  حساب تفصیلی را انتخاب کنید");
            }
            mandeBedehkar();

        }

    }

    public class BestankarItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            jLabel10.setText(" ");
            int accountId;
            String account = jComboBox2.getSelectedItem().toString();
            account = account.substring(account.indexOf("-") + 1, account.indexOf("/"));
            accountId = Integer.parseInt(account);
            if (accounts.hasTafsili(accountId)) {
                jComboBox2.setSelectedIndex(0);
                jLabel10.setText("حساب تفصیلی دارد.  حساب تفصیلی را انتخاب کنید");

            }
            mandeBestankar();

        }

    }

    public void getDocId() {
        doc = accounts.createDoc();
        jLabel8.setText(String.valueOf(doc));
    }

    public void getDate() {
        Date date = new Date();
        SimpleDateFormat fd = new SimpleDateFormat("yyyy/MM/dd");
        jLabel2.setText(fd.format(date));
        SimpleDateFormat fd2 = new SimpleDateFormat("yyyyMMdd");
        this.date = Integer.valueOf(fd2.format(date).toString());

    }

    public void getAccounts() {
        classes.TafsiliAccounts tac = new classes.TafsiliAccounts();
        data = tac.getAccounts2();
        for (int i = 0; i < data.length; i++) {
            int tafsiliId = Integer.parseInt(data[i][1]);
            int groupId = Integer.parseInt(data[i][3]);
            int moeinId = Integer.parseInt(data[i][4]);

            String tafsili = data[i][2];
            String group = accounts.getTfsiliGroup(groupId);
            String moein = accounts.getMoein(moeinId);
            jComboBox1.addItem(tafsili + "-" + tafsiliId + "/" + group + "-" + groupId + "/" + moein + "-" + moeinId);
            jComboBox2.addItem(tafsili + "-" + tafsiliId + "/" + group + "-" + groupId + "/" + moein + "-" + moeinId);
        }

        classes.MoeinAccounts ac = new classes.MoeinAccounts();

        data = ac.getAccounts();
        for (int i = 0; i < data.length; i++) {

            int moeinId = Integer.parseInt(data[i][1]);
            int totalId = Integer.parseInt(data[i][4]);
            int totalGroupId = accounts.getTotalGroupId(totalId);

            String moein = data[i][2];
            String total = accounts.getTotalById(totalId);
            String totalGroup = accounts.getTotalGroupById(totalGroupId);
            jComboBox1.addItem(moein + "-" + moeinId + "/" + total + "-" + totalId + "/" + totalGroup + "-" + totalGroupId);
            jComboBox2.addItem(moein + "-" + moeinId + "/" + total + "-" + totalId + "/" + totalGroup + "-" + totalGroupId);

        }

    }

    public void mandeBedehkar() {
        double mande;
        int accountId;
        String account = jComboBox1.getSelectedItem().toString();
        account = account.substring(account.indexOf("-") + 1, account.indexOf("/"));
        accountId = Integer.parseInt(account);
        mande = accounts.getMande(accountId);
        jTextField3.setText(mande + "");
        bedehkarId = accountId;
        bedehkarMande = mande;
    }

    public void mandeBestankar() {
        int accountId;
        double mande;
        String account = jComboBox2.getSelectedItem().toString();
        account = account.substring(account.indexOf("-") + 1, account.indexOf("/"));
        accountId = Integer.parseInt(account);
        mande = accounts.getMande(accountId);
        jTextField4.setText(mande + "");
        bestankarId = accountId;
        bestankarMande = mande;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ثبت روزنامه(سند)");
        setAutoRequestFocus(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("سریال");

        jLabel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setBackground(new java.awt.Color(204, 255, 255));
        jLabel2.setText("21/9/96");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("تاریخ");

        jLabel3.setText("مبلغ");

        jLabel5.setText("بدهکار");

        jLabel6.setText("بستاکار");

        jLabel7.setText("شرح");

        jButton1.setText("کپی نام حسابها در شرح");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setText("مانده");

        jTextField3.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jTextField3.setForeground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jTextField3.setDisabledTextColor(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"));
        jTextField3.setEnabled(false);
        jTextField3.setSelectedTextColor(javax.swing.UIManager.getDefaults().getColor("Button.background"));

        jTextField4.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.background"));
        jTextField4.setForeground(new java.awt.Color(204, 204, 204));
        jTextField4.setDisabledTextColor(javax.swing.UIManager.getDefaults().getColor("Button.darkShadow"));
        jTextField4.setEnabled(false);

        jButton2.setText("ثبت");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("انصراف");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 102, 102));
        jLabel10.setText("jLabel10");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(75, 75, 75)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel1)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField2)
                                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(75, 75, 75)
                        .addComponent(jButton2)
                        .addGap(231, 231, 231))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(227, 227, 227))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addGap(5, 5, 5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jButton1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        tozih = jTextField2.getText() + "(" + jComboBox1.getSelectedItem() + ") " + "(" + jComboBox2.getSelectedItem() + ")";
        jTextField2.setText(tozih);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        boolean error = false;
        double mablagh = 0;
        if (bedehkarId == bestankarId) {
            JOptionPane.showMessageDialog(null, " طرف بدهکار و بستانکار یک حساب می باشد");
            error = true;
        }
        if (jTextField1.getText().isEmpty() && !error) {
            JOptionPane.showMessageDialog(null, " مبلغ وارد نشده");
            error = true;
        } else if (!error) {
            try {
                mablagh = Double.parseDouble(jTextField1.getText());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, " مقدار معتبر وارد کنید");
                jTextField1.setText("");
                error = true;
            }
        }

        if (!error) {
            tozih = jTextField2.getText();
            accounts.sabteRouzname(doc, bedehkarId, bestankarId, bedehkarMande, bestankarMande, mablagh, tozih, date);
            getDocId();
            jTextField1.setText("");
            jTextField2.setText("");
            mandeBedehkar();
            mandeBestankar();
            //mande= mande+bedehkar   mande=mande-bestankar  ya   mande+bedehkar   agar manfi shod dakhele parantez (be sourate mosbat)
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(SabtRouzname.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(SabtRouzname.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(SabtRouzname.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(SabtRouzname.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SabtRouzname().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
