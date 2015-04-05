package package1;

import package0.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableColumn;
import static package0.Face1.Boot_DB;
import static package0.Face1.stmt;
import static package0.Face1.table;


public class Face5 extends Face1 {
    
    public Vector<Object> columnNames = new Vector<Object>();
    public Vector<Object> dados = new Vector<Object>();
    
    JFrame frameModer, confFrame;
    JButton jButton1, jButton2, jButton3;
    public JTable tablesMod;
    
    DefaultTableModel modelMod = new DefaultTableModel(dados, columnNames){
        public Class getColumnClass(int column){
            for(int row = 0; row < getRowCount(); row++){
                Object o = getValueAt(row, column);
                
                if(o != null){
                    return o.getClass();
                }
            }
            
            return Object.class;
        }
        @Override
        public boolean isCellEditable(int row, int column){
            if(column < 4) return true;
            return false;
        }
        
    };
    
    public void functionTable(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        //System.out.println(columns);
        for(int i = 1; i <= columns; i++){
            columnNames.addElement(md.getColumnName(i));
        }
        
        while(rs.next()){
            Vector<Object> row = new Vector<Object>(columns);
            
            for(int i = 1; i <= columns; i++){
                row.addElement(rs.getObject(i));
            }
            dados.addElement(row);
        }
    }
    
    public void initializer(ResultSet rs) throws SQLException {
        
        frameModer = new JFrame("Modify");
        frameModer.setVisible(true);
        frameModer.setDefaultCloseOperation(frameModer.getDefaultCloseOperation());
        
        functionTable(rs);
        tablesMod = new JTable(modelMod);
        
        tablesMod.removeColumn(tablesMod.getColumnModel().getColumn(6));
        tablesMod.removeColumn(tablesMod.getColumnModel().getColumn(6));
        
        JScrollPane scrollPaneMod = new JScrollPane(tablesMod);
        
        scrollPaneMod.setViewportView(tablesMod);
        
        jButton1 = new JButton("Cancel");
        jButton1.addActionListener(this);
        
        jButton2 = new JButton("Delet");
        jButton2.addActionListener(this);
        
        jButton3 = new JButton("Refresh");
        jButton3.addActionListener(this);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frameModer.getContentPane());
        frameModer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(scrollPaneMod, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPaneMod, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton3)
                                        .addComponent(jButton2)
                                        .addComponent(jButton1))
                                .addContainerGap())
        );
                
        tablesMod.getModel().addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if(e.getType() == e.UPDATE){
                    try {
                        Date date = new Date();
                        SimpleDateFormat format = new SimpleDateFormat("E dd/MM/yyyy 'às' HH:mm:ss");
                        stmt = Boot_DB.createStatement();
                        ResultSet resultSet = stmt.executeQuery("SELECT * FROM " + table);
                        int row = e.getFirstRow();
                        int column = e.getColumn();
                        TableModel model = (TableModel)e.getSource();
                        String columnName = model.getColumnName(column);
                        Object data = model.getValueAt(row, column);
                        int viewRow = tablesMod.getSelectedRow();
                        int modelRow = tablesMod.convertRowIndexToModel(viewRow);
                        
                        stmt.execute("update " + table
                                + " set " + columnName + " ='" + data
                                + "' where ID = " + tablesMod.getModel().getValueAt(modelRow,6));
                        stmt.execute("update " + table
                                + " set Modification = '" + format.format(date)
                                + "' where ID = " + tablesMod.getModel().getValueAt(modelRow,6));
                        
                        stmt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(Face5.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        frameModer.pack();
    }
    
    public void deletFrame(){
        confFrame = new JFrame("Confirm deletion");
        JLabel jLabel1 = new javax.swing.JLabel();
        JButton jButton11 = new javax.swing.JButton();
        JButton jButton22 = new javax.swing.JButton();
        
        confFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jLabel1.setFont(new java.awt.Font("Microsoft JhengHei", 1, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Want to delet data?");
        
        jButton11.setText("No");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {    
                confFrame.dispose();
            }
        });
        
        jButton22.setText("Yes");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int rows[] = tablesMod.getSelectedRows();
                for (int i = 0; i < rows.length; i++) {
                    try {
                        stmt = Boot_DB.createStatement();
                        stmt.execute("delete from " + table
                                + " where ID = " + tablesMod.getModel().getValueAt(rows[i],6));
                        frameModer.dispose();
                        Face5 novo = new Face5(stmt.executeQuery("select * from " + table));
                        if(i < rows.length-1) novo.frameModer.dispose();
                        stmt.close();
                    } catch (SQLException | FileNotFoundException ex) {
                        Logger.getLogger(Face5.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                confFrame.dispose();
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(confFrame.getContentPane());
        confFrame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(56, Short.MAX_VALUE)
                                .addComponent(jButton22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton11)
                                        .addComponent(jButton22))
                                .addContainerGap())
        );
        
        confFrame.setVisible(true);
        confFrame.pack();
    }
    
    public Face5(ResultSet rs) throws FileNotFoundException, SQLException {
        initializer(rs);
    }
    
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==jButton1){
            frameModer.dispose();
        }
        
        else if(e.getSource()==jButton2){
            deletFrame();
        }
        
        else if(e.getSource()==jButton3){
            frameModer.dispose();
            try {
                stmt = Boot_DB.createStatement();
                new Face5(stmt.executeQuery("select * from " + table));
                stmt.close();
            } catch (SQLException | FileNotFoundException ex) {
                Logger.getLogger(Face5.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
}