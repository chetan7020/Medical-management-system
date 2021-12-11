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
public class Admin_GUI_sales implements ActionListener
{
    JPanel sales_p , sales_p_west , sales_p_center;
    JButton delete , update , add , add_b , update_b ;
    JLabel add_name , add_type , add_price  , today_total , money , add_quantity , grand_total_l, grand_money_l;
    JLabel update_name , update_type , update_price  ,update_quantity;
    JTextField add_name_tf  , add_price_tf  , add_quantity_tf;
    JTextField update_name_tf  , update_price_tf  , update_quantity_tf ;
    JTable sales_list ;
    JScrollPane sp ;
    JTextField search_tf;
    JButton search_b;
    DefaultTableModel model ;
    JComboBox add_type_cb , update_type_cb;
    Font f = new Font("Bebas Neue" , Font.BOLD , 14);
    String url = "jdbc:mysql://localhost:3306/medical_management?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String uname =  "root";
    String pass = "";
    int total , amount , grand_total;
    Admin_GUI_sales()
    {
        sales_p = new JPanel();
        
        Admin_GUI.center.removeAll();
        Admin_GUI.center.repaint();
        Admin_GUI.center.revalidate();
        Admin_GUI.center.add(sales_p);
        
        sales_p_west = new JPanel();
        sales_p_center = new JPanel();
        search_tf = new JTextField(10);
        search_b = new JButton("Search"); 
        delete = new JButton("Delete");
        update = new JButton("Update");
        add = new JButton("Add");
        today_total = new JLabel("Today's Total :");
        grand_total_l = new JLabel("Grand Total :");
        money = new JLabel("0.00 ₹");
        grand_money_l = new JLabel("0.00 ₹");
        sales_p.setLayout(new BorderLayout(7 , 7));
        sales_p_west.setLayout(null);
        sales_p_center.setLayout(null);
        
        model = new DefaultTableModel();
        sales_list = new JTable(model);
        sales_list.setBackground(Color.LIGHT_GRAY);
        sales_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        sales_list.setRowSorter(sorter);
        sp = new JScrollPane(sales_list , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        add.setForeground(new Color(255,250,250));
        add.setBackground(new Color(0,0,0));
        delete.setForeground(new Color(255,250,250));
        delete.setBackground(new Color(0,0,0));
        update.setForeground(new Color(255,250,250));
        update.setBackground(new Color(0,0,0));
        search_b.setForeground(new Color(255,250,250));
        search_b.setBackground(new Color(0,0,0));
         
        search_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        sales_p_west.setBackground(new Color(152, 251, 152));
        sales_p_center.setBackground(new Color(152, 251, 152));
        sales_p_west.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, new Color(0, 128, 0)));
        sales_p_center.setBorder(BorderFactory.createMatteBorder(0, 7, 0, 0, new Color(0, 128, 0)));
        sales_p_west.setPreferredSize(new Dimension(150,0));
        
        
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        String url = "jdbc:mysql://localhost:3306/medical_management?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String uname =  "root";
        String pass = "";
        String sql = "SELECT * FROM `mm_sales`";

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
        sales_list = new JTable(dataVector, columnNamesVector)
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
        sp = new JScrollPane( sales_list );
        sp.setBounds(30, 50 , 800, 320);
        today_total.setBounds(30 , 407 , 90 , 25);
        grand_total_l.setBounds(600, 407, 90, 25);
        
        
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        total = 0;
        grand_total = 0;
        for(int x = 0; x < sales_list.getRowCount(); x++)
        {
            if(formatter.format(date).toString().equals(sales_list.getValueAt(x, 6).toString()))
            {
                amount = Integer.parseInt(sales_list.getValueAt(x, 5).toString());
                total = amount+total;
            }
            
            amount = Integer.parseInt(sales_list.getValueAt(x, 5).toString());
            grand_total = amount+grand_total;
        }
        
        money.setText(total + "₹");
        money.setFont(new Font("Arial" , Font.BOLD , 34));
        money.setBounds(130 , 400 , 150 , 40);
        grand_money_l.setText(grand_total + "₹");
        grand_money_l.setFont(new Font("Arial" , Font.BOLD , 34));
        grand_money_l.setBounds(700 , 400 , 150 , 40);
        
