package package1;

import package0.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;



public class Face5 extends Face1 {
    
    public Vector<Object> columnNames = new Vector<Object>();
    public Vector<Object> dados = new Vector<Object>();
    
    JFrame frameModer;
    JButton jButton1, jButton2;
    
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
    };
    
    public void functionTable(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        
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
        frameModer.setBounds(100, 100, 500, 350);
        frameModer.setVisible(true);
        frameModer.setDefaultCloseOperation(frameModer.getDefaultCloseOperation());
        
        functionTable(rs);
        JTable tablesMod = new JTable(modelMod);
        tablesMod.setEnabled(false);
        JScrollPane scrollPaneMod = new JScrollPane(tablesMod);
        
        scrollPaneMod.setViewportView(tablesMod);

        jButton1 = new JButton("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frameModer.dispose();
            }
        });
        jButton2 = new JButton("Apply changes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frameModer.getContentPane());
        frameModer.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        frameModer.pack();
    }
    
    public Face5(ResultSet rs) throws FileNotFoundException, SQLException {
        initializer(rs);
    }
}
