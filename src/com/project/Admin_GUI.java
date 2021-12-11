package com.project;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Tom Marvolo Riddle
 */

public class Admin_GUI implements ActionListener 
{
    static JPanel center , west , north , south ,sales_p ,user_p ;
    static JFrame frame ;
    JButton logout , drugs , company , warning , sales ;
    CardLayout c ;
    static JLabel logo , name , footer , bg ;
    JLabel about_us ;
    Admin_GUI()
    {
        c = new CardLayout();
        frame = new JFrame();
        frame.setLayout( new BorderLayout(7 , 7) );
        
        name = new JLabel("Medical Management System");
        name.setFont(new Font("Josefin Sans" , Font.BOLD , 45));
        logo = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\chetan\\OneDrive\\Desktop\\Advanced java micro-project\\src\\logo.png").getImage().getScaledInstance(60,50, Image.SCALE_DEFAULT)));
        bg = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\chetan\\OneDrive\\Desktop\\Advanced java micro-project\\src\\bg3.jpg").getImage().getScaledInstance(1020,600, Image.SCALE_DEFAULT)));
        about_us = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\chetan\\OneDrive\\Desktop\\Advanced java micro-project\\src\\team.png").getImage().getScaledInstance(40,40, Image.SCALE_DEFAULT)));
        logout = new JButton("Logout");
        drugs = new JButton("Drugs");
        company = new JButton("Company");
        warning = new JButton("Warning");
        sales = new JButton("Sales");
        footer = new JLabel("© Copyright Medical Management System | All rights reserved");
        footer.setFont(new Font("Bahnschrift Light" , Font.BOLD , 22));
        sales_p = new JPanel() ;
        user_p = new JPanel() ;

        // ---------------- sub panels of border layout ---------------
        center = new JPanel();
        west = new JPanel();
        north = new JPanel();
        south = new JPanel();
        
        west.setLayout(null);
        north.setLayout(null);
        center.setLayout(c);
        south.setLayout(new FlowLayout());
        
        drugs.addActionListener(this);
        company.addActionListener(this);
        sales.addActionListener(this);
        warning.addActionListener(this);
        logout.addActionListener(this);
        about_us.addMouseListener(new MouseAdapter()  
        {  
            public void mouseClicked(MouseEvent e)  
            {  
                JFrame as = new JFrame();
                JPanel as_1 = new JPanel();
                JPanel as_2 = new JPanel();
                JPanel as_3 = new JPanel();
                
                as.setLayout(new BorderLayout());
                as_1.setLayout(null);
                as_2.setLayout(null);
                as_3.setLayout(null);
                
                Font n = new Font("Times New Roman" , Font.BOLD , 20);
                Font r = new Font("Times New Roman" , Font.BOLD , 18);
                
                JLabel cn = new JLabel("Name - Patil Chetan");
                JLabel cr = new JLabel("Roll No - 66");
                JLabel an = new JLabel("Name - Kale Atharva");
                JLabel ar = new JLabel("Roll No - 26");
                JLabel sn = new JLabel("Name - Kakde Shantanu");
                JLabel sr = new JLabel("Roll No - 25");
                
                cn.setFont(n);
                an.setFont(n);
                sn.setFont(n);
                
                cr.setFont(r);
                ar.setFont(r);
                sr.setFont(r);
                
                sn.setBounds(40, 30, 400, 30);
                sr.setBounds(40, 70, 400, 30);
                an.setBounds(40, 30, 400, 30);
                ar.setBounds(40, 70, 400, 30);
                cn.setBounds(40, 30, 400, 30);
                cr.setBounds(40, 70, 400, 30);
                
                as_1.add(sn);
                as_2.add(an);
                as_3.add(cn);
                as_1.add(sr);
                as_2.add(ar);
                as_3.add(cr);

                as_1.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, new Color(0, 128, 0)));
                as_2.setBorder(BorderFactory.createMatteBorder(7, 0, 7, 7, new Color(0, 128, 0)));
                as_3.setBorder(BorderFactory.createMatteBorder(7, 0, 7, 7, new Color(0, 128, 0)));
                
                as_1.setBackground(new Color(152, 251, 152));
                as_2.setBackground(new Color(152, 251, 152));
                as_3.setBackground(new Color(152, 251, 152));
                
                as_1.setPreferredSize(new Dimension(300 , 300));
                as_2.setPreferredSize(new Dimension(300 , 300));
                as_3.setPreferredSize(new Dimension(300 , 300));
                
                as.add(as_1 , BorderLayout.LINE_START);
                as.add(as_2 , BorderLayout.CENTER);
                as.add(as_3 , BorderLayout.LINE_END);
                
                as.setSize(900,200);
                as.setTitle("About Us");
                as.setVisible(true);
                as.setLocationRelativeTo(null);
            }  
        }); 

        logo.setBounds(300,15, 80, 70);
        name.setBounds(380, 15, 800, 70);
        
        north.add(logo);
        north.add(name);
        north.add(about_us);
        
        drugs.setBounds(80, 72, 90, 30);
        company.setBounds(80, 150, 90, 30);
        sales.setBounds(80, 220, 90, 30);
        warning.setBounds(80, 290, 90, 30);
        logout.setBounds(80, 360, 90, 30);
        bg.setBounds(0, 0, 1020, 600);
        about_us.setBounds(60, 25, 40, 40);
        
        center.add(bg);
        west.add(drugs);
        west.add(warning);
        west.add(sales);
        west.add(company);
        west.add(logout);
        
        south.add(footer);
        
        logout.setForeground(new Color(255,250,250));
        logout.setBackground(new Color(0,0,0));
        drugs.setForeground(new Color(255,250,250));
        drugs.setBackground(new Color(0,0,0));
        company.setForeground(new Color(255,250,250));
        company.setBackground(new Color(0,0,0));
        sales.setForeground(new Color(255,250,250));
        sales.setBackground(new Color(0,0,0));
        warning.setForeground(new Color(255,250,250));
        warning.setBackground(new Color(0,0,0));
        
        center.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, new Color(0, 128, 0)));
        west.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, new Color(0, 128, 0)));
        north.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, new Color(0, 128, 0)));
        south.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, new Color(0, 128, 0)));
        
        
        user_p.setBackground(new Color(152, 251, 152));
        sales_p.setBackground(new Color(152, 251, 152));
        
        // ---------------- set bg to sub panels of border layout ---------------
        center.setBackground(new Color(152, 251, 152));
        west.setBackground(new Color(152, 251, 152));
        north.setBackground(new Color(152, 251, 152));
        south.setBackground(new Color(152, 251, 152));
        
        // ---------------- set prefered size to sub panels of border layout ---------------
        north.setPreferredSize(new Dimension(100,100));
        west.setPreferredSize(new Dimension(250,100));
        south.setPreferredSize(new Dimension(100,70));
        
        // ---------------- add sub panels of border layout ---------------
        frame.add(north,BorderLayout.NORTH);
        frame.add(south,BorderLayout.SOUTH);
	frame.add(center,BorderLayout.CENTER);
	frame.add(west,BorderLayout.WEST);
              
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    
    }

    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == drugs)
        {
            new Admin_GUI_drugs();
        }
        else if(e.getSource() == company)
        {
            new Admin_GUI_company();
        }
        else if(e.getSource() == sales)
        {
            new Admin_GUI_sales();
        }
        else if(e.getSource() == warning)
        {
            new Admin_GUI_warning();
        }
        else if(e.getSource() == logout)
        {
            JDialog logout_db =new JDialog(frame,"logout",false);
            JLabel logout_l = new JLabel("Really want to logout?" );
            JButton yes = new JButton("Yes");
            JButton no = new JButton("No");
            
            logout_db.setLayout(null);
            
            logout_l.setFont(new Font("Bebas Neue" , Font.BOLD , 17));
            
            yes.setForeground(new Color(255,250,250));
            yes.setBackground(new Color(0,0,0));
            no.setForeground(new Color(255,250,250));
            no.setBackground(new Color(0,0,0));
            
            logout_l.setBounds(100, 30, 200, 30);
            yes.setBounds(105, 80, 70, 30);
            no.setBounds(205, 80, 70, 30);
            
            yes.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose(); 
                    frame.setVisible(false);
                }
            });
            no.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    logout_db.dispose(); 
                    logout_db.setVisible(false);
                }
            });

            logout_db.add(logout_l);
            logout_db.add(yes);
            logout_db.add(no);
            
            logout_db.getContentPane().setBackground(new Color(152, 251, 152));
            logout_db.setVisible(true);
            logout_db.setSize(400 , 200);
            logout_db.setLocationRelativeTo(null);
        }
    }  
}
