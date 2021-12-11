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
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class Admin_GUI_drugs implements ActionListener
{
    JPanel drugs_p , drugs_p_west , drugs_p_center;
    JButton delete , update , add , add_b , update_b , sales , sales_b;
    JLabel add_name , add_type , add_price , add_expiry_date , add_company , add_shelf_number , add_quantity ;
    JLabel update_name , update_type , update_price , update_expiry_date , update_company , update_shelf_number , update_quantity;
    JTextField add_name_tf  , add_price_tf , add_expiry_date_tf , add_company_tf , add_shelf_number_tf , add_quantity_tf ;
    JTextField sales_quantity_tf , update_name_tf  , update_price_tf , update_expiry_date_tf , update_company_tf , update_shelf_number_tf , update_quantity_tf ;
    JTable drugs_list ;
    JTextField search_tf;
    JButton search_b;
    JScrollPane sp ;
    DefaultTableModel model ;
    JComboBox add_type_cb , update_type_cb ; 
    int i;
    String url = "jdbc:mysql://localhost:3306/medical_management?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String uname =  "root";
    String pass = "";
    Font f = new Font("Bebas Neue" , Font.BOLD , 14);
    Admin_GUI_drugs()
    {
        drugs_p = new JPanel();
        
        Admin_GUI.center.removeAll();
        Admin_GUI.center.repaint();
        Admin_GUI.center.revalidate();
        Admin_GUI.center.add(drugs_p);
        
        drugs_p_west = new JPanel();
        drugs_p_center = new JPanel();
        search_tf = new JTextField(10);
        search_b = new JButton("Search"); 
        delete = new JButton("Delete");
        update = new JButton("Update");
        add = new JButton("Add");
        sales = new JButton("Sales");
        drugs_p.setLayout(new BorderLayout(7 , 7));
        drugs_p_west.setLayout(null);
        drugs_p_center.setLayout(null);
        
        model = new DefaultTableModel();
        drugs_list = new JTable(model);
        drugs_list.setBackground(Color.LIGHT_GRAY);
        drugs_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        sp = new JScrollPane(drugs_list , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        add.setForeground(new Color(255,250,250));
        add.setBackground(new Color(0,0,0));
        delete.setForeground(new Color(255,250,250));
        delete.setBackground(new Color(0,0,0));
        update.setForeground(new Color(255,250,250));
        update.setBackground(new Color(0,0,0));
        search_b.setForeground(new Color(255,250,250));
        search_b.setBackground(new Color(0,0,0));
        sales.setForeground(new Color(255,250,250));
        sales.setBackground(new Color(0,0,0));
         
        search_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        drugs_p_west.setBackground(new Color(152, 251, 152));
        drugs_p_center.setBackground(new Color(152, 251, 152));
        drugs_p_west.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, new Color(0, 128, 0)));
        drugs_p_center.setBorder(BorderFactory.createMatteBorder(0, 7, 0, 0, new Color(0, 128, 0)));
        drugs_p_west.setPreferredSize(new Dimension(150,0));
        
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        String url = "jdbc:mysql://localhost:3306/medical_management?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String uname =  "root";
        String pass = "";
        String sql = "SELECT * FROM `mm_drugs`";

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
        drugs_list = new JTable(dataVector, columnNamesVector)
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
        sp = new JScrollPane( drugs_list );
        sp.setBounds(30, 50 , 800, 400);
        drugs_p_center.add( sp );
        
        add.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        sales.addActionListener(this);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(drugs_list.getModel());
        drugs_list.setRowSorter(sorter);
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
        
        add.setBounds(33, 100, 80, 30);
        update.setBounds(33, 180, 80, 30);
        delete.setBounds(33, 260, 80, 30);
        sales.setBounds(33, 340, 80, 30);
        
        search_tf.setBounds(500, 10, 150, 25);
        search_b.setBounds(680, 10, 80, 25);
             
        drugs_p_west.add(add);
        drugs_p_west.add(update);
        drugs_p_west.add(delete);
        drugs_p_west.add(sales);
        
//        drugs_p_center.add(search_tf);
//        drugs_p_center.add(search_b);
        
        drugs_p.add(drugs_p_west , BorderLayout.WEST);
        drugs_p.add(drugs_p_center , BorderLayout.CENTER);
        
    }
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == add)
        {
            JDialog add_db =new JDialog(Admin_GUI.frame,"Add to Drugs List",false);
            
            add_db.setLayout(null);
            
            add_name = new JLabel("Name :");
            add_type = new JLabel("Type :");
            add_price = new JLabel("Price :");
            add_expiry_date = new JLabel("Expiry Day's");
            add_company = new JLabel("Company :");
            add_shelf_number = new JLabel("Shelf No. :");
            add_quantity = new JLabel("Qantity :");
            add_name_tf = new JTextField(20);
            add_type_cb = new JComboBox();
            add_price_tf = new JTextField(20);
            add_expiry_date_tf = new JTextField(20);
            add_company_tf = new JTextField(20);
            add_shelf_number_tf = new JTextField(20);
            add_quantity_tf = new JTextField(20);
            add_b = new JButton("Add");
            
            add_type_cb.addItem("Select drug type");
            add_type_cb.addItem("Medicine");
            add_type_cb.addItem("Syrup");
            
            add_b.setForeground(new Color(255,250,250));
            add_b.setBackground(new Color(0,0,0));
            
            add_name.setBounds(110, 40, 150, 30);
            add_type.setBounds(110, 90, 150, 30);
            add_price.setBounds(110, 140, 150, 30);
            add_expiry_date.setBounds(110, 190, 150, 30);
            add_company.setBounds(110, 240, 150, 30);
            add_shelf_number.setBounds(110, 290, 150, 30);
            add_quantity.setBounds(110, 340, 150, 30);
            
            add_name_tf.setBounds(220, 40, 150, 25);
            add_type_cb.setBounds(220, 90, 150, 33);
            add_price_tf.setBounds(220, 140, 150, 30);
            add_expiry_date_tf.setBounds(220, 190, 150, 30);
            add_company_tf.setBounds(220, 240, 150, 30);
            add_shelf_number_tf.setBounds(220, 290, 150, 30);
            add_quantity_tf.setBounds(220, 340, 150, 30);
            
            add_b.setBounds(200, 390, 80, 30);
            
            add_b.addActionListener(this);
            
            add_name.setFont(f);
            add_type.setFont(f);
            add_price.setFont(f);
            add_expiry_date.setFont(f);
            add_company.setFont(f);
            add_shelf_number.setFont(f);
            add_quantity.setFont(f);
            
            add_name_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_type_cb.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_price_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_expiry_date_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_company_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_quantity_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_shelf_number_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            
            add_db.add(add_name);
            add_db.add(add_type);
            add_db.add(add_price);
            add_db.add(add_expiry_date);
            add_db.add(add_company);
            add_db.add(add_shelf_number);
            add_db.add(add_quantity);
            
            add_db.add(add_name_tf);
            add_db.add(add_type_cb);
            add_db.add(add_price_tf);
            add_db.add(add_expiry_date_tf);
            add_db.add(add_company_tf);
            add_db.add(add_shelf_number_tf);
            add_db.add(add_quantity_tf);
            
            add_db.add(add_b);
            
            add_db.getContentPane().setBackground(new Color(152, 251, 152));
            add_db.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0, 128, 0)));
            add_db.setVisible(true);
            add_db.setSize(500,500);    
            add_db.setLocationRelativeTo(null);
        }
        else if(ae.getSource() == add_b)
        {
            if(add_name_tf.getText().equals("") || add_type_cb.getSelectedItem().equals("Select drug type") || add_price_tf.getText().equals("") || add_expiry_date_tf.getText().equals("") || add_company_tf.getText().equals("") || add_shelf_number_tf.getText().equals("") || add_quantity_tf.getText().equals(""))
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Empty Fields cann't be Entered.","Drugs - Add",JOptionPane.ERROR_MESSAGE); 
            }
            else
            {
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , uname , pass);   
                    Statement st = con.createStatement();
                    st.execute("INSERT INTO `mm_drugs` (`Name`, `Type`, `Price`, `Expiry day's`, `Company`, `Shelf No.`, `Quantity`) VALUES ('"+add_name_tf.getText()+"', '"+add_type_cb.getSelectedItem()+"', '"+Integer.parseInt(add_price_tf.getText())+"', '"+Integer.parseInt(add_expiry_date_tf.getText())+"', '"+add_company_tf.getText()+"', '"+Integer.parseInt(add_shelf_number_tf.getText())+"', '"+Integer.parseInt(add_quantity_tf.getText())+"')");
                    st.close();
                    if(Integer.parseInt(add_quantity_tf.getText()) < 15 || Integer.parseInt(add_expiry_date_tf.getText()) < 10 )
                    {
                        Statement st1 = con.createStatement();
                        st1.addBatch("DELETE FROM `mm_warning`;");
                        st1.addBatch("INSERT INTO `mm_warning` select `Name`, `Type`, `Expiry day's`, `Quantity` from `mm_drugs` where `Expiry day's` < 11 OR `Quantity` < 15;");
                        st1.executeBatch();
                        st1.close();
                    }
                    
                    
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                add_name_tf.setText("");
                add_price_tf.setText("");
                add_expiry_date_tf.setText("");
                add_company_tf.setText("");
                add_shelf_number_tf.setText("");
                add_quantity_tf.setText("");
                new Admin_GUI_drugs();
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Successfully Added.","Drugs - Add",JOptionPane.INFORMATION_MESSAGE); 
            } 
        }
        else if(ae.getSource() == delete)
        {
            if(drugs_list.getSelectedRow() != -1) 
            {
                try
                {
                    int sn = (int) drugs_list.getModel().getValueAt(drugs_list.getSelectedRow(), 0);
                    String Name = drugs_list.getModel().getValueAt(drugs_list.getSelectedRow(), 1).toString();
                    String type = drugs_list.getModel().getValueAt(drugs_list.getSelectedRow(), 2).toString();
                    int quatity = (int) drugs_list.getModel().getValueAt(drugs_list.getSelectedRow(), 7);
                    int ed = (int) drugs_list.getModel().getValueAt(drugs_list.getSelectedRow(), 4);
                    String sql1 = "DELETE FROM `mm_drugs` WHERE SN = \""+sn+"\";";
                    String sql2 = "DELETE FROM `mm_warning` WHERE Name = \""+Name+"\" AND Type = \""+type+"\" AND Quantity = \""+quatity+"\" AND `Expiry day's` = \""+ed+"\";";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , uname , pass);   
                    Statement st3 = con.createStatement();
                    String q1 = "SET  @num := 0;";
                    String q2 = "UPDATE `mm_drugs` SET SN = @num := (@num+1);";
                    String q3 = "ALTER TABLE `mm_drugs` AUTO_INCREMENT =1;";
                    st3.addBatch(q1);
                    st3.addBatch(q2);
                    st3.addBatch(q3);
                    Statement st1 = con.createStatement();
                    Statement st2 = con.createStatement();
                    st1.executeUpdate(sql1);
                    st2.executeUpdate(sql2);
                    st1.close();
                    st2.close();
                    
                    
                    st3.executeBatch();
                    st3.close();
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                new Admin_GUI_drugs();
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Successfully Deleted.","Drugs - Delete",JOptionPane.INFORMATION_MESSAGE); 
            }
            else
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Select Row First.","Drugs - Delete",JOptionPane.ERROR_MESSAGE); 
            }
            
        }
        else if(ae.getSource() == update)
        {
            if(drugs_list.getSelectedRow() == -1)
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Select Row First.","Drugs - Update",JOptionPane.ERROR_MESSAGE); 
            }
            else
            {
                int j = drugs_list.getSelectedRow();
                JDialog update_db =new JDialog(Admin_GUI.frame,"Update to Drugs List",false);
            
                update_db.setLayout(null);

                update_name = new JLabel("Name :");
                update_type = new JLabel("Type :");
                update_price = new JLabel("Price");
                update_expiry_date = new JLabel("Expiry Day's");
                update_company = new JLabel("Company :");
                update_shelf_number = new JLabel("Shelf Number :");
                update_quantity = new JLabel("Quantity :");
                update_name_tf = new JTextField(20);
                update_type_cb = new JComboBox();
                update_price_tf = new JTextField(20);
                update_expiry_date_tf = new JTextField(20);
                update_company_tf = new JTextField(20);
                update_shelf_number_tf = new JTextField(20);
                update_quantity_tf = new JTextField(20);
                update_b = new JButton("Update");
                
                update_type_cb.addItem("Select drug type");
                update_type_cb.addItem("Medicine");
                update_type_cb.addItem("Syrup");
                
                update_name_tf.setText(drugs_list.getModel().getValueAt(j, 1).toString());
                update_price_tf.setText(drugs_list.getModel().getValueAt(j, 3).toString());
                update_expiry_date_tf.setText(drugs_list.getModel().getValueAt(j, 4).toString());
                update_company_tf.setText(drugs_list.getModel().getValueAt(j, 5).toString());
                update_shelf_number_tf.setText(drugs_list.getModel().getValueAt(j, 6).toString());
                update_quantity_tf.setText(drugs_list.getModel().getValueAt(j, 7).toString());

                update_b.setForeground(new Color(255,250,250));
                update_b.setBackground(new Color(0,0,0));

                update_name.setBounds(110, 40, 150, 30);
                update_type.setBounds(110, 90, 150, 30);
                update_price.setBounds(110, 140, 150, 30);
                update_expiry_date.setBounds(110, 190, 150, 30);
                update_company.setBounds(110, 240, 150, 30);
                update_shelf_number.setBounds(110, 290, 150, 30);
                update_quantity.setBounds(110, 340, 150, 30);

                update_name_tf.setBounds(220, 40, 150, 25);
                update_type_cb.setBounds(220, 90, 150, 30);
                update_price_tf.setBounds(220, 140, 150, 30);
                update_expiry_date_tf.setBounds(220, 190, 150, 30);
                update_company_tf.setBounds(220, 240, 150, 30);
                update_shelf_number_tf.setBounds(220, 290, 150, 30);
                update_quantity_tf.setBounds(220, 340, 150, 30);

                update_b.setBounds(200, 390, 80, 30);

                update_b.addActionListener(this);

                update_name.setFont(f);
                update_type.setFont(f);
                update_price.setFont(f);
                update_expiry_date.setFont(f);
                update_company.setFont(f);
                update_shelf_number.setFont(f);
                update_quantity.setFont(f);

                update_name_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_type_cb.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_price_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_expiry_date_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_company_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_shelf_number_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_quantity_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                update_db.add(update_name);
                update_db.add(update_type);
                update_db.add(update_price);
                update_db.add(update_expiry_date);
                update_db.add(update_company);
                update_db.add(update_shelf_number);
                update_db.add(update_quantity);

                update_db.add(update_name_tf);
                update_db.add(update_type_cb);
                update_db.add(update_price_tf);
                update_db.add(update_expiry_date_tf);
                update_db.add(update_company_tf);
                update_db.add(update_shelf_number_tf);
                update_db.add(update_quantity_tf);

                update_db.add(update_b);

                update_db.getContentPane().setBackground(new Color(152, 251, 152));
                update_db.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0, 128, 0)));
                update_db.setVisible(true);
                update_db.setSize(500,500); 
                update_db.setLocationRelativeTo(null);
            }
             
        }
        else if(ae.getSource() == update_b)
        {
            if(update_name_tf.getText().equals("") || update_type_cb.getSelectedItem().equals("Select drug type") || update_price_tf.getText().equals("") || update_expiry_date_tf.getText().equals("") || update_company_tf.getText().equals("") || update_shelf_number_tf.getText().equals("") || update_quantity_tf.getText().equals(""))
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Empty Fields cann't be Entered.","Drugs - Update",JOptionPane.ERROR_MESSAGE); 
            }
            else if(drugs_list.getSelectedRow() != -1)
            {
                try
                {
                    int sn = (int) drugs_list.getModel().getValueAt(drugs_list.getSelectedRow(), 0);
                    String sql = "UPDATE `mm_drugs` SET `Name`='"+update_name_tf.getText()+"',`Type`='"+update_type_cb.getSelectedItem()+"',`Price`='"+Integer.parseInt(update_price_tf.getText())+"',`Expiry day's`='"+Integer.parseInt(update_expiry_date_tf.getText())+"',`Company`='"+update_company_tf.getText()+"',`Shelf No.`='"+Integer.parseInt(update_shelf_number_tf.getText())+"',`Quantity`='"+Integer.parseInt(update_quantity_tf.getText())+"' WHERE SN = \""+sn+"\";";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , uname , pass);
                    if(Integer.parseInt(update_quantity_tf.getText()) < 15 || Integer.parseInt(update_expiry_date_tf.getText()) < 10 )
                    {
                        Statement st1 = con.createStatement();
                        st1.addBatch("DELETE FROM `mm_warning`;");
                        st1.addBatch("INSERT INTO `mm_warning` select `Name`, `Type`, `Expiry day's`, `Quantity` from `mm_drugs` where `Expiry day's` < 11 OR `Quantity` < 15;");
                        st1.executeBatch();
                        st1.close();
                    }
                    Statement st = con.createStatement();
                    st.executeUpdate(sql);
                    st.close();
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                new Admin_GUI_drugs();
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Row Updated.","Drugs - Update",JOptionPane.INFORMATION_MESSAGE); 
            }
            else
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Select Row First.","Drugs - Update",JOptionPane.ERROR_MESSAGE); 
            }
            
        }
        else if(ae.getSource() == sales)
        {
            if(drugs_list.getSelectedRow() == -1)
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Select Row First.","Drugs - Sales",JOptionPane.ERROR_MESSAGE); 
            }
            else
            {
                JDialog sales_db =new JDialog(Admin_GUI.frame,"Add to sales",false);
            
                sales_db.setLayout(null);

                JLabel sales_quantity = new JLabel("Quantity :");
                sales_quantity_tf = new JTextField(20);
                
                sales_b = new JButton("Add To Sales");
                
                sales_b.setForeground(new Color(255,250,250));
                sales_b.setBackground(new Color(0,0,0));

                sales_quantity.setBounds(110, 38, 150, 30);

                sales_quantity_tf.setBounds(220, 40, 150, 25);

                sales_b.setBounds(180, 95, 150, 30);

                sales_b.addActionListener(this);

                sales_quantity.setFont(f);

                sales_quantity_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                sales_db.add(sales_quantity);

                sales_db.add(sales_quantity_tf);

                sales_db.add(sales_b);

                sales_db.getContentPane().setBackground(new Color(152, 251, 152));
                sales_db.getRootPane().setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(0, 128, 0)));
                sales_db.setVisible(true);
                sales_db.setSize(500,200); 
                sales_db.setLocationRelativeTo(null);
            }
        }
        else if(ae.getSource() == sales_b)
        {
            int sr = drugs_list.getSelectedRow();
            if(sales_quantity_tf.getText().equals(""))
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Empty Fields cann't be Entered.","Drugs - Sales",JOptionPane.ERROR_MESSAGE); 
            }
            else
            {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                try
                {
                    int sn = (int) drugs_list.getModel().getValueAt(drugs_list.getSelectedRow(), 0);
                    int q = 0 , u =0 ;
                    int qv = Integer.parseInt(sales_quantity_tf.getText());
                    int av = (int)drugs_list.getModel().getValueAt(drugs_list.getSelectedRow(), 7);
                    int pr = (int) drugs_list.getModel().getValueAt(drugs_list.getSelectedRow(), 3);
                    int t = pr * qv ;
                    
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , uname , pass);   
                    Statement st = con.createStatement();
                    if(qv <= av)
                    {
                        st.execute("INSERT INTO `mm_sales`(`Name`, `Type`, `Price`, `Quantity`,  `Total Price` , `Date`) VALUES ('"+drugs_list.getModel().getValueAt(sr, 1).toString()+"','"+drugs_list.getModel().getValueAt(sr, 2).toString()+"','"+Integer.parseInt(drugs_list.getModel().getValueAt(sr, 3).toString())+"','"+Integer.parseInt(sales_quantity_tf.getText())+"' ,'"+t+"' ,'"+formatter.format(date).toString()+"');");
                        PreparedStatement ps2 = con.prepareStatement("SELECT * FROM `mm_drugs` WHERE SN = ?");
                        ps2.setInt(1, sn);
                        ResultSet my_rs = ps2.executeQuery();
                        while (my_rs.next()) {
                            q = my_rs.getInt("Quantity");
                        }
                        u = q - Integer.parseInt(sales_quantity_tf.getText());
                        Statement st1 = con.createStatement();
                        st1.execute("UPDATE `mm_drugs` SET `Quantity`='"+u+"' WHERE SN = "+sn+";");
                        st1.close();
                        JOptionPane.showMessageDialog(Admin_GUI.frame,"Successfully Added To Sales.","Drugs - Sales",JOptionPane.INFORMATION_MESSAGE); 
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(Admin_GUI.frame,"Entered quantity more than available","Drugs - Sales",JOptionPane.ERROR_MESSAGE); 
                    }
                    if( u <= 0)
                    {
                        String sql1 = "DELETE FROM `mm_drugs` WHERE SN = \""+sn+"\";";
                        Statement st3 = con.createStatement();
                        st3.execute(sql1);
                        st3.close();
                    }
                    Statement st3 = con.createStatement();
                    String q1 = "SET  @num := 0;";
                    String q2 = "UPDATE `mm_drugs` SET SN = @num := (@num+1);";
                    String q3 = "ALTER TABLE `mm_drugs` AUTO_INCREMENT =1;";
                    st3.addBatch(q1);
                    st3.addBatch(q2);
                    st3.addBatch(q3);
                    st3.executeBatch();
                    st3.close();
                    st.close();
                    con.close();
                    new Admin_GUI_drugs();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                sales_quantity_tf.setText("");

            } 
        }
                
    }
}
