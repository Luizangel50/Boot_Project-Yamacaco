package package1;
import package0.*;
import java.awt.BorderLayout;
import javax.swing.*;
import java.io.FileNotFoundException;

public class Face2 extends Face1 {
  
    public Face2() throws FileNotFoundException {
        Read();
        JScrollPane jScrollPane1 = new JScrollPane();
        JFrame frameShow = new JFrame("Hyperlinks");
        JTable tables = new JTable(getmodel());
        tables.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(tables);
        frameShow.setVisible(true);
        frameShow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton jButton1 = new JButton("Return to begin");
        
        
        jScrollPane1.setViewportView(tables);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frame.setVisible(true);
                frameShow.dispose();                
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(frameShow.getContentPane());
        frameShow.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        frameShow.pack();
        
    }
}
