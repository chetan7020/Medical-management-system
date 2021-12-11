package com.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

/**
 *
 * @author Tom Marvolo Riddle
 */

public class Login implements ActionListener
{
    JFrame frame ;
    JPanel center , north ;
    JTextField username_tf ;
    JPasswordField password_tf;
    JButton submit  , back;
    JLabel header , logo , username , password , uname , pass , medicine1 , medicine2;
    Login()
    {
        frame = new JFrame();
        center = new JPanel();
        north = new JPanel();
        
        username_tf = new JTextField();
        password_tf = new JPasswordField();
        username = new JLabel("Username :");
        password = new JLabel("Password :");
        submit = new JButton("Submit");
        back = new JButton("Back");
        header = new JLabel("Medical Management System");
        logo = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\chetan\\OneDrive\\Desktop\\Advanced java micro-project\\src\\logo.png").getImage().getScaledInstance(60,50, Image.SCALE_DEFAULT)));
        medicine1 = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\chetan\\OneDrive\\Desktop\\Advanced java micro-project\\src\\medical.png").getImage().getScaledInstance(512,512, Image.SCALE_DEFAULT)));
        medicine2 = new JLabel(new ImageIcon(new ImageIcon("C:\\Users\\chetan\\OneDrive\\Desktop\\Advanced java micro-project\\src\\medical.png").getImage().getScaledInstance(512,512, Image.SCALE_DEFAULT)));
        uname = new JLabel("admin");
        pass = new JLabel("Chet@n7020");
        
        submit.setForeground(new Color(255,250,250));
        submit.setBackground(new Color(0,0,0));
        back.setForeground(new Color(255,250,250));
        back.setBackground(new Color(0,0,0));
        
        //---------set font to labels------------------------ 
        header.setFont(new Font("Bebas Neue" , Font.BOLD , 45));
        username.setFont(new Font("Bebas Neue" , Font.PLAIN , 17));
        password.setFont(new Font("Bebas Neue" , Font.PLAIN , 17));
        
        //------------set layout to frame , center(center panel) and north(north panel)-------
        frame.setLayout(new BorderLayout(7,7));
        center.setLayout(null);
        north.setLayout(null);
        
        //------------------set tooltip to username_tf and password_tf on center panel------------
        username_tf.setToolTipText("Enter username"); 
        password_tf.setToolTipText("Enter password"); 
        
        submit.addActionListener(this);
        back.addActionListener(this);
        
        username_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        password_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        //------------------set bounds to components on center panel------------
        username.setBounds(450, 170, 100, 25);
        password.setBounds(450, 230, 100, 25);
        username_tf.setBounds(600, 170, 200, 25);
        password_tf.setBounds(600, 230, 200, 25);
        back.setBounds(500, 285, 80, 30);
        submit.setBounds(650, 285, 80, 30);
        medicine1.setBounds(0, 20, 512, 512);
        medicine2.setBounds(765, 20, 512, 512);
        
        logo.setBounds(300,15, 80, 70);
        header.setBounds(380, 15, 800, 70);
        
        //----------------add coponents on center panel (north)-------------
        north.add(header);
        north.add(logo);
        
        //------------------set border to components on center panel------------
        center.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, new Color(0, 128, 0)));
        north.setBorder(BorderFactory.createMatteBorder(7, 7, 7, 7, new Color(0, 128, 0)));
        username_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        password_tf.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        //----------------add coponents on center panel (center)-------------
        center.add(username_tf);
        center.add(password_tf);
        center.add(submit);
        center.add(back);
        center.add(username);
        center.add(password);
        center.add(medicine1);
        center.add(medicine2);
        
        //------------------set background to panels in frame-------------------------
        center.setBackground(new Color(152, 251, 152));
        north.setBackground(new Color(152, 251, 152));
        
        //---------------------set size to panels in frame-------------------------
        north.setPreferredSize(new Dimension(100,100));
        
        //-----------------------add panel to frame-------------------------
        frame.add(center , BorderLayout.CENTER);
        frame.add(north , BorderLayout.NORTH);
        
        //--------------- frame settings -------------------    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == submit)
        {
            if( username_tf.getText().equals(uname.getText())  && password_tf.getText().equals(pass.getText()))
            {
                new Admin_GUI();
                frame.dispose();
            }
            else if(username_tf.getText().equals("")  || password_tf.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Use can't place fields empty.");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Username or Password is Invalid.");
            }
            
        }
        else if(ae.getSource() == back)
        {
            new Welcome();
            frame.dispose();
        }
    }
}
