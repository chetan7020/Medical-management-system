package com.project;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tom Marvolo Riddle
 */
public class Admin_GUI_warning
{
    JPanel warning_p ;
    JTable warning_list ;
    JScrollPane sp ;
    DefaultTableModel model ;
    int i , w;
    Font f = new Font("Bebas Neue" , Font.BOLD , 14);
    String url = "jdbc:mysql://localhost:3306/medical_management?zeroDateTimeBehavior=CONVERT_TO_NULL";
    String uname =  "root";
    String pass = "";
    Admin_GUI_warning()
    {
        warning_p = new JPanel();
        
        Admin_GUI.center.removeAll();
        Admin_GUI.center.repaint();
        Admin_GUI.center.revalidate();
        Admin_GUI.center.add(warning_p);
        
        warning_p.setLayout(null);
        
        model = new DefaultTableModel();
        warning_list = new JTable(model);
        
        ArrayList columnNames = new ArrayList();
        ArrayList data = new ArrayList();

        String sql = "SELECT * FROM `mm_warning`";

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
        warning_list = new JTable(dataVector, columnNamesVector)
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
        
        
        warning_list.setBackground(new Color(255,69,0));
        
        sp = new JScrollPane( warning_list );
        warning_p.add( sp );
        
        warning_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
         
        warning_p.setBackground(new Color(152, 251, 152));
        
        sp.setBounds(100, 30 , 800, 400);
        
        warning_p.add(sp);
         
    }
}
