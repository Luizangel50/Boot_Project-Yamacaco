package package0;
import package1.*;

import java.awt.*;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.*;

public class Face1 implements ActionListener {
    
    //Variables declaration
    public JFrame frame;
    private JButton btnShowHyperlinks, btnAdd, btnModify;
    private JLabel lblRegisterAndAccess, links;
    public Vector<Object> columnNames = new Vector<Object>();
    public Vector<Object> dados = new Vector<Object>();
    
    //Variables for sql commands
    public static String dbURL = "jdbc:derby://localhost:1527/Boot_DB/;create=true;user=luizangel;password=123546;";
    public static String table = "Tables";
    public static Connection Boot_DB;
    public static Statement stmt;
    
    public static void createConnection(){
        try{
            
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            Boot_DB = DriverManager.getConnection(dbURL);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private static void shutdown(){
        try
        {
            if (stmt != null)
            {
                stmt.close();
            }
            if (Boot_DB != null)
            {
                //DriverManager.getConnection("jdbc:derby:;shutdown=true");
                Boot_DB.close();
            }           
        }
        catch (SQLException sqlExcept)
        {
            sqlExcept.printStackTrace();
        }
    }
    
    public void Read() {
        try{
            stmt = Boot_DB.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from " + table);
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
            
            rs.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    //Create table with database data
    private DefaultTableModel model = new DefaultTableModel(dados, columnNames){
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
    
    public DefaultTableModel getmodel() {
        return model;
    }
    
    //initialize() method
    void initialize() throws FileNotFoundException {
        //JFrame settings
        frame = new JFrame("Hyperlinks");
        frame.setBounds(100, 100, 350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        //JLabel settings
        lblRegisterAndAccess = new JLabel("Register and access hyperlinks");
        lblRegisterAndAccess.setBounds(0, 11, 334, 23);
        lblRegisterAndAccess.setFont(new Font("Microsoft JhengHei", Font.BOLD, 16));
        lblRegisterAndAccess.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(lblRegisterAndAccess);
        
        //button ShowHyperlinks settings
        btnShowHyperlinks = new JButton("Show hyperlinks");
        btnShowHyperlinks.addActionListener(this);
        btnShowHyperlinks.setBounds(100, 58, 150, 47);
        btnShowHyperlinks.setFont(new Font("Tahoma", Font.BOLD, 13));
        frame.getContentPane().add(btnShowHyperlinks);
        
        //button Add settings
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(this);
        btnAdd.setBounds(100, 116, 65, 56);
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 13));
        frame.getContentPane().add(btnAdd);
        
        //button Modify settings
        btnModify = new JButton("Modify");
        btnModify.addActionListener(this);
        btnModify.setBounds(172, 116, 78, 56);
        btnModify.setFont(new Font("Tahoma", Font.BOLD, 13));
        frame.getContentPane().add(btnModify);
        
    }
    
    //interface implemented
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnShowHyperlinks){
            try {
                frame.setVisible(false);
                new Face2();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Face1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(e.getSource()==btnAdd){
            try {
                frame.setVisible(false);
                new Face3();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Face1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else if(e.getSource()==btnModify){
            try {
                frame.setVisible(false);
                new Face4();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Face1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //constructor
    public Face1() throws FileNotFoundException {
        initialize();
    }
    
    //main
    public static void main(String[] args) {
        createConnection();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Face1 window = new Face1();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
