package com.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Tom Marvolo Riddle
 */
public class Admin_GUI_company implements ActionListener
{
    JPanel company_p , company_p_west , company_p_center;
    JButton delete , update , add , add_b , update_b ;
    JLabel add_name , add_address , add_phone_no , add_expiry_date , add_company ;
    JLabel update_name , update_address , update_phone_no , update_expiry_date , update_company ;
    JTextField add_name_tf , add_address_tf , add_phone_no_tf ;
    JTextField update_name_tf , update_address_tf , update_phone_no_tf ;
    JTable company_list ;
    JTextField search_tf;
    JButton search_b;
    JScrollPane sp ;
    DefaultTableModel model ;
    JComboBox add_type_cb , update_type_cb ; 
//    int i;
    String url = "jdbc:mysql://localhost:3306/medical_management?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String uname =  "root";
    String pass = "";
    Font f = new Font("Bebas Neue" , Font.BOLD , 14);
    Admin_GUI_company()
    {
        company_p = new JPanel();
        
        Admin_GUI.center.removeAll();
        Admin_GUI.center.repaint();
        Admin_GUI.center.revalidate();
        Admin_GUI.center.add(company_p);
        
        company_p_west = new JPanel();
        company_p_center = new JPanel();
        search_tf = new JTextField(10);
        search_b = new JButton("Search"); 
        delete = new JButton("Delete");
        update = new JButton("Update");
        add = new JButton("Add");
        company_p.setLayout(new BorderLayout(7 , 7));
        company_p_west.setLayout(null);
        company_p_center.setLayout(null);
        
        model = new DefaultTableModel();
        company_list = new JTable(model);
        company_list.setBackground(Color.LIGHT_GRAY);
        company_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        company_list.setRowSorter(sorter);
        sp = new JScrollPane(company_list , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        add.setForeground(new Color(255,250,250));
        add.setBackground(new Color(0,0,0));
        delete.setForeground(new Color(255,250,250));
        delete.setBackground(new Color(0,0,0));
        update.setForeground(new Color(255,250,250));
        update.setBackground(new Color(0,0,0));
        search_b.setForeground(new Color(255,250,250));
        search_b.setBackground(new Color(0,0,0));
         
        search_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        company_p_west.setBackground(new Color(152, 251, 152));
        company_p_center.setBackground(new Color(152, 251, 152));
        company_p_west.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, new Color(0, 128, 0)));
        company_p_center.setBorder(BorderFactory.createMatteBorder(0, 7, 0, 0, new Color(0, 128, 0)));
        company_p_west.setPreferredSize(new Dimension(150,0));
        
        
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        String url = "jdbc:mysql://localhost:3306/medical_management?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String uname =  "root";
        String pass = "";
        String sql = "SELECT * FROM `mm_company`";

        try (Connection connection = DriverManager.getConnection( url, uname, pass );
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql ))
        {
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            //  Get column names
            for (int i = 1; i <= columns; i++)
            {
                columnNames.add( md.getColumnName(i) );
            }

            //  Get row data
            while (rs.next())
            {
                ArrayList row = new ArrayList(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.add( rs.getObject(i) );
                }

                data.add( row );
            }
        }
        catch (SQLException e)
        {
            System.out.println( e.getMessage() );
        }
        Vector columnNamesVector = new Vector();
        Vector dataVector = new Vector();

        for (int i = 0; i < data.size(); i++)
        {
            ArrayList subArray = (ArrayList)data.get(i);
            Vector subVector = new Vector();
            for (int j = 0; j < subArray.size(); j++)
            {
                subVector.add(subArray.get(j));
            }
            dataVector.add(subVector);
        }

        for (int i = 0; i < columnNames.size(); i++ )
            columnNamesVector.add(columnNames.get(i));

        //  Create table with database data    
        company_list = new JTable(dataVector, columnNamesVector)
        {
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };
        sp = new JScrollPane( company_list );
        sp.setBounds(30, 50 , 800, 400);
        company_p_center.add( sp );
        
        add.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        search_b.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String text = search_tf.getText();
              if (text.trim().length() == 0) {
                 sorter.setRowFilter(null);
              } else {
                 sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
              }
          }
        });
        
        add.setBounds(33, 130, 80, 30);
        update.setBounds(33, 210, 80, 30);
        delete.setBounds(33, 290, 80, 30);
        
        search_tf.setBounds(500, 10, 150, 25);
        search_b.setBounds(680, 10, 80, 25);
             
        company_p_west.add(add);
        company_p_west.add(update);
        company_p_west.add(delete);
        