        add.setBounds(33, 130, 80, 30);
        update.setBounds(33, 150, 80, 30);
        delete.setBounds(33, 250, 80, 30);
        
        search_tf.setBounds(500, 10, 150, 25);
        search_b.setBounds(680, 10, 80, 25);
             
//        sales_p_west.add(add);
        sales_p_west.add(update);
        sales_p_west.add(delete);
        
//        sales_p_center.add(search_tf);
//        sales_p_center.add(search_b);
        sales_p_center.add(today_total);
        sales_p_center.add(money);
        sales_p_center.add(sp);
        sales_p_center.add(grand_money_l);
        sales_p_center.add(grand_total_l);
        
        sales_p.add(sales_p_west , BorderLayout.WEST);
        sales_p.add(sales_p_center , BorderLayout.CENTER);
 
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
            add_quantity = new JLabel("Quantity :");
            add_name_tf = new JTextField(20);
            add_type_cb = new JComboBox();
            add_price_tf = new JTextField(20);
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
            add_quantity.setBounds(110, 190, 150, 30);
            
            add_name_tf.setBounds(220, 40, 150, 25);
            add_type_cb.setBounds(220, 90, 150, 30);
            add_price_tf.setBounds(220, 140, 150, 30);
            add_quantity_tf.setBounds(220, 190, 150, 30);
            
            add_b.setBounds(200, 285, 80, 30);
            
            add_b.addActionListener(this);
            
            add_name.setFont(f);
            add_type.setFont(f);
            add_price.setFont(f);
            add_quantity.setFont(f);
            
            add_name_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_type_cb.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_price_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add_quantity_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            
            add_db.add(add_name);
            add_db.add(add_type);
            add_db.add(add_price);
            add_db.add(add_quantity);
            
            add_db.add(add_name_tf);
            add_db.add(add_type_cb);
            add_db.add(add_price_tf);
            add_db.add(add_quantity_tf);
            
            add_db.add(add_b);
            
