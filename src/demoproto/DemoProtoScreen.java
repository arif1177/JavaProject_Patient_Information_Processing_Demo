/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package demoproto;


import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author akhan
 */


public class DemoProtoScreen extends javax.swing.JFrame {
    private final DBHelper dbHelper;
    private List<String[]> diabetecPatientList;
   
    private final List<PatientSummary> patientSummaryList;

    private final MBSSummary mbsSummary;
    
        /**
     * Creates new form DemoProtoScreen
     */
    public DemoProtoScreen() {
        initComponents();
        dbHelper = new DBHelper();
        this.patientSummaryList = new ArrayList<>();
        this.mbsSummary = new MBSSummary();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelDBmanipulate = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaStatus = new javax.swing.JTextArea();
        btnSelectPatient = new javax.swing.JButton();
        cmbBxDiagCodes = new javax.swing.JComboBox();
        btnProcessPatientSummary = new javax.swing.JButton();
        btnGenerateGraphData = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnDemo = new javax.swing.JButton();
        btnFilUpAdmissionDetails = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMember = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablePatientSummary = new javax.swing.JTable();
        lblPatientSummary = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        txtAreaStatus.setColumns(20);
        txtAreaStatus.setRows(5);
        txtAreaStatus.setToolTipText("Calculate total co-occurrence of MBS codes in the patients. Then write in the excel");
        jScrollPane1.setViewportView(txtAreaStatus);

        btnSelectPatient.setText("Step 1: Select patients");
        btnSelectPatient.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnSelectPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectPatientActionPerformed(evt);
            }
        });

        cmbBxDiagCodes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "E10", "E100", "E1001", "E1002", "E101", "E1011", "E1012", "E1013", "E1014", "E1015", "E1016", "E102", "E1021", "E1022", "E1029", "E103", "E1031", "E1032", "E1033", "E1034", "E1035", "E1036", "E1039", "E104", "E1040", "E1041", "E1042", "E1043", "E1049", "E105", "E1051", "E1052", "E1053", "E106", "E1061", "E1062", "E1063", "E1064", "E1065", "E1069", "E107", "E1071", "E1073", "E108", "E109", "E11", "E110", "E1101", "E1102", "E111", "E1111", "E1112", "E1113", "E1114", "E1115", "E1116", "E112", "E1121", "E1122", "E1129", "E113", "E1131", "E1132", "E1133", "E1134", "E1135", "E1136", "E1139", "E114", "E1140", "E1141", "E1142", "E1143", "E1149", "E115", "E1151", "E1152", "E1153", "E116", "E1161", "E1162", "E1163", "E1164", "E1165", "E1169", "E117", "E1171", "E1172", "E1173", "E118", "E119", "E13", "E130", "E1301", "E1302", "E131", "E1311", "E1312", "E1313", "E1314", "E1315", "E1316", "E132", "E1321", "E1322", "E1329", "E133", "E1331", "E1332", "E1333", "E1334", "E1335", "E1336", "E1339", "E134", "E1340", "E1341", "E1342", "E1343", "E1349", "E135", "E1351", "E1352", "E1353", "E136", "E1361", "E1362", "E1363", "E1364", "E1365", "E1369", "E137", "E1371", "E1372", "E1373", "E138", "E139", "E14", "E140", "E1401", "E1402", "E141", "E1411", "E1412", "E1413", "E1414", "E1415", "E1416", "E142", "E1421", "E1422", "E1429", "E143", "E1431", "E1432", "E1433", "E1434", "E1435", "E1436", "E1439", "E144", "E1440", "E1441", "E1442", "E1443", "E1449", "E145", "E1451", "E1452", "E1453", "E146", "E1461", "E1462", "E1463", "E1464", "E1465", "E1469", "E147", "E1471", "E1472", "E1473", "E148", "E149", "E23", "E230", "E231", "E232", "E233", "E236", "E237", "N25", "N250", "N251", "N258", "N259", "O24", "O240", "O241", "O242", "O243", "O244", "O245", "O249", "P70 ", "P700", "P701", "P702", "P703", "P704", "P708", "P709", "Z13 ", "Z130", "Z131", "Z132", "Z133", "Z134", "Z135", "Z136", "Z137", "Z83", "Z830", "Z831", "Z832", "Z833", "Z834", "Z835", "Z836", "Z837" }));

        btnProcessPatientSummary.setText("Step 2: Process Patient Summary");
        btnProcessPatientSummary.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnProcessPatientSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcessPatientSummaryActionPerformed(evt);
            }
        });

        btnGenerateGraphData.setText("Step 3. Generate Graph Data");
        btnGenerateGraphData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnGenerateGraphData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateGraphDataActionPerformed(evt);
            }
        });

        jLabel1.setText("Diabetec MBS codes");

        btnDemo.setText("1. Fill up patient table");
        btnDemo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDemo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDemoActionPerformed(evt);
            }
        });

        btnFilUpAdmissionDetails.setText("2. Fill up admission details");
        btnFilUpAdmissionDetails.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnFilUpAdmissionDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFilUpAdmissionDetailsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDBmanipulateLayout = new javax.swing.GroupLayout(panelDBmanipulate);
        panelDBmanipulate.setLayout(panelDBmanipulateLayout);
        panelDBmanipulateLayout.setHorizontalGroup(
            panelDBmanipulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDBmanipulateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDBmanipulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                    .addGroup(panelDBmanipulateLayout.createSequentialGroup()
                        .addGroup(panelDBmanipulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSelectPatient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnGenerateGraphData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnProcessPatientSummary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelDBmanipulateLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbBxDiagCodes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(55, 55, 55)
                        .addGroup(panelDBmanipulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDemo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnFilUpAdmissionDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(436, 436, 436)))
                .addContainerGap())
        );
        panelDBmanipulateLayout.setVerticalGroup(
            panelDBmanipulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDBmanipulateLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDBmanipulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbBxDiagCodes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDemo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDBmanipulateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelectPatient)
                    .addComponent(btnFilUpAdmissionDetails))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProcessPatientSummary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGenerateGraphData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Main", panelDBmanipulate);

        tableMember.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Member", "Membership", "Total Admission", "LoS", "Hosp Ben", "Med Ben", "Pros Ben"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMember.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMemberMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableMember);

        jTabbedPane1.addTab("Patient List", jScrollPane2);

        tablePatientSummary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Claim #", "Admission Date", "LoS", "Prin. MBS", "MBS 1", "MBS 2", "MBS 3", "MBS extra", "DRG", "Hos. Ben.", "Med. Ben.", "Pros. Ben."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablePatientSummary);

        lblPatientSummary.setText("Patient Summary");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 873, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPatientSummary)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPatientSummary)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Patient Summary", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelectPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectPatientActionPerformed
        // TODO add your handling code here:
        //put the whole diagnosis code into an string list
        List<String> diagCode = new ArrayList();
        for(int i =0;i<cmbBxDiagCodes.getItemCount();i++)
            diagCode.add(cmbBxDiagCodes.getItemAt(i).toString());
        //find patients
        this.diabetecPatientList = this.dbHelper.getPatientIDsGivenDiagCode(diagCode);
        
        PatientSummary tempPS;
        this.txtAreaStatus.append("Total " + diabetecPatientList.size() + " unique patients found with Diabetec related diagnose code\n" );
        DefaultTableModel d =  (DefaultTableModel)tableMember.getModel();
        for(int i = 0;i<diabetecPatientList.size();i++)
        {
            
            tempPS = dbHelper.getPatientSummary(diabetecPatientList.get(i)[0], diabetecPatientList.get(i)[1]);
            d.addRow(tempPS.getSummaryPatientInfoRow());
            patientSummaryList.add(tempPS);
        }
    }//GEN-LAST:event_btnSelectPatientActionPerformed

    private void tableMemberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMemberMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount() == 2)
        {
            JTable target = (JTable)evt.getSource();
            int row = target.getSelectedRow();
            String member = this.tableMember.getValueAt(row,0).toString();
            String membership = this.tableMember.getValueAt(row, 1).toString();
            System.out.println(member + "---" + membership);
            List<AdmissionSummary> ps = dbHelper.getAdmissionSummaryGivenPatientIDs(member, membership);
            DefaultTableModel d =  (DefaultTableModel)tablePatientSummary.getModel();
            d.getDataVector().removeAllElements();//delete all rows
            this.lblPatientSummary.setText("Patient Summary for member: " + member + " , membership: " + membership);
            for(int i = 0;i<ps.size();i++)
            {
                d.addRow(new Object[]{ps.get(i).claim_number, ps.get(i).date_of_incident, ps.get(i).length_of_stay, ps.get(i).principal_mbs, ps.get(i).mbs_1, 
                    ps.get(i).mbs_2, ps.get(i).mbs_3, ps.get(i).mbs_extra, ps.get(i).drg, ps.get(i).hospital_benefit, ps.get(i).medical_benefit, ps.get(i).prosthesis_benefit});
            }
        }
    }//GEN-LAST:event_tableMemberMouseClicked

    /**
     * Assuming that we have the patient list (and some basic info of each of them) that have certain DRG codes,
     * this function calculates admission info for each of the patient and based on that
     * updates MBS codes 
     * Finally it writes the MBS summary in the excel file
     * @param evt 
     */
    private void btnProcessPatientSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcessPatientSummaryActionPerformed
        int i = 0;
        for (PatientSummary ps : this.patientSummaryList) 
        {
            i++;
            ps.processPatientAndMBSSummaryfromAdmissionSummary(dbHelper.getAdmissionSummaryGivenPatientIDs(ps.memberID, ps.memberShipID),this.mbsSummary);                       
            
        }
        this.txtAreaStatus.append("\n\nWriting Excel data");
        Utility.writeExcelData(this.mbsSummary);
        this.txtAreaStatus.append("\nFinished Writing Excel data");
    }//GEN-LAST:event_btnProcessPatientSummaryActionPerformed

    private void btnGenerateGraphDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateGraphDataActionPerformed
        if(Utility.writeMBSCoOccurrenceInExcel(mbsSummary.mbsCode,patientSummaryList))
            this.txtAreaStatus.append("\n\nDone generating edge list in excel");
        else
            this.txtAreaStatus.append("\nSomething went wrong");
    }//GEN-LAST:event_btnGenerateGraphDataActionPerformed

    private void btnDemoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDemoActionPerformed
        // TODO add your handling code here:
        AdapterDemoDB add = new AdapterDemoDB();
        List<String> diagCode = new ArrayList();
        for(int i =0;i<cmbBxDiagCodes.getItemCount();i++)
            diagCode.add(cmbBxDiagCodes.getItemAt(i).toString());
        add.importAllToSQLDB(diagCode);
    }//GEN-LAST:event_btnDemoActionPerformed

    private void btnFilUpAdmissionDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFilUpAdmissionDetailsActionPerformed
        // TODO add your handling code here:
        AdapterDemoDB add = new AdapterDemoDB();
        add.insertAdmissionSummaryRecordsInMSSQL();
    }//GEN-LAST:event_btnFilUpAdmissionDetailsActionPerformed

    
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
            java.util.logging.Logger.getLogger(DemoProtoScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DemoProtoScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DemoProtoScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DemoProtoScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DemoProtoScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDemo;
    private javax.swing.JButton btnFilUpAdmissionDetails;
    private javax.swing.JButton btnGenerateGraphData;
    private javax.swing.JButton btnProcessPatientSummary;
    private javax.swing.JButton btnSelectPatient;
    private javax.swing.JComboBox cmbBxDiagCodes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblPatientSummary;
    private javax.swing.JPanel panelDBmanipulate;
    private javax.swing.JTable tableMember;
    private javax.swing.JTable tablePatientSummary;
    private javax.swing.JTextArea txtAreaStatus;
    // End of variables declaration//GEN-END:variables
}