//        company_p_center.add(search_tf);
//        company_p_center.add(search_b);
        
        company_p.add(company_p_west , BorderLayout.WEST);
        company_p.add(company_p_center , BorderLayout.CENTER);
 
    }
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == add)
        {
            JDialog add_db =new JDialog(Admin_GUI.frame,"Add to Drugs List",false);
            
            add_db.setLayout(null);
            
            add_name = new JLabel("Name :");
            add_address = new JLabel("Address :");
            add_phone_no = new JLabel("Phone No.");
            add_name_tf = new JTextField(20);
            add_address_tf = new JTextField(20);
            add_phone_no_tf = new JTextField(20);
            add_b = new JButton("Add");
            
            add_b.setForeground(new Color(255,250,250));
            add_b.setBackground(new Color(0,0,0));
            
            add_name.setBounds(110, 40, 150, 30);
            add_address.setBounds(110, 90, 150, 30);
            add_phone_no.setBounds(110, 140, 150, 30);
            
            add_name_tf.setBounds(220, 40, 150, 25);
            add_address_tf.setBounds(220, 90, 150, 30);
            add_phone_no_tf.setBounds(220, 140, 150, 30);

            add_b.setBounds(200, 200, 80, 30);
            
            add_b.addActionListener(this);
            
            add_name.setFont(f);
            add_address.setFont(f);
            add_phone_no.setFont(f);
            
            add_name_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_address_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_phone_no_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            
            add_db.add(add_name);
            add_db.add(add_address);
            add_db.add(add_phone_no);
            
            add_db.add(add_name_tf);
            add_db.add(add_address_tf);
            add_db.add(add_phone_no_tf);

            add_db.add(add_b);
            
            add_db.getContentPane().setBackground(new Color(152, 251, 152));
            add_db.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0, 128, 0)));
            add_db.setVisible(true);
            add_db.setSize(500,280); 
            add_db.setLocationRelativeTo(null);
        }
        else if(ae.getSource() == add_b)
        {
            if(add_name_tf.getText().equals("") || add_address_tf.getText().equals("") || add_phone_no_tf.getText().equals(""))
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame, "Empty field cann't be added","Add Error",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , uname , pass);   
                    Statement st = con.createStatement();
                    st.execute("INSERT INTO `mm_company`(`Name`, `Address`, `Phone No.`) VALUES ('"+add_name_tf.getText()+"','"+add_address_tf.getText()+"','"+add_phone_no_tf.getText()+"');");
                    st.close();
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                add_name_tf.setText("");
                add_address_tf.setText("");
                add_phone_no_tf.setText("");
                new Admin_GUI_company();
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Successfully Added.","Add Information.",JOptionPane.INFORMATION_MESSAGE); 
            }          
        }
        else if(ae.getSource() == delete)
        {
            if(company_list.getSelectedRow() != -1) 
            {
                try
                {
                    String name = company_list.getModel().getValueAt(company_list.getSelectedRow(), 0).toString();
                    String sql = "DELETE FROM `mm_company` WHERE Name = \""+name+"\";";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , uname , pass);   
                    Statement st = con.createStatement();
                    st.executeUpdate(sql);
                    st.close();
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                new Admin_GUI_company();
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Successfully Deleted.","Delete Information",JOptionPane.INFORMATION_MESSAGE); 
            }
            else
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Select Row First.","Delete Error",JOptionPane.ERROR_MESSAGE); 
            }
            
        }
        else if(ae.getSource() == update)
        {
            int row_no = company_list.getSelectedRow();
            if(row_no == -1)
            {
                 JOptionPane.showMessageDialog(Admin_GUI.frame, "Select Row First","Update Error",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JDialog update_db =new JDialog(Admin_GUI.frame,"Update to Drugs List",false);
            
                update_db.setLayout(null);

                update_name = new JLabel("Name :");
                update_address = new JLabel("Type :");
                update_phone_no = new JLabel("Phone No.:");

                update_name_tf = new JTextField(20);
                update_address_tf = new JTextField(20);
                update_phone_no_tf = new JTextField(20);

                update_b = new JButton("Update");

                update_name_tf.setText(company_list.getModel().getValueAt(row_no, 0).toString());
                update_address_tf.setText(company_list.getModel().getValueAt(row_no, 1).toString());
                update_phone_no_tf.setText(company_list.getModel().getValueAt(row_no, 2).toString());

                update_b.setForeground(new Color(255,250,250));
                update_b.setBackground(new Color(0,0,0));

                update_name.setBounds(110, 40, 150, 30);
                update_address.setBounds(110, 90, 150, 30);
                update_phone_no.setBounds(110, 140, 170, 30);

                update_name_tf.setBounds(220, 40, 150, 25);
                update_address_tf.setBounds(220, 90, 150, 30);
                update_phone_no_tf.setBounds(220, 140, 150, 30);

                update_b.setBounds(200, 200, 80, 30);

                update_b.addActionListener(this);

                update_name.setFont(f);
                update_address.setFont(f);
                update_phone_no.setFont(f);

                update_name_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_address_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_phone_no_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                update_db.add(update_name);
                update_db.add(update_address);
                update_db.add(update_phone_no);

                update_db.add(update_name_tf);
                update_db.add(update_address_tf);
                update_db.add(update_phone_no_tf);

                update_db.add(update_b);

                update_db.getContentPane().setBackground(new Color(152, 251, 152));
                update_db.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0, 128, 0)));
                update_db.setVisible(true);
                update_db.setSize(500,280);
                update_db.setLocationRelativeTo(null);
            }
             
        }
        else if(ae.getSource() == update_b)
        {
            if(update_name_tf.getText().equals("") || update_address_tf.getText().equals("") || update_phone_no_tf.getText().equals(""))
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame, "Empty field cann't be added","Add Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(company_list.getSelectedRow() != -1)
            {
                try
                {
                    String name = company_list.getModel().getValueAt(company_list.getSelectedRow(), 0).toString();
                    String sql = "UPDATE `mm_company` SET `Name`='"+update_name_tf.getText()+"',`Address`='"+update_address_tf.getText()+"',`Phone No.`='"+update_phone_no_tf.getText()+"' WHERE Name = \""+name+"\";";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , uname , pass);   
                    Statement st = con.createStatement();
                    st.executeUpdate(sql);
                    st.close();
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                new Admin_GUI_company();
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Row Updated.","Update Error",JOptionPane.INFORMATION_MESSAGE); 
            }
            else
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Select Row First.","Update",JOptionPane.ERROR_MESSAGE); 
            }
        }
                
    }
}