            add_db.getContentPane().setBackground(new Color(152, 251, 152));
            add_db.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, new Color(0, 128, 0)));
            add_db.setVisible(true);
            add_db.setSize(500,400); 
            add_db.setLocationRelativeTo(null);
        }
        else if(ae.getSource() == add_b)
        {
            if(add_name_tf.getText().equals("") || add_type_cb.getSelectedItem().equals("Select drug type") || add_price_tf.getText().equals("") || add_quantity_tf.getText().equals("") )
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Cann't insert empty fields","Sales - Add",JOptionPane.ERROR_MESSAGE); 
            }
            else
            {
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                try
                {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , uname , pass);   
                    Statement st = con.createStatement();
                    st.execute("INSERT INTO `mm_sales`(`Name`, `Type`, `Price`, `Quantity`, `Date`) VALUES ('"+add_name_tf.getText()+"','"+add_type_cb.getSelectedItem()+"','"+Integer.parseInt(add_price_tf.getText())+"','"+Integer.parseInt(add_quantity_tf.getText())+"','"+formatter.format(date).toString()+"');");
                    st.close();
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
//                int i = 0;
//                i = (int) (i + Double.parseDouble(add_price_tf.getText()));  
//                System.out.println(i);
//                money.setText( i+"₹");
                add_name_tf.setText("");
                add_price_tf.setText("");
                add_quantity_tf.setText("");
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Successfully Added.","Sales - Add",JOptionPane.INFORMATION_MESSAGE); 
            }
            
        }
        else if(ae.getSource() == delete)
        {
            int row_no = sales_list.getSelectedRow();
            if(row_no == -1)
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Select Row First.","Sales - Update",JOptionPane.ERROR_MESSAGE); 
            }
            else if(sales_list.getSelectedRow() != -1) 
            {
                try
                {
                    int sn = (int) sales_list.getModel().getValueAt(sales_list.getSelectedRow(), 0);
                    String sql = "DELETE FROM `mm_sales` WHERE SN = \""+sn+"\";";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(url , uname , pass);  
                    Statement st3 = con.createStatement();
                    String q1 = "SET  @num := 0;";
                    String q2 = "UPDATE `mm_sales` SET SN = @num := (@num+1);";
                    String q3 = "ALTER TABLE `mm_sales` AUTO_INCREMENT =1;";
                    st3.addBatch(q1);
                    st3.addBatch(q2);
                    st3.addBatch(q3);
                    Statement st = con.createStatement();
                    st.executeUpdate(sql);
                    st3.executeBatch();
                    st3.close();
                    st.close();
                    con.close();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Deleted Successfully","Sales - Delete",JOptionPane.INFORMATION_MESSAGE); 
                new Admin_GUI_sales();
            }
            
        }
        else if(ae.getSource() == update)
        {
            int row_no = sales_list.getSelectedRow();
            if(row_no == -1)
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Select Row First.","Sales - Update",JOptionPane.ERROR_MESSAGE); 
            }
            else
            {
                JDialog update_db =new JDialog(Admin_GUI.frame,"Update to Drugs List",false);
            
                update_db.setLayout(null);

                update_name = new JLabel("Name :");
                update_type = new JLabel("Type :");
                update_price = new JLabel("Price");
                update_quantity = new JLabel("Quantity :");
                update_name_tf = new JTextField(20);
                update_type_cb = new JComboBox();
                update_price_tf = new JTextField(20);
                update_quantity_tf = new JTextField(20);
                update_b = new JButton("Update");
                
                update_type_cb.addItem("Select drug type");
                update_type_cb.addItem("Medicine");
                update_type_cb.addItem("Syrup");

                update_name_tf.setText(sales_list.getModel().getValueAt(row_no, 1).toString());
                update_price_tf.setText(sales_list.getModel().getValueAt(row_no, 3).toString());
                update_quantity_tf.setText(sales_list.getModel().getValueAt(row_no, 4).toString());

                update_b.setForeground(new Color(255,250,250));
                update_b.setBackground(new Color(0,0,0));

                update_name.setBounds(110, 40, 150, 30);
                update_type.setBounds(110, 90, 150, 30);
                update_price.setBounds(110, 140, 150, 30);
                update_quantity.setBounds(110, 190, 150, 30);

                update_name_tf.setBounds(220, 40, 150, 25);
                update_type_cb.setBounds(220, 90, 150, 30);
                update_price_tf.setBounds(220, 140, 150, 30);
                update_quantity_tf.setBounds(220, 190, 150, 30);

                update_b.setBounds(200, 285, 80, 30);

                update_b.addActionListener(this);

                update_name.setFont(f);
                update_type.setFont(f);
                update_price.setFont(f);
                update_quantity.setFont(f);

                update_name_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_type_cb.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_price_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                update_quantity_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                update_db.add(update_name);
                update_db.add(update_type);
                update_db.add(update_price);
                update_db.add(update_quantity);

                update_db.add(update_name_tf);
                update_db.add(update_type_cb);
                update_db.add(update_price_tf);
                update_db.add(update_quantity_tf);

                update_db.add(update_b);

                update_db.getContentPane().setBackground(new Color(152, 251, 152));
                update_db.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 0, 0, 7, new Color(0, 128, 0)));
                update_db.setVisible(true);
                update_db.setSize(500,400);  
                update_db.setLocationRelativeTo(null);
            }
             
        }
        else if(ae.getSource() == update_b)
        {
            if(update_name_tf.getText().equals("") || update_type_cb.getSelectedItem().equals("Select drug type") || update_price_tf.getText().equals("") || update_quantity_tf.getText().equals(""))
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Empty Fields cann't be Entered.","Sales - Update",JOptionPane.ERROR_MESSAGE); 
            }
            else if(sales_list.getSelectedRow() != -1)
            {
                try
                {
                    int sn = (int) sales_list.getModel().getValueAt(sales_list.getSelectedRow(), 0);
                    String sql = "UPDATE `mm_sales` SET `Name`='"+update_name_tf.getText()+"',`Type`='"+update_type_cb.getSelectedItem()+"',`Price`='"+Integer.parseInt(update_price_tf.getText())+"',`Quantity`='"+Integer.parseInt(update_quantity_tf.getText())+"' WHERE Name = \""+sn+"\";";
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
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Row Updated","Sales - Update",JOptionPane.INFORMATION_MESSAGE); 
                new Admin_GUI_sales();
            }
            else
            {
                JOptionPane.showMessageDialog(Admin_GUI.frame,"Select Row First.","Sales - Update",JOptionPane.ERROR_MESSAGE); 
            }
            
        }
                
    }
}
